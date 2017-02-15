package gamesoftitalia.bizbong;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.concurrent.ExecutionException;

import gamesoftitalia.bizbong.connessione.ModificaStatisticheAsync;
import gamesoftitalia.bizbong.connessione.ProfiloAsync;
import gamesoftitalia.bizbong.entity.BizBong;
import gamesoftitalia.bizbong.entity.Profilo;
import gamesoftitalia.bizbong.entity.Statistiche;

/**
 * Created by GameSoftItalia on 21/12/2016.
 */

public class BizBongGameActivity extends AppCompatActivity {

    private BizBong bizBong;
    private TextView domanda, time, punteggioTextView;
    private Button[] risposte;
    private int turno;
    private int punteggio;
    private long tempoCorrente;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private String nickname;

    private String profiloGson;
    private Profilo profilo;
    private Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bizbong_game);

        // SharedPrefernces
        sharedPreferences = this.getSharedPreferences("sessioneUtente", this.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        if(sharedPreferences.getAll().containsKey("nickname"))
            nickname = sharedPreferences.getAll().get("nickname").toString();

        try {
            profiloGson = new ProfiloAsync(BizBongGameActivity.this).execute(nickname).get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        profilo = new Gson().fromJson(profiloGson, Profilo.class);
        // Log.d("DEBUG1:", "Modalita->"+profilo.getStatistiche().getModalitaList().length+", Punteggio->"+profilo.getStatistiche().getPunteggiList().length);

        // Game's Variables
        punteggio = 0;
        turno = 0;

        // BizBong
        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            bizBong = (BizBong) bundle.getSerializable("bizbong");
        }

        // TextView
        domanda = (TextView) findViewById(R.id.domanda);
        domanda.setText(bizBong.getListaDomande().get(turno).getDomanda());
        time = (TextView) findViewById(R.id.time);
        tempoCorrente = 17;
        time.setText(String.valueOf(tempoCorrente));
        punteggioTextView = (TextView) findViewById(R.id.punteggio);

        // Button
        risposte = new Button[4];
        for(int i = 0; i < 4; i++){
            int indexAnswer = i+1;
            final int tmp = i;
            String pathName = "@id/answer" + indexAnswer;
            //Log.d("DEBUG:", "PathName-->"+pathName+", ID1-->"+getResources().getIdentifier(pathName, null, getPackageName())+", ID2-->"+R.id.answer1);
            risposte[tmp] = (Button) findViewById(getResources().getIdentifier(pathName, null, getPackageName()));
            risposte[tmp].setText(bizBong.getListaDomande().get(turno).getListaRisposte().get(tmp).toString());
            risposte[tmp].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(bizBong.getListaDomande().get(turno).getRispostaVera().equals(risposte[tmp].getText().toString())) {
                        punteggio += bizBong.getListaDomande().get(turno).getPunteggio();
                        punteggioTextView.setText(getResources().getString(R.string.punteggio2)+" "+punteggio);
                    }
                    for(int j = 0; j < 4; j++){
                        if(bizBong.getListaDomande().get(turno).getRispostaVera().equals(risposte[j].getText().toString())) {
                            risposte[j].setBackgroundResource(R.drawable.button_modalita_true);
                            risposte[j].setOnClickListener(null);
                        }
                    }
                    mHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            setNuovoTurno();
                        }
                    }, 1000);
                }
            });
        }

        // Thread di gioco
        mHandler = new Handler();
        new Thread(new Runnable() {
            @Override
            public void run() {
                // TODO Auto-generated method stub
                while (turno < 9) {
                    try {
                        Thread.sleep(1000);
                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                // TODO Auto-generated method stub
                                if (tempoCorrente == 1) {
                                    for(int j = 0; j < 4; j++){
                                        if(bizBong.getListaDomande().get(turno).getRispostaVera().equals(risposte[j].getText().toString())) {
                                            risposte[j].setBackgroundResource(R.drawable.button_modalita_true);
                                            risposte[j].setOnClickListener(null);
                                        }
                                    }
                                    try{
                                        Thread.sleep(1000);
                                        mHandler.post(new Runnable() {
                                            @Override
                                            public void run() {
                                                setNuovoTurno();
                                            }
                                        });
                                    } catch (Exception e){
                                        //  TODO: handle exception
                                    }
                                } else {
                                    tempoCorrente--;
                                    time.setText(String.valueOf(tempoCorrente));
                                }
                            }
                        });
                    } catch (Exception e) {
                        // TODO: handle exception
                    }
                }
            }
        }).start();
    }

    @Override
    public void onBackPressed(){

    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        finish();
    }


    private void setNuovoTurno() {
        if(turno < 9){
            tempoCorrente = 16;
            time.setText(String.valueOf(tempoCorrente));
            turno++;
            domanda.setText(bizBong.getListaDomande().get(turno).getDomanda());
            for(int i = 0; i < 4; i++){
                final int tmp = i;
                risposte[tmp].setBackgroundResource(R.drawable.buttonshape);
                risposte[tmp].setText(bizBong.getListaDomande().get(turno).getListaRisposte().get(i).toString());
                risposte[tmp].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(bizBong.getListaDomande().get(turno).getRispostaVera().equals(risposte[tmp].getText().toString())) {
                            punteggio += bizBong.getListaDomande().get(turno).getPunteggio();
                            punteggioTextView.setText(getResources().getString(R.string.punteggio2)+" "+punteggio);

                            // Aggiungi punti a statistiche
                            Statistiche statistiche =  profilo.getStatistiche();
                            for(int j = 0; j < statistiche.getModalitaList().length; j++){
                                //Log.d("DEBUG:", "BIZBONG.MODALITA->"+bizBong.getModalita()+ "; BIZBONG.Tema->"+bizBong.getListaDomande().get(turno).getTema()+"; STATISTICHE->"+statistiche.getModalitaList()[j]);
                                if(bizBong.getListaDomande().get(turno).getTema().equals(statistiche.getModalitaList()[j]))
                                    profilo.getStatistiche().getPunteggiList()[j] += bizBong.getListaDomande().get(turno).getPunteggio();
                                if(bizBong.getModalita().equals(statistiche.getModalitaList()[j]))
                                    profilo.getStatistiche().getPunteggiList()[j] += bizBong.getListaDomande().get(turno).getPunteggio();
                            }
                        }
                        for(int j = 0; j < 4; j++){
                            if(bizBong.getListaDomande().get(turno).getRispostaVera().equals(risposte[j].getText().toString())) {
                                risposte[j].setBackgroundResource(R.drawable.button_modalita_true);
                                risposte[j].setOnClickListener(null);
                            }
                        }
                        mHandler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                setNuovoTurno();
                            }
                        }, 1000);
                    }
                });
            }
        } else{
            AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
            LayoutInflater inflater = this.getLayoutInflater();
            View dialogView = inflater.inflate(R.layout.congratulazioni_dialog, null);
            dialogBuilder.setView(dialogView);

            // Dialog Views
            TextView congratulazioniText = (TextView) dialogView.findViewById(R.id.congratulazioniText);
            congratulazioniText.setText(getResources().getString(R.string.congratulazioni)+" "+ nickname.substring(0, 1).toUpperCase() + nickname.substring(1));

            // Punteggio Ottenuto
            Log.d("DEBUG2:", "Modalita->"+profilo.getStatistiche().getModalitaList().length+", Punteggio->"+profilo.getStatistiche().getPunteggiList().length);

            TextView punteggioText = (TextView) dialogView.findViewById(R.id.punteggioText);
            punteggioText.setText(getResources().getString(R.string.punteggio2)+" "+punteggio);

            // AggiornaStatistiche
            String aggiornaProfiloGson = new Gson().toJson(profilo, Profilo.class);
            new ModificaStatisticheAsync(BizBongGameActivity.this).execute(aggiornaProfiloGson);

            // Button Exit
            Button avantiButton = (Button) dialogView.findViewById(R.id.avantiButton);
            avantiButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(BizBongGameActivity.this, HomeActivity.class);
                    startActivity(intent);
                    finish();
                }
            });
            AlertDialog alertDialog = dialogBuilder.create();
            alertDialog.show();
        }
    }
}
