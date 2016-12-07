package gamesoftitalia.bizbong;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import gamesoftitalia.bizbong.connessione.CreaProfiloAsync;

public class CreaProfilo extends AppCompatActivity {

    private EditText nicknameEdit, passwordEdit, emailEdit;
    private TextView registrazione;
    private String nickname, password, email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crea_profilo);

        //EditText
        nicknameEdit = (EditText) findViewById(R.id.nicknameEdit);
        passwordEdit = (EditText) findViewById(R.id.passwordEdit);
        emailEdit = (EditText) findViewById(R.id.emailEdit);

        //Button
        registrazione = (TextView) findViewById(R.id.registrazioneButton);
        registrazione.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nickname = nicknameEdit.getText().toString();
                password = passwordEdit.getText().toString();
                email = emailEdit.getText().toString();
                new CreaProfiloAsync(CreaProfilo.this).execute(nickname, password, email);
            }
        });
    }
}
