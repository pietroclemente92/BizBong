package gamesoftitalia.bizbong;

import android.app.Dialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;

import gamesoftitalia.bizbong.gifanimator.PlayGifView;
import gamesoftitalia.bizbong.service.MusicServiceBase;


public class MainActivity extends AppCompatActivity {

    private Button creaProfiloButton, loginButton, allenamentoButton;
    PlayGifView pGif;
    private Intent intent;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    LayoutInflater inflater;


    private boolean audioAssociato = true;  //prende valore dal file
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

        //Music
        if (audioAssociato == true){
            music.setClass(this, MusicServiceBase.class);
            associareService();
            startService(music);
        }

        // Layout
        inflater = LayoutInflater.from(MainActivity.this);

        //Shared
        sharedPreferences = getSharedPreferences("sessioneUtente", MainActivity.this.MODE_PRIVATE);
        editor = sharedPreferences.edit();

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
        dialog.show();

        // Loading
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(sharedPreferences.getAll().containsKey("game")){
                    //Inseriamo il reloading della partita
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
                            startActivity(intent);
                        }
                    });

                    allenamentoButton = (Button) layout.findViewById(R.id.allenamentoButton);
                    allenamentoButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //intent = new Intent(MainActivity.this, CreaProfiloActivity.class);
                            //startActivity(intent);
                        }
                    });
                }
            }
        }, 10000);
    }




    @Override
    protected void onResume() {
        super.onResume();
        if (audioAssociato==true)
            if(mServ!=null)
                mServ.resumeMusic();
    };

    @Override
    protected void onPause() {
        if (audioAssociato==true)
            if(mServ!=null)
                mServ.pauseMusic();
        super.onPause();
    }


    @Override
    protected void onDestroy() {
        mServ.stopMusic();
        stopService(music);
        disassocaiareService();
        super.onDestroy();
    };

    //legare il servizio al contesto
    public void associareService(){
        bindService(music, Scon, Context.BIND_AUTO_CREATE);
        audioAssociato = true;
    }

    //togliere il servizio al contesto
    public void disassocaiareService() {
        if(audioAssociato) {
            unbindService(Scon);
            audioAssociato = false;
        }
    }
}
