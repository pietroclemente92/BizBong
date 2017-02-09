package gamesoftitalia.bizbong;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import gamesoftitalia.bizbong.connessione.CreaProfiloAsync;
import gamesoftitalia.bizbong.connessione.LoginAsync;
import gamesoftitalia.bizbong.entity.Impostazioni;
import gamesoftitalia.bizbong.service.MusicServiceBase;

public class CreaProfiloActivity extends AppCompatActivity {

    private EditText nicknameEdit, passwordEdit, emailEdit;
    private TextView registrazione;
    private ImageButton backButton;
    private String nickname, password, email;
    private Impostazioni entity;

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

        entity= (Impostazioni) getIntent().getSerializableExtra("Impostazioni");     //ricevitore oggetto Impostazioni

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

                if(controlCreaProfilo())
                    new CreaProfiloAsync(CreaProfiloActivity.this).execute(nickname, password, email);
            }
        });
    }

    private boolean controlCreaProfilo(){
        boolean flagCreaProfilo = true;

        if(nickname.length() < 0 && password.length() < 0) {
            Toast.makeText(CreaProfiloActivity.this, "Error Empty: Non sono state inserite le credenziali opportune. Riprovare.", Toast.LENGTH_LONG).show();
            flagCreaProfilo = false;
        }
        if(nickname.length() > 16 && nickname.length() < 5) {
            Toast.makeText(CreaProfiloActivity.this, "Error Nickname: Il nickname inserito non ha lunghezza idonea(Min 5, Max 16).", Toast.LENGTH_LONG).show();
            flagCreaProfilo = false;
        }
        if(!email.matches("[a-zA-Z0-9._%-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}")) {
            Toast.makeText(CreaProfiloActivity.this, "Error Email: L' email inserita non è idonea.", Toast.LENGTH_LONG).show();
            flagCreaProfilo = false;
        }
        if(!password.matches("((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%_-]).{4,16})")) {
            Toast.makeText(CreaProfiloActivity.this, "Error Password: La password inserita non è idonea(deve contenere una lettera maiuscola, minuscola, numero e un carattaere alfanumerico).", Toast.LENGTH_LONG).show();
            flagCreaProfilo = false;
        }

        return flagCreaProfilo;
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
