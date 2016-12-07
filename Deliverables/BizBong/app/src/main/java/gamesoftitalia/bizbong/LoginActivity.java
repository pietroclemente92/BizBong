package gamesoftitalia.bizbong;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import gamesoftitalia.bizbong.connessione.CreaProfiloAsync;
import gamesoftitalia.bizbong.connessione.LoginAsync;

public class LoginActivity extends AppCompatActivity {

    private EditText nicknameEdit, passwordEdit;
    private TextView login;
    private String nickname, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

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
}
