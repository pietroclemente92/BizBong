package gamesoftitalia.bizbong;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import gamesoftitalia.bizbong.fragment.ProfiloFragment;

public class ProfiloActivity extends AppCompatActivity {

    private static TextView nicknameText, emailText;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private ImageButton backButton;
    private Button modificaButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profilo);

        // ImageButton
        backButton = (ImageButton) findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProfiloActivity.super.onBackPressed();
                finish();
            }
        });

        //Shared
        sharedPreferences = getSharedPreferences("sessioneUtente", ProfiloActivity.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        // String
        nicknameText = (TextView) findViewById(R.id.nicknameProfilo);
        if(sharedPreferences.getAll().containsKey("nickname"))
            nicknameText.setText("Session:"+sharedPreferences.getAll().get("nickname").toString());

        // Button
        modificaButton = (Button) findViewById(R.id.modificaButton);
        modificaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tmpModificaNickname ;
            }
        });
    }

    @Override
    public void onBackPressed() {

    }
}
