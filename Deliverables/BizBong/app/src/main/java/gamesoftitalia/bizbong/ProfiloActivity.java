package gamesoftitalia.bizbong;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.concurrent.ExecutionException;

import gamesoftitalia.bizbong.connessione.ModificaProfiloAsync;
import gamesoftitalia.bizbong.connessione.ProfiloAsync;
import gamesoftitalia.bizbong.entity.Profilo;
import gamesoftitalia.bizbong.fragment.ProfiloFragment;

public class ProfiloActivity extends AppCompatActivity {

    private static TextView nicknameText, emailText;
    private Context context = ProfiloActivity.this;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private ImageButton backButton;

    private EditText modificaNicknameTextView, modificaEmailTextView, modificaPasswordTextView, confermaPasswordTextView;
    private Button modificaButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profilo);

        //Shared
        sharedPreferences = getSharedPreferences("sessioneUtente", ProfiloActivity.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        // ImageButton
        backButton = (ImageButton) findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProfiloActivity.super.onBackPressed();
                finish();
            }
        });

        // Profilo
        final String nicknameSession = sharedPreferences.getAll().get("nickname").toString();
        String profiloGson = null;
        try {
            profiloGson = new ProfiloAsync(context).execute(nicknameSession).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        final Profilo profilo = new Gson().fromJson(profiloGson, Profilo.class);
        // Log.d("DEBUG:", "Value-->"+profilo.getStatistiche().getPunteggiList()[0]);


        // ProfiloText
        nicknameText = (TextView) findViewById(R.id.nicknameProfilo);
        nicknameText.setText("Nickname: "+profilo.getNickname());
        emailText = (TextView) findViewById(R.id.emailProfilo);
        emailText.setText("Email: "+profilo.getEmail());

        // TextView
        modificaNicknameTextView = (EditText) findViewById(R.id.modificaNickname);
        modificaNicknameTextView.setHint(profilo.getNickname());
        modificaEmailTextView = (EditText) findViewById(R.id.modificaEmail);
        modificaEmailTextView.setHint(profilo.getEmail());
        modificaPasswordTextView = (EditText) findViewById(R.id.modificaPassword);
        confermaPasswordTextView = (EditText) findViewById(R.id.confermaPassword);

        // Button
        modificaButton = (Button) findViewById(R.id.modificaButton);
        modificaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tmpModificaEmail = modificaEmailTextView.getText().toString();
                String tmpModificaPassword = modificaPasswordTextView.getText().toString();
                String tmpConfermaPassword = confermaPasswordTextView.getText().toString();

                if(tmpModificaEmail.length() > 0) {
                    profilo.setEmail(tmpModificaEmail);
                    ricaricaProfilo(tmpModificaEmail);
                }
                if(tmpModificaPassword.length() > 0 && tmpModificaPassword.equals(tmpConfermaPassword))
                    profilo.setPassword(tmpModificaPassword);

                String modificaProfilo = new Gson().toJson(profilo, Profilo.class);
                // Log.d("DEBUG:", "GSON-->"+modificaProfilo);
                new ModificaProfiloAsync(context).execute(modificaProfilo);
            }
        });
    }

    @Override
    public void onBackPressed() {

    }

    private void ricaricaProfilo(String newEmail){
        emailText.setText("Email: "+newEmail);
        modificaEmailTextView.setHint(newEmail);
    }
}