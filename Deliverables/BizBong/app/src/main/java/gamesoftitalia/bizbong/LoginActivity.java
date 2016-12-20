package gamesoftitalia.bizbong;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import gamesoftitalia.bizbong.connessione.CreaProfiloAsync;
import gamesoftitalia.bizbong.connessione.LoginAsync;
import gamesoftitalia.bizbong.service.MusicServiceBase;

public class LoginActivity extends AppCompatActivity {

    private EditText nicknameEdit, passwordEdit;
    private TextView login;
    private String nickname, password;

    private boolean audioAssociato=true;     /*valore prende da file*/
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
        setContentView(R.layout.activity_login);

        //Music
        if (audioAssociato == true){
            music.setClass(this, MusicServiceBase.class);
            associareService();
            startService(music);
        }

        //EditText
        nicknameEdit = (EditText) findViewById(R.id.nicknameEdit);
        passwordEdit = (EditText) findViewById(R.id.passwordEdit);

        //Button
        login = (TextView) findViewById(R.id.registrazioneButton);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nickname = nicknameEdit.getText().toString();
                password = passwordEdit.getText().toString();
                new LoginAsync(LoginActivity.this).execute(nickname, password);
            }
        });
    }



    @Override
    protected void onResume() {     /*quando l'app viene riattivata*/
        super.onResume();
        if (audioAssociato==true)
            if(mServ!=null)
                mServ.resumeMusic();
    };

    @Override
    protected void onPause() {            /*quando l'app va in background viene stoppato*/
        if (audioAssociato==true)
            if(mServ!=null)
                mServ.pauseMusic();
        super.onPause();
    }


    //legare il servizio al contesto
    public void associareService(){
        bindService(music, Scon, Context.BIND_AUTO_CREATE);                    /*aggiungere il servizio*/
        audioAssociato = true;
    }
}