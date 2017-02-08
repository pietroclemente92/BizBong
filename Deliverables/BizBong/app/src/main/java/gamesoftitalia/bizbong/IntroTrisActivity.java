package gamesoftitalia.bizbong;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Random;

import gamesoftitalia.bizbong.gifanimator.PlayGifView;

public class IntroTrisActivity extends AppCompatActivity {
    private int scelta_utente;
    private String modalita;
    private int s=0;



    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private String nickname;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro_tris);

        //Shared
        sharedPreferences = getSharedPreferences("sessioneUtente", ProfiloActivity.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        nickname = sharedPreferences.getAll().get("nickname").toString();
        


        modalita=getIntent().getExtras().getString("modalita");
        intro_partita();





    }

    private void intro_partita(){
        TextView titolo=(TextView)findViewById(R.id.titolo);
        ImageButton biz=(ImageButton)findViewById(R.id.biz);
        ImageButton bong=(ImageButton)findViewById(R.id.bong);
        titolo.setText(nickname+" scegli una faccia della moneta per stabilire chi inzia a giocare");

        biz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scelta_utente=0;
                timer_gif();
                //disabilitare clic una volta premuti entrambi i tati
            }
        });

        bong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scelta_utente=1;
                timer_gif();
            }
        });

    }


    private void timer_gif(){
        final PlayGifView gif=(PlayGifView) findViewById(R.id.gif);
        final RelativeLayout lancio=(RelativeLayout)findViewById(R.id.lancio);
        gif.setVisibility(View.VISIBLE);
        gif.setImageResource(R.drawable.lancio_moneta);
        new CountDownTimer(6500, 1000) {
            @Override
            public void onTick(long l) {
                if (l/1000==4) {
                    gif.setVisibility(View.GONE);
                    lancio.setVisibility(View.VISIBLE);
                    Random random = new Random();
                    int d = random.nextInt(2);
                    ImageView moneta=(ImageView)findViewById(R.id.moneta);
                    String pathName;
                    if(d==0) {
                        /*visualizzare immagine moneta faccia biz*/
                        pathName = "@drawable/monetabiz";
                        moneta.setImageResource(getResources().getIdentifier(pathName, null, getPackageName()));
                    }
                    else {
                     /* visualizzare immagine moneta faccia bong*/
                        pathName = "@drawable/monetabong";
                        moneta.setImageResource(getResources().getIdentifier(pathName, null, getPackageName()));
                    }

                    TextView inzioTurno=(TextView)findViewById(R.id.inzioturno);
                    if(d==scelta_utente) {
                            if (modalita.equals("3"))
                                                   s=0;
                            inzioTurno.setText("Inzia a giocare " + nickname + "   Attendi...");
                    }else if (modalita.equals("3")) {
                                    inzioTurno.setText("Inzia a giocare l'ospite   Attendi...");
                                    s=1;
                          }else{
                             inzioTurno.setText("Inzia a giocare BizBong   Attendi...");
                             modalita="2";
                          }
                }

            }

            @Override
            public void onFinish() {
                LinearLayout testo=(LinearLayout)findViewById(R.id.testo);
                LinearLayout scelta_moneta=(LinearLayout)findViewById(R.id.scelta_moneta);
                LinearLayout testo2=(LinearLayout)findViewById(R.id.testo2);
                lancio.setVisibility(View.GONE);
                testo.setVisibility(View.GONE);
                scelta_moneta.setVisibility(View.GONE);
                testo2.setVisibility(View.GONE);

                Intent openPage= new Intent(IntroTrisActivity.this,GameTrisActivity.class);
                openPage.putExtra("modalita", modalita);
                openPage.putExtra("s", s);
                startActivity(openPage);
            }
        }.start();
    }



}


