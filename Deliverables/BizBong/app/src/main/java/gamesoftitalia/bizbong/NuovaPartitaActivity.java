package gamesoftitalia.bizbong;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import gamesoftitalia.bizbong.connessione.BizBongAsync;

public class NuovaPartitaActivity extends AppCompatActivity {

    private int position = 0;
    private ImageButton backButton;
    private Button giocaAdessoButton;
    private String modalita;
    private Intent intent;

    // BizBong
    private int indexClassica = 0;
    private int indexMultipla = 0;
    private Button classicaButton, challengeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_nuova_partita_bizbong);

        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            position = bundle.getInt("position");
            //setContentView(R.layout.activity_nuova_partita);
        }

        // Scelta del layout e del gioco
        switch(position){
            case 0:  //BizBongMode
                setContentView(R.layout.activity_nuova_partita_bizbong);

                // Button
                classicaButton = (Button) findViewById(R.id.classicaButton);
                classicaButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(indexMultipla == 1) {
                            indexMultipla = 0;
                            challengeButton.setBackgroundResource(R.drawable.buttonshape);
                        }
                        if(indexClassica == 1) {
                            indexClassica = 0;
                            classicaButton.setBackgroundResource(R.drawable.buttonshape);
                        } else{
                            modalita = "classica";
                            indexClassica = 1;
                            classicaButton.setBackgroundResource(R.drawable.button_modalita_true);
                        }
                    }
                });
                challengeButton = (Button) findViewById(R.id.challengeButton);
                challengeButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(indexClassica == 1){
                            indexClassica = 0;
                            classicaButton.setBackgroundResource(R.drawable.buttonshape);
                        }
                        if(indexMultipla == 1) {
                            indexMultipla = 0;
                            challengeButton.setBackgroundResource(R.drawable.buttonshape);
                        } else{
                            modalita = "Challenge";
                            indexMultipla = 1;
                            challengeButton.setBackgroundResource(R.drawable.button_modalita_true);
                        }
                    }
                });
                giocaAdessoButton = (Button) findViewById(R.id.giocaAdesso);
                giocaAdessoButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(indexClassica == 1 || indexClassica == 1){
                            new BizBongAsync(NuovaPartitaActivity.this).execute(modalita);
                        } else{
                            Toast.makeText(NuovaPartitaActivity.this, "Selezionare una delle modalità proposte per avviare la partita!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                break;
            case 1:
                setContentView(R.layout.activity_nuova_partita_sudoku);

                //Button
                final Button facile2X2 = (Button) findViewById(R.id.facile2x2Button);
                facile2X2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        modalita = "2x2f";
                        facile2X2.setBackgroundResource(R.drawable.button_modalita_true);
                    }
                });

                final Button medio2X2 = (Button) findViewById(R.id.medio2x2Button);
                medio2X2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        modalita = "2x2m";
                        medio2X2.setBackgroundResource(R.drawable.button_modalita_true);
                    }
                });

                final Button difficile2X2 = (Button) findViewById(R.id.difficile2x2Button);
                difficile2X2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        modalita = "2x2d";
                        difficile2X2.setBackgroundResource(R.drawable.button_modalita_true);
                    }
                });


                final Button facile3X3 = (Button) findViewById(R.id.facile3x3Button);



                final Button medio3X3 = (Button) findViewById(R.id.medio3x3Button);


                final Button difficile3X3 = (Button) findViewById(R.id.difficile3x3Button);



                giocaAdessoButton = (Button) findViewById(R.id.giocaAdesso);
                giocaAdessoButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        intent = new Intent(NuovaPartitaActivity.this, GameSudoBizBongActivity.class);
                        intent.putExtra("modalita", modalita);
                        startActivity(intent);
                    }
                });
                break;
        }

        // Button
        backButton = (ImageButton) findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NuovaPartitaActivity.super.onBackPressed();
            }
        });
    }

    @Override
    public void onBackPressed() {

    }
}
