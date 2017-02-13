package gamesoftitalia.bizbong;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.IBinder;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import gamesoftitalia.bizbong.connessione.CreaProfiloAsync;
import gamesoftitalia.bizbong.connessione.LoginAsync;
import gamesoftitalia.bizbong.entity.Impostazioni;
import gamesoftitalia.bizbong.service.MusicServiceBase;

public class LoginActivity extends AppCompatActivity {

    private EditText nicknameEdit, passwordEdit;
    private TextView login;
    private ImageButton backButton;
    private String nickname, password;
    private Impostazioni entity;

    private Intent music=new Intent();
    private boolean audioAssociato;
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
        setContentView(R.layout.activity_login);
        entity= (Impostazioni) getIntent().getSerializableExtra("Impostazioni");     //ricevitore oggetto Impostazioni

        //Music
        audioAssociato=entity.getAudio();
        if (audioAssociato){
            music.setClass(this, MusicServiceBase.class);
            bindService(music, Scon, Context.BIND_AUTO_CREATE);    //legare il servizio al contesto
            startService(music);
        }

        //EditText
        nicknameEdit = (EditText) findViewById(R.id.nicknameEdit);
        passwordEdit = (EditText) findViewById(R.id.passwordEdit);

        //Button
        backButton = (ImageButton) findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(entity.getEffetti()==true) {
                    MediaPlayer mp = MediaPlayer.create(LoginActivity.this, R.raw.bottoni);
                    mp.start();
                }
                if(entity.getVibrazione()){
                    Vibrator g = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    g.vibrate(100);
                }
                LoginActivity.super.onBackPressed();
            }
        });
        login = (TextView) findViewById(R.id.registrazioneButton);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(entity.getEffetti()==true) {
                    MediaPlayer mp = MediaPlayer.create(LoginActivity.this, R.raw.bottoni);
                    mp.start();
                }
                if(entity.getVibrazione()){
                    Vibrator g = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    g.vibrate(100);
                }
                nickname = nicknameEdit.getText().toString();
                password = passwordEdit.getText().toString();

                if(nickname.length() > 0 && password.length() > 0) {
                    new LoginAsync(LoginActivity.this).execute(nickname, password);
                }
            }
        });
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
