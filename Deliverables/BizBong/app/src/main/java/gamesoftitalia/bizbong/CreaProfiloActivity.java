package gamesoftitalia.bizbong;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import gamesoftitalia.bizbong.connessione.CreaProfiloAsync;
import gamesoftitalia.bizbong.service.MusicServiceBase;

public class CreaProfiloActivity extends AppCompatActivity {

    private EditText nicknameEdit, passwordEdit, emailEdit;
    private TextView registrazione;
    private ImageButton backButton;
    private String nickname, password, email;

    private boolean audioAssociato=false;     /*valore prende da file*/
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
        setContentView(R.layout.activity_crea_profilo);

        //Music
        if (audioAssociato == true){
            music.setClass(this, MusicServiceBase.class);
            associareService();
            startService(music);
        }

        //EditText
        nicknameEdit = (EditText) findViewById(R.id.nicknameEdit);
        passwordEdit = (EditText) findViewById(R.id.passwordEdit);
        emailEdit = (EditText) findViewById(R.id.emailEdit);

        //Button
        backButton = (ImageButton) findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreaProfiloActivity.super.onBackPressed();
            }
        });
        registrazione = (TextView) findViewById(R.id.registrazioneButton);
        registrazione.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nickname = nicknameEdit.getText().toString();
                password = passwordEdit.getText().toString();
                email = emailEdit.getText().toString();
                new CreaProfiloAsync(CreaProfiloActivity.this).execute(nickname, password, email);
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

    @Override
    public void onBackPressed() {

    }

    //legare il servizio al contesto
    public void associareService(){
        bindService(music, Scon, Context.BIND_AUTO_CREATE);                    /*aggiungere il servizio*/
        audioAssociato = true;
    }
}
