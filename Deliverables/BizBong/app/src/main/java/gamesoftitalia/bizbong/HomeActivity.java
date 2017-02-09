package gamesoftitalia.bizbong;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.concurrent.ExecutionException;

import gamesoftitalia.bizbong.adapters.PagerAdapter;
import gamesoftitalia.bizbong.connessione.ProfiloAsync;
import gamesoftitalia.bizbong.entity.Impostazioni;
import gamesoftitalia.bizbong.entity.Profilo;
import gamesoftitalia.bizbong.service.MusicServiceBase;

public class HomeActivity extends AppCompatActivity {
    static final int READ_BLOCK_SIZE = 100;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private LinearLayout profiloButton;
    private ImageButton logoutButton;
    private ImageButton impostazioniButton;
    private static TextView nicknameText;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private Impostazioni entity;

    private Intent music=new Intent();
    private boolean audioAssociato;
    private MusicServiceBase mServ;


    private String nickname;
    private String profiloGson;
    private Profilo profilo;


    ServiceConnection Scon =new ServiceConnection(){
        /*implementazione metodi astratti*/
        public void onServiceConnected(ComponentName name, IBinder binder) {
            mServ = ((MusicServiceBase.ServiceBinder)binder).getService();
        }
        public void onServiceDisconnected(ComponentName name) {
            mServ = null;
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        caricaImpostazioni();

        //Shared
        sharedPreferences = getSharedPreferences("sessioneUtente", ProfiloActivity.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        if(sharedPreferences.getAll().containsKey("nickname"))
            nickname = sharedPreferences.getAll().get("nickname").toString();

        try {
            profiloGson = new ProfiloAsync(HomeActivity.this).execute(nickname).get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        profilo = new Gson().fromJson(profiloGson, Profilo.class);

        // String
        nicknameText = (TextView) findViewById(R.id.nicknameProfilo);
        if(sharedPreferences.getAll().containsKey("nickname"))
            nicknameText.setText(sharedPreferences.getAll().get("nickname").toString());

        //Music
        audioAssociato=entity.getAudio();
        if (audioAssociato){
            music.setClass(this, MusicServiceBase.class);
            bindService(music, Scon, Context.BIND_AUTO_CREATE);    //legare il servizio al contesto
            startService(music);
        }

        /*
        // Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        */

        // TabLayout
        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("Tab 1"));
        tabLayout.addTab(tabLayout.newTab().setText("Tab 2"));
        tabLayout.addTab(tabLayout.newTab().setText("Tab 3"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        setupTabIcons();

        // ViewPager
        viewPager = (ViewPager) findViewById(R.id.pager);
        final PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount(), profilo, entity);
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        // Button
        profiloButton = (LinearLayout) findViewById(R.id.profiloButton);
        profiloButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, ProfiloActivity.class);
                intent.putExtra("Impostazioni", (Serializable) entity);
                startActivity(intent);
            }
        });

        //Button impostazioni
        impostazioniButton = (ImageButton) findViewById(R.id.impostazioniButton);
        impostazioniButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, ImpostazioniActivity.class);
                startActivity(intent);
            }
        });

        // ImageButton
        logoutButton = (ImageButton) findViewById(R.id.logoutButton);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Editor clear all element
                editor.clear();
                editor.commit();


                // Restart MainActivity
                Intent intent = new Intent(HomeActivity.this, IntroActivity.class);
                startActivity(intent);
                finish();
            }
        });



    }

    private void setupTabIcons() {

        TextView tabUno = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tablayout, null);
        tabUno.setText("ONE");
        tabUno.setCompoundDrawablesWithIntrinsicBounds(0, R.mipmap.ic_home, 0, 0);
        tabLayout.getTabAt(0).setCustomView(tabUno);

        TextView tabDue = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tablayout, null);
        tabDue.setText("TWO");
        tabDue.setCompoundDrawablesWithIntrinsicBounds(0, R.mipmap.ic_challenge, 0, 0);
        tabLayout.getTabAt(1).setCustomView(tabDue);

        TextView tabTre = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tablayout, null);
        tabTre.setText("THREE");
        tabTre.setCompoundDrawablesWithIntrinsicBounds(0, R.mipmap.ic_pie_chart, 0, 0);
        tabLayout.getTabAt(2).setCustomView(tabTre);

        /*TextView tabQuattro = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tablayout, null);
        tabQuattro.setText("FOUR");
        tabQuattro.setCompoundDrawablesWithIntrinsicBounds(0, R.mipmap.ic_setting, 0, 0);
        tabLayout.getTabAt(3).setCustomView(tabQuattro);*/
    }

    private void caricaImpostazioni(){
        //stringa da dividere
        String imp=ReadBtn();

        //divisione stringa
        String[] i=imp.split("\\-");

        String lingua=i[0];
        String audioS=i[1];
        String effettiS=i[2];
        String vibrazioneS=i[3];

        //conversione stringhe
        boolean audio= Boolean.valueOf(audioS);             //String-->boolean
        boolean effetti= Boolean.valueOf(effettiS);         //String-->boolean
        boolean vibrazione= Boolean.valueOf(vibrazioneS);   //String-->boolean

        //oggetto impostazioni da avvalorare con i valori presi da file
        entity=new Impostazioni(lingua,audio,effetti,vibrazione);
    }

    private String ReadBtn() {
        String s="";
        try {
            FileInputStream fileIn=openFileInput("BizBong.txt");
            InputStreamReader InputRead= new InputStreamReader(fileIn);
            char[] inputBuffer= new char[READ_BLOCK_SIZE];
            int charRead;
            while ((charRead=InputRead.read(inputBuffer))>0) {
                String readstring=String.copyValueOf(inputBuffer,0,charRead);
                s +=readstring;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return s;
    }



    @Override
    protected void onResume() {     /*quando l'app viene riattivata*/
        super.onResume();
        if (audioAssociato==true)
            if (mServ != null) {
                bindService(music, Scon, Context.BIND_AUTO_CREATE);
                mServ.resumeMusic();
            }
    };

    @Override
    protected void onPause() {            /*quando l'app va in background viene stoppato*/
        if (audioAssociato==true)
            if(mServ!=null){
                mServ.pauseMusic();
                unbindService(Scon);
            }
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        try {
            audioAssociato = entity.getAudio();
            if (audioAssociato) {
                mServ.stopMusic();
                stopService(music);
                unbindService(Scon);   //togliere il contesto al servizio
            }
        } catch (NullPointerException | IllegalArgumentException e){}

        super.onDestroy();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            // Dialog
            AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
            LayoutInflater inflater = this.getLayoutInflater();
            final View dialogView = inflater.inflate(R.layout.dialog_close_app, null);
            dialogBuilder.setView(dialogView);

            final AlertDialog alertDialog = dialogBuilder.create();

            // Button Exit
            Button yesButton = (Button) dialogView.findViewById(R.id.yesButton);
            yesButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    android.os.Process.killProcess(android.os.Process.myPid());
                }
            });

            Button noButton = (Button) dialogView.findViewById(R.id.yesButton);
            noButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    alertDialog.dismiss();
                }
            });

            alertDialog.show();


            return false;

        }
        return super.onKeyDown(keyCode, event);
    }
}

