package gamesoftitalia.bizbong;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.Locale;

import gamesoftitalia.bizbong.connessione.BizBongAsync;
import gamesoftitalia.bizbong.entity.Impostazioni;

public class NuovaPartitaActivity extends AppCompatActivity {

    private int position = 0;
    private ImageButton backButton;
    private Button giocaAdessoButton;
    private String modalita;
    private Intent intent;
    private Impostazioni entity;


    // BizBong
    private int indexClassica = 0;
    private int indexMultipla = 0;
    private Button classicaButton, challengeButton;

    //Sudoku 2X2
    private int index2x2f = 0;
    private int index2x2m = 0;
    private int index2x2d = 0;
    private Button facile2X2, medio2X2, difficile2X2;

    //Sudoku 3X3
    private int index3x3f = 0;
    private int index3x3m = 0;
    private int index3x3d = 0;
    private Button facile3X3, medio3X3, difficile3X3;



    //Tris
    private int indexMulti=0;
    private int indexSingle=0;
    private Button singleButton, multiButton;


    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        entity= (Impostazioni) getIntent().getSerializableExtra("Impostazioni");     //ricevitore oggetto Impostazioni

        Log.d("aaa","-->"+entity.getLingua());

        //Shared
        sharedPreferences = getSharedPreferences("sessioneUtente", ProfiloActivity.MODE_PRIVATE);
        editor = sharedPreferences.edit();
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
                            modalita = "challenge";
                            indexMultipla = 1;
                            challengeButton.setBackgroundResource(R.drawable.button_modalita_true);
                        }
                    }
                });
                giocaAdessoButton = (Button) findViewById(R.id.giocaAdesso);
                giocaAdessoButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(indexClassica == 1 || indexMultipla == 1){
                            //if (Locale.getDefault().getISO3Language()=="ita")
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
                facile2X2 = (Button) findViewById(R.id.facile2x2Button);
                facile2X2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(index2x2m==1){
                            index2x2m=0;
                            medio2X2.setBackgroundResource(R.drawable.buttonshape);
                        }
                        if(index2x2d == 1) {
                            index2x2d = 0;
                            difficile2X2.setBackgroundResource(R.drawable.buttonshape);
                        }
                        if(index3x3f==1){
                            index3x3f=0;
                            facile3X3.setBackgroundResource(R.drawable.buttonshape);
                        }
                        if(index3x3m==1){
                            index3x3m=0;
                            medio3X3.setBackgroundResource(R.drawable.buttonshape);
                        }
                        if(index3x3d == 1) {
                            index3x3d = 0;
                            difficile3X3.setBackgroundResource(R.drawable.buttonshape);
                        }

                        if(index2x2f==1){
                            index2x2f=0;
                            facile2X2.setBackgroundResource(R.drawable.buttonshape);
                        }
                        else{
                            modalita = "2x2f";
                            index2x2f=1;
                            facile2X2.setBackgroundResource(R.drawable.button_modalita_true);
                        }
                    }
                });

                medio2X2 = (Button) findViewById(R.id.medio2x2Button);
                medio2X2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(index2x2f==1){
                            index2x2f=0;
                            facile2X2.setBackgroundResource(R.drawable.buttonshape);
                        }
                        if(index2x2d == 1) {
                            index2x2d = 0;
                            difficile2X2.setBackgroundResource(R.drawable.buttonshape);
                        }
                        if(index3x3f==1){
                            index3x3f=0;
                            facile3X3.setBackgroundResource(R.drawable.buttonshape);
                        }
                        if(index3x3m==1){
                            index3x3m=0;
                            medio3X3.setBackgroundResource(R.drawable.buttonshape);
                        }
                        if(index3x3d == 1) {
                            index3x3d = 0;
                            difficile3X3.setBackgroundResource(R.drawable.buttonshape);
                        }

                        if(index2x2m==1){
                            index2x2m=0;
                            medio2X2.setBackgroundResource(R.drawable.buttonshape);
                        }
                        else {
                            modalita = "2x2m";
                            index2x2m=1;
                            medio2X2.setBackgroundResource(R.drawable.button_modalita_true);
                        }
                    }
                });

                difficile2X2 = (Button) findViewById(R.id.difficile2x2Button);
                difficile2X2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(index2x2f==1){
                            index2x2f=0;
                            facile2X2.setBackgroundResource(R.drawable.buttonshape);
                        }
                        if(index2x2m == 1) {
                            index2x2m = 0;
                            medio2X2.setBackgroundResource(R.drawable.buttonshape);
                        }
                        if(index3x3f==1){
                            index3x3f=0;
                            facile3X3.setBackgroundResource(R.drawable.buttonshape);
                        }
                        if(index3x3m==1){
                            index3x3m=0;
                            medio3X3.setBackgroundResource(R.drawable.buttonshape);
                        }
                        if(index3x3d == 1) {
                            index3x3d = 0;
                            difficile3X3.setBackgroundResource(R.drawable.buttonshape);
                        }

                        if(index2x2d==1){
                            index2x2d=0;
                            difficile2X2.setBackgroundResource(R.drawable.buttonshape);
                        }
                        else {
                            modalita = "2x2d";
                            index2x2d=1;
                            difficile2X2.setBackgroundResource(R.drawable.button_modalita_true);
                        }
                    }
                });


                facile3X3 = (Button) findViewById(R.id.facile3x3Button);
                facile3X3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(index3x3m ==1){
                            index3x3m=0;
                            medio3X3.setBackgroundResource(R.drawable.buttonshape);
                        }
                        if(index3x3d == 1) {
                            index3x3d = 0;
                            difficile3X3.setBackgroundResource(R.drawable.buttonshape);
                        }
                        if(index2x2f==1){
                            index2x2f=0;
                            facile2X2.setBackgroundResource(R.drawable.buttonshape);
                        }
                        if(index2x2m==1){
                            index2x2m=0;
                            medio2X2.setBackgroundResource(R.drawable.buttonshape);
                        }
                        if(index2x2d == 1) {
                            index2x2d = 0;
                            difficile2X2.setBackgroundResource(R.drawable.buttonshape);
                        }

                        if(index3x3f==1){
                            index3x3f=0;
                            facile3X3.setBackgroundResource(R.drawable.buttonshape);
                        }
                        else{
                            modalita = "3x3f";
                            index3x3f=1;
                            facile3X3.setBackgroundResource(R.drawable.button_modalita_true);
                        }
                    }
                });

                medio3X3 = (Button) findViewById(R.id.medio3x3Button);
                medio3X3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(index3x3f==1){
                            index3x3f=0;
                            facile3X3.setBackgroundResource(R.drawable.buttonshape);
                        }
                        if(index3x3d == 1) {
                            index3x3d = 0;
                            difficile3X3.setBackgroundResource(R.drawable.buttonshape);
                        }
                        if(index2x2f==1){
                            index2x2f=0;
                            facile2X2.setBackgroundResource(R.drawable.buttonshape);
                        }
                        if(index2x2m==1){
                            index2x2m=0;
                            medio2X2.setBackgroundResource(R.drawable.buttonshape);
                        }
                        if(index2x2d == 1) {
                            index2x2d = 0;
                            difficile2X2.setBackgroundResource(R.drawable.buttonshape);
                        }

                        if(index3x3m==1){
                            index3x3m=0;
                            medio3X3.setBackgroundResource(R.drawable.buttonshape);
                        }
                        else {
                            modalita = "3x3m";
                            index3x3m=1;
                            medio3X3.setBackgroundResource(R.drawable.button_modalita_true);
                        }
                    }
                });

                difficile3X3 = (Button) findViewById(R.id.difficile3x3Button);
                difficile3X3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(index3x3f==1){
                            index3x3f=0;
                            facile3X3.setBackgroundResource(R.drawable.buttonshape);
                        }
                        if(index3x3m == 1) {
                            index3x3m = 0;
                            medio3X3.setBackgroundResource(R.drawable.buttonshape);
                        }
                        if(index2x2f==1){
                            index2x2f=0;
                            facile2X2.setBackgroundResource(R.drawable.buttonshape);
                        }
                        if(index2x2m==1){
                            index2x2m=0;
                            medio2X2.setBackgroundResource(R.drawable.buttonshape);
                        }
                        if(index2x2d == 1) {
                            index2x2d = 0;
                            difficile2X2.setBackgroundResource(R.drawable.buttonshape);
                        }

                        if(index3x3d==1){
                            index3x3d=0;
                            difficile3X3.setBackgroundResource(R.drawable.buttonshape);
                        }
                        else {
                            modalita = "3x3d";
                            index3x3d=1;
                            difficile3X3.setBackgroundResource(R.drawable.button_modalita_true);
                        }
                    }
                });



                giocaAdessoButton = (Button) findViewById(R.id.giocaAdesso);
                giocaAdessoButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (index2x2f==1||index2x2m==1||index2x2d==1||index3x3f==1||index3x3m==1||index3x3d==1) {
                            intent = new Intent(NuovaPartitaActivity.this, GameSudoBizBongActivity.class);
                            intent.putExtra("modalita", modalita);
                            startActivity(intent);
                        }else{
                            Toast.makeText(NuovaPartitaActivity.this, "Selezionare una delle modalità proposte per avviare la partita!", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
                break;

            case 2: //tris
                setContentView(R.layout.activity_nuova_partita_tris);
                final String nickname = sharedPreferences.getAll().get("nickname").toString();

                //Button
                singleButton = (Button) findViewById(R.id.singleButton);
                singleButton.setText(nickname+" Vs BizBong");
                singleButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (indexMulti==1){
                            indexMulti=0;
                            multiButton.setBackgroundResource(R.drawable.buttonshape);
                        }
                        if(indexSingle==1){
                            indexSingle=0;
                            singleButton.setBackgroundResource(R.drawable.buttonshape);
                        }
                        else{
                            indexSingle=1;
                            modalita="1";
                            singleButton.setBackgroundResource(R.drawable.button_modalita_true);
                        }
                    }
                });

                multiButton = (Button) findViewById(R.id.multiButton);
                multiButton.setText(nickname+" Vs Ospite");
                multiButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (indexSingle==1){
                            indexSingle=0;
                            singleButton.setBackgroundResource(R.drawable.buttonshape);
                        }
                        if(indexMulti==1){
                            indexMulti=0;
                            multiButton.setBackgroundResource(R.drawable.buttonshape);
                        }
                        else{
                            indexMulti=1;
                            modalita="3";
                            multiButton.setBackgroundResource(R.drawable.button_modalita_true);
                        }
                    }
                });

                giocaAdessoButton = (Button) findViewById(R.id.giocaAdesso);
                giocaAdessoButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(indexMulti==1||indexSingle==1) {
                            intent = new Intent(NuovaPartitaActivity.this, IntroTrisActivity.class);
                            intent.putExtra("modalita", modalita);
                            startActivity(intent);
                        } else{
                             Toast.makeText(NuovaPartitaActivity.this, "Selezionare una delle modalità proposte per avviare la partita!", Toast.LENGTH_SHORT).show();
                        }
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
