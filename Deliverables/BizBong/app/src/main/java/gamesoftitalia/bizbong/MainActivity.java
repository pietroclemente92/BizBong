package gamesoftitalia.bizbong;

import android.app.Dialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.util.Locale;
import gamesoftitalia.bizbong.entity.Impostazioni;
import gamesoftitalia.bizbong.gifanimator.PlayGifView;
import gamesoftitalia.bizbong.service.MusicServiceBase;

/**
 * Created by GameSoftItalia on 09/01/2017.
 */

public class MainActivity extends AppCompatActivity {

    static final int READ_BLOCK_SIZE = 100;
    private Button creaProfiloButton, loginButton, allenamentoButton;
    private PlayGifView pGif;
    private TextView loading_dialog_textview;
    private Intent intent;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private LayoutInflater inflater;
    private Impostazioni entity;
    private boolean audioAssociato;
    private Intent music = new Intent();
    private MusicServiceBase mServ;

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
        setContentView(R.layout.activity_main);

        if (controlloFile())
               WriteBtn(Locale.getDefault().getISO3Language(),"true","true","true");   //creazione file impostazioni
        caricaImpostazioni();
        configuraLingua();

        //audio activity
        audioAssociato=entity.getAudio();
        if (audioAssociato){
            music.setClass(this, MusicServiceBase.class);
            bindService(music, Scon,Context.BIND_AUTO_CREATE);    //legare il servizio al contesto
            startService(music);
        }

        // Layout
        inflater = LayoutInflater.from(MainActivity.this);

        //Shared
        sharedPreferences = getSharedPreferences("sessioneUtente", MainActivity.this.MODE_PRIVATE);
        //Log.d("Debug", "Value-->"+sharedPreferences.getAll().containsKey("online"));

        // Loading Dialog
        final Dialog dialog = new  Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_loading);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setCancelable(false);
        //dialog.getWindow().setLayout(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        //GIF
        pGif = (PlayGifView) dialog.findViewById(R.id.gif);
        pGif.setImageResource(R.drawable.cane);

        //TextView
        loading_dialog_textview = (TextView) dialog.findViewById(R.id.loading_text_dialog);
        dialog.show();

        // Loading
        new CountDownTimer(10000, 1000){
            @Override
            public void onTick(long l) {
                switch((int) (l/1000)){
                    case 8:
                        loading_dialog_textview.setText("Loading.");
                        break;
                    case 7:
                        loading_dialog_textview.setText("Loading..");
                        break;
                    case 6:
                        loading_dialog_textview.setText("Loading...");
                        break;
                    case 5:
                        loading_dialog_textview.setText("Loading.");
                        break;
                    case 4:
                        loading_dialog_textview.setText("Loading..");
                        break;
                    case 3:
                        loading_dialog_textview.setText("Loading...");
                        break;
                    case 2:
                        loading_dialog_textview.setText("Loading.");
                        break;
                    case 1:
                        loading_dialog_textview.setText("Loading..");
                        break;
                }
            }

            @Override
            public void onFinish() {
                if(sharedPreferences.getAll().containsKey("online")){
                    // Inseriamo il reloading della partita
                    dialog.dismiss();
                    Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                    startActivity(intent);
                } else {
                    dialog.dismiss();
                    LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.main_button_layout, null, false);

                    LinearLayout linear = (LinearLayout)findViewById(R.id.mainLayout);
                    linear.addView(layout);

                    //Button
                    creaProfiloButton = (Button) layout.findViewById(R.id.creaProfiloButton);
                    creaProfiloButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            intent = new Intent(MainActivity.this, CreaProfiloActivity.class);
                            intent.putExtra("Impostazioni", (Serializable) entity);
                            startActivity(intent);
                        }
                    });

                    loginButton = (Button) layout.findViewById(R.id.loginButton);
                    loginButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //fare controllo effetti se attivati musica play
                            MediaPlayer mp = MediaPlayer.create(MainActivity.this, R.raw.bottoni);
                            mp.start();
                            intent = new Intent(MainActivity.this, LoginActivity.class);
                            intent.putExtra("Impostazioni", (Serializable) entity);
                            startActivity(intent);
                        }
                    });
                }
            }
        }.start();
    }

    private boolean controlloFile(){
           return ReadBtn().equals("");
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

    private void configuraLingua(){
        Configuration config = new Configuration();
        if (entity.getLingua().equals("ita"))
                                      config.locale = Locale.ITALIAN ;
        else if(entity.getLingua().equals("eng"))
                                          config.locale = Locale.ENGLISH;
             else if (entity.getLingua().equals("ukr")){
                                             Locale locale = new Locale("uk");
                                             Locale.setDefault(locale);
                                             config.locale = locale;
                  }
        getResources().updateConfiguration(config, null);
    }


    private void WriteBtn(String lingua,String audio,String effetti,String vibrazione) {
        try {
            FileOutputStream fileout=openFileOutput("BizBong.txt", MODE_PRIVATE);
            OutputStreamWriter outputWriter=new OutputStreamWriter(fileout);
            outputWriter.write(lingua);
            outputWriter.write("-"+audio);
            outputWriter.write("-"+effetti);
            outputWriter.write("-"+vibrazione);
            outputWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
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
            if(mServ!=null) {
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
}
