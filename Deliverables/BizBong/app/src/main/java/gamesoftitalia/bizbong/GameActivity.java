package gamesoftitalia.bizbong;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import gamesoftitalia.bizbong.entity.BizBong;

/**
 * Created by GameSoftItalia on 21/12/2016.
 */

public class GameActivity extends AppCompatActivity {

    private BizBong bizBong;
    private TextView domanda;
    private Button[] risposte;
    private int turn = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        // BizBong
        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            bizBong = (BizBong) bundle.getSerializable("bizbong");
        }

        // TextView
        domanda = (TextView) findViewById(R.id.domanda);
        domanda.setText(bizBong.getListaDomande().get(turn).getDomanda());

        // Button
        risposte = new Button[4];
        for(int i = 0; i < 4; i++){
            String pathName = "@id/answer" + i;
            risposte[i] = (Button) findViewById(getResources().getIdentifier(pathName, null, getPackageName()));
            risposte[i].setText("MONDO");
            Log.d("DEBUG:", "Value-->"+bizBong.getListaDomande().get(turn).getListaRisposte().get(i).toString());
            //risposte[i].setText(bizBong.getListaDomande().get(turn).getListaRisposte().get(0).toString());
        }

        int finePartita = 16000 * 9;
        new CountDownTimer(finePartita, 16000){

            @Override
            public void onTick(long millisUntilFinished) {
                if(millisUntilFinished/16000 == turn){
                    domanda.setText(bizBong.getListaDomande().get(turn).getDomanda());
                    for(int i = 0; i < 4; i++){
                        Log.d("DEBUG:", "Value-->"+bizBong.getListaDomande().get(turn).getListaRisposte().get(i).toString());
                        //risposte[i].setText(bizBong.getListaDomande().get(turn).getListaRisposte().get(i).toString());
                    }
                }
                turn++;
            }

            @Override
            public void onFinish() {
                finish();
            }
        }.start();
    }

    @Override
    public void onBackPressed(){

    }
}
