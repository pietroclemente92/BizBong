package gamesoftitalia.bizbong;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.Random;

public class GameTrisActivity extends AppCompatActivity {

    private int player,computer,singolo,r;
    private int turno=0;
    private int[]x=new int [9];
    private String modalita;
    private MyCount counter = new MyCount(500,1000);

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private String nickname;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_tris);

        //Shared
        sharedPreferences = getSharedPreferences("sessioneUtente", ProfiloActivity.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        nickname = sharedPreferences.getAll().get("nickname").toString();

        modalita=getIntent().getExtras().getString("modalita");
        singolo=getIntent().getExtras().getInt("s");
        inizzializza();
        Random random = new Random();
        r=random.nextInt(8);
        TextView mano=(TextView)findViewById(R.id.textView1);
        if(modalita.equals("1")){
            creacampo();
            player=1;
            computer=0;
            mano.setText("Turno "+nickname+" fai la tua mossa...");
        }
        if(modalita.equals("2")){
            creacampo();
            player=0;
            computer=1;
            mano.setText("Attendere turno BizBong...");
            gioco_computer();
        }
        if (modalita.equals("3")){
            creacampo();
            if (singolo==0){
                mano.setText("Turno "+nickname+" fai la tua mossa...");
            }
            else{
                mano.setText("Turno ospite fai la tua mossa...");
            }
        }

        ImageButton b1=(ImageButton)findViewById(R.id.imageButton1);
        b1.setOnClickListener(new Click1());
        ImageButton b2=(ImageButton)findViewById(R.id.imageButton2);
        b2.setOnClickListener(new Click2());
        ImageButton b3=(ImageButton)findViewById(R.id.imageButton3);
        b3.setOnClickListener(new Click3());
        ImageButton b4=(ImageButton)findViewById(R.id.imageButton4);
        b4.setOnClickListener(new Click4());
        ImageButton b5=(ImageButton)findViewById(R.id.imageButton5);
        b5.setOnClickListener(new Click5());
        ImageButton b6=(ImageButton)findViewById(R.id.imageButton6);
        b6.setOnClickListener(new Click6());
        ImageButton b7=(ImageButton)findViewById(R.id.imageButton7);
        b7.setOnClickListener(new Click7());
        ImageButton b8=(ImageButton)findViewById(R.id.imageButton8);
        b8.setOnClickListener(new Click8());
        ImageButton b9=(ImageButton)findViewById(R.id.imageButton9);
        b9.setOnClickListener(new Click9());
    }

    public class Click1 implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            ImageButton b1=(ImageButton)findViewById(R.id.imageButton1);

            if(modalita.equals("1"))
                casella1_1x();
            else if(modalita.equals("2"))
                casella1_1o();
            if(modalita.equals("3"))
                if (singolo==0){
                    casella1_1x();
                    singolo++;
                    TextView mano=(TextView)findViewById(R.id.textView1);
                    mano.setText("Turno ospite fai la tua mossa...");
                }
                else{
                    casella1_1o();
                    singolo--;
                    TextView mano=(TextView)findViewById(R.id.textView1);
                    mano.setText("Turno "+nickname+" fai la tua mossa...");
                }
            b1.setEnabled(false);
            inserimento_array(0);
            if (modalita.equals("3")){
                palyer_vs_player();
            }
            else{
                if(fine_partita_giocatore()==1||fine_partita_giocatore()==2)
                    return;
                gioco_computer();
            }
        }
    }

    public class Click2 implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            ImageButton b2=(ImageButton)findViewById(R.id.imageButton2);

            if(modalita.equals("1"))
                casella2_2x();
            else if(modalita.equals("2"))
                casella2_2o();
            if(modalita.equals("3"))
                if (singolo==0){
                    casella2_2x();
                    singolo++;
                    TextView mano=(TextView)findViewById(R.id.textView1);

                    mano.setText("Turno ospite fai la tua mossa...");
                }
                else{
                    casella2_2o();
                    singolo--;
                    TextView mano=(TextView)findViewById(R.id.textView1);
                    mano.setText("Turno "+nickname+" fai la tua mossa...");
                }
            b2.setEnabled(false);
            inserimento_array(1);
            if (modalita.equals("3")){
                palyer_vs_player();
            }
            else{
                if(fine_partita_giocatore()==1||fine_partita_giocatore()==2)
                    return;
                gioco_computer();
            }
        }
    }

    public class Click3 implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            ImageButton b3=(ImageButton)findViewById(R.id.imageButton3);

            if(modalita.equals("1"))
                casella3_3x();
            else if(modalita.equals("2"))
                casella3_3o();
            if(modalita.equals("3"))
                if (singolo==0){
                    casella3_3x();
                    singolo++;
                    TextView mano=(TextView)findViewById(R.id.textView1);

                    mano.setText("Turno ospite fai la tua mossa...");
                }
                else{
                    casella3_3o();
                    singolo--;
                    TextView mano=(TextView)findViewById(R.id.textView1);
                    mano.setText("Turno "+nickname+" fai la tua mossa...");
                }
            b3.setEnabled(false);
            inserimento_array(2);
            if (modalita.equals("3")){
                palyer_vs_player();
            }
            else{
                if(fine_partita_giocatore()==1||fine_partita_giocatore()==2)
                    return;
                gioco_computer();
            }
        }
    }

    public class Click4 implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            ImageButton b4=(ImageButton)findViewById(R.id.imageButton4);

            if(modalita.equals("1"))
                casella4_4x();
            else if(modalita.equals("2"))
                casella4_4o();
            if(modalita.equals("3"))
                if (singolo==0){
                    casella4_4x();
                    singolo++;
                    TextView mano=(TextView)findViewById(R.id.textView1);

                    mano.setText("Turno ospite fai la tua mossa...");
                }
                else{
                    casella4_4o();
                    singolo--;
                    TextView mano=(TextView)findViewById(R.id.textView1);

                    mano.setText("Turno "+nickname+" fai la tua mossa...");
                }
            b4.setEnabled(false);
            inserimento_array(3);
            if (modalita.equals("3")){
                palyer_vs_player();
            }
            else{
                if(fine_partita_giocatore()==1||fine_partita_giocatore()==2)
                    return;
                gioco_computer();
            }
        }
    }

    public class Click5 implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            ImageButton b5=(ImageButton)findViewById(R.id.imageButton5);

            if(modalita.equals("1"))
                casella5_5x();
            else if(modalita.equals("2"))
                casella5_5o();
            if(modalita.equals("3"))
                if (singolo==0){
                    casella5_5x();
                    singolo++;
                    TextView mano=(TextView)findViewById(R.id.textView1);

                    mano.setText("Turno ospite fai la tua mossa...");
                }
                else{
                    casella5_5o();
                    singolo--;
                    TextView mano=(TextView)findViewById(R.id.textView1);
                    mano.setText("Turno "+nickname+" fai la tua mossa...");
                }
            b5.setEnabled(false);
            inserimento_array(4);
            if (modalita.equals("3")){
                palyer_vs_player();
            }
            else{
                if(fine_partita_giocatore()==1||fine_partita_giocatore()==2)
                    return;
                gioco_computer();
            }
        }
    }

    public class Click6 implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            ImageButton b6=(ImageButton)findViewById(R.id.imageButton6);

            if(modalita.equals("1"))
                casella6_6x();
            else if(modalita.equals("2"))
                casella6_6o();
            if(modalita.equals("3"))
                if (singolo==0){
                    casella6_6x();
                    singolo++;
                    TextView mano=(TextView)findViewById(R.id.textView1);

                    mano.setText("Turno ospite fai la tua mossa...");
                }
                else{
                    casella6_6o();
                    singolo--;
                    TextView mano=(TextView)findViewById(R.id.textView1);
                    mano.setText("Turno "+nickname+" fai la tua mossa...");
                }
            b6.setEnabled(false);
            inserimento_array(5);
            if (modalita.equals("3")){
                palyer_vs_player();
            }
            else{
                if(fine_partita_giocatore()==1||fine_partita_giocatore()==2)
                    return;
                gioco_computer();
            }
        }
    }

    public class Click7 implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            ImageButton b7=(ImageButton)findViewById(R.id.imageButton7);

            if(modalita.equals("1"))
                casella7_7x();
            else if(modalita.equals("2"))
                casella7_7o();
            if(modalita.equals("3"))
                if (singolo==0){
                    casella7_7x();
                    singolo++;
                    TextView mano=(TextView)findViewById(R.id.textView1);

                    mano.setText("Turno ospite fai la tua mossa...");
                }
                else{
                    casella7_7o();
                    singolo--;
                    TextView mano=(TextView)findViewById(R.id.textView1);
                    mano.setText("Turno "+nickname+" fai la tua mossa...");
                }
            b7.setEnabled(false);
            inserimento_array(6);
            if (modalita.equals("3")){
                palyer_vs_player();
            }
            else{
                if(fine_partita_giocatore()==1||fine_partita_giocatore()==2)
                    return;
                gioco_computer();
            }
        }
    }

    public class Click8 implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            ImageButton b8=(ImageButton)findViewById(R.id.imageButton8);

            if(modalita.equals("1"))
                casella8_8x();
            else if(modalita.equals("2"))
                casella8_8o();
            if(modalita.equals("3"))
                if (singolo==0){
                    casella8_8x();
                    singolo++;
                    TextView mano=(TextView)findViewById(R.id.textView1);

                    mano.setText("Turno ospite fai la tua mossa...");
                }
                else{
                    casella8_8o();
                    singolo--;
                    TextView mano=(TextView)findViewById(R.id.textView1);
                    mano.setText("Turno "+nickname+" fai la tua mossa...");
                }
            b8.setEnabled(false);
            inserimento_array(7);
            if (modalita.equals("3")){
                palyer_vs_player();
            }
            else{
                if(fine_partita_giocatore()==1||fine_partita_giocatore()==2)
                    return;
                gioco_computer();
            }
        }
    }

    public class Click9 implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            ImageButton b9=(ImageButton)findViewById(R.id.imageButton9);

            if(modalita.equals("1"))
                casella9_9x();
            else if(modalita.equals("2"))
                casella9_9o();
            if(modalita.equals("3"))
                if (singolo==0){
                    casella9_9x();
                    singolo++;
                    TextView mano=(TextView)findViewById(R.id.textView1);

                    mano.setText("Turno ospite fai la tua mossa...");
                }
                else{
                    casella9_9o();
                    singolo--;
                    TextView mano=(TextView)findViewById(R.id.textView1);
                    mano.setText("Turno "+nickname+" fai la tua mossa...");
                }
            b9.setEnabled(false);
            inserimento_array(8);
            if (modalita.equals("3")){
                palyer_vs_player();
            }
            else{
                if(fine_partita_giocatore()==1||fine_partita_giocatore()==2)
                    return;
                gioco_computer();
            }
        }
    }


    private void inizzializza(){
        int i;
        for(i=0;i<9;i++)
            x[i]=10;
    }


    private void gioco_computer(){
        int flag=0;
        int posizione=0;
        posizione=vincita_evitare(computer);
        if (posizione!=11){
            inserimento_array(posizione);
            inserimento_X_O_nel_campo(computer,posizione);
            flag=1;
        }
        else{
            posizione=vincita_evitare(player);
            if (posizione!=11){
                inserimento_array(posizione);
                inserimento_X_O_nel_campo(computer,posizione);
            }
            else{
                posizione=mosse();
                if (posizione!=11){
                    inserimento_array(posizione);
                    inserimento_X_O_nel_campo(computer,posizione);
                }
            }
        }
        if(flag==1){
            /*vittoria computer*/
            fine_partita("Vince BizBong");
            return;
        }
        if (turno>8&&flag==0) {
            /*pareggio*/
            fine_partita("Pareggio");
        }
        return;
    }

    private void inserimento_X_O_nel_campo(int computer,int posizione){
        TextView mano=(TextView)findViewById(R.id.textView1);
        mano.setText("Attendere turno BizBong...");
        counter.start();
        if (computer==0)
            switch(posizione){
                case 0:
                    ImageButton b1=(ImageButton)findViewById(R.id.imageButton1);
                    casella1_1o();
                    b1.setEnabled(false);
                    break;
                case 1:
                    ImageButton b2=(ImageButton)findViewById(R.id.imageButton2);
                    casella2_2o();
                    b2.setEnabled(false);
                    break;
                case 2:
                    ImageButton b3=(ImageButton)findViewById(R.id.imageButton3);
                    casella3_3o();
                    b3.setEnabled(false);
                    break;
                case 3:
                    ImageButton b4=(ImageButton)findViewById(R.id.imageButton4);
                    casella4_4o();
                    b4.setEnabled(false);
                    break;
                case 4:
                    ImageButton b5=(ImageButton)findViewById(R.id.imageButton5);
                    casella5_5o();
                    b5.setEnabled(false);
                    break;
                case 5:
                    ImageButton b6=(ImageButton)findViewById(R.id.imageButton6);
                    casella6_6o();
                    b6.setEnabled(false);
                    break;
                case 6:
                    ImageButton b7=(ImageButton)findViewById(R.id.imageButton7);
                    casella7_7o();
                    b7.setEnabled(false);
                    break;
                case 7:
                    ImageButton b8=(ImageButton)findViewById(R.id.imageButton8);
                    casella8_8o();
                    b8.setEnabled(false);
                    break;
                case 8:
                    ImageButton b9=(ImageButton)findViewById(R.id.imageButton9);
                    casella9_9o();
                    b9.setEnabled(false);
                    break;
            }
        if (computer==1)
            switch(posizione){
                case 0:
                    ImageButton b1=(ImageButton)findViewById(R.id.imageButton1);
                    casella1_1x();
                    b1.setEnabled(false);
                    break;
                case 1:
                    ImageButton b2=(ImageButton)findViewById(R.id.imageButton2);
                    casella2_2x();
                    b2.setEnabled(false);
                    break;
                case 2:
                    ImageButton b3=(ImageButton)findViewById(R.id.imageButton3);
                    casella3_3x();
                    b3.setEnabled(false);
                    break;
                case 3:
                    ImageButton b4=(ImageButton)findViewById(R.id.imageButton4);
                    casella4_4x();
                    b4.setEnabled(false);
                    break;
                case 4:
                    ImageButton b5=(ImageButton)findViewById(R.id.imageButton5);
                    casella5_5x();
                    b5.setEnabled(false);
                    break;
                case 5:
                    ImageButton b6=(ImageButton)findViewById(R.id.imageButton6);
                    casella6_6x();
                    b6.setEnabled(false);
                    break;
                case 6:
                    ImageButton b7=(ImageButton)findViewById(R.id.imageButton7);
                    casella7_7x();
                    b7.setEnabled(false);
                    break;
                case 7:
                    ImageButton b8=(ImageButton)findViewById(R.id.imageButton8);
                    casella8_8x();
                    b8.setEnabled(false);
                    break;
                case 8:
                    ImageButton b9=(ImageButton)findViewById(R.id.imageButton9);
                    casella9_9x();
                    b9.setEnabled(false);
                    break;
            }
        return;
    }

    private void inserimento_array(int p){
        if (turno%2==0)
            x[p]=2;                      /*X=2*/
        else
            x[p]=3;                             /*O=3*/
        turno++;
        return;
    }

    private int vincita_evitare(int giocatore){
        int i;
        int casella=0;
        if(giocatore==1)
            casella=40;                    /*per le x*/
        else
            casella=90;                    /*per il O*/
        i=0;

        while(i<=6){
            //controllo righe cella vuota vincente o evitare
            if(x[i]*x[i+1]*x[i+2]==casella){
                if(x[i]==10)
                    return i;
                if(x[i+1]==10)
                    return i+1;
                if(x[i+2]==10)
                    return i+2;
            }
            i+=3;
        }

        i=0;
        while(i<=2){
            //controllo colonna cella vuota vincente o evitare
            if(x[i]*x[i+3]*x[i+6]==casella){
                if(x[i]==10)
                    return i;
                if(x[i+3]==10)
                    return i+3;
                if(x[i+6]==10)
                    return i+6;
            }
            i++;
        }

        //controllo diagonale principale cella vuota vincente o evitare
        if(x[0]*x[4]*x[8]==casella){
            if(x[0]==10)
                return 0;
            if(x[4]==10)
                return 4;
            if(x[8]==10)
                return 8;
        }

        //controllo diagonale principale cella vuota vincente o evitare
        if(x[2]*x[4]*x[6]==casella){
            if(x[2]==10)
                return 2;
            if(x[4]==10)
                return 4;
            if(x[6]==10)
                return 6;
        }
        return  11;
    }

    private int mosse(){
        switch(r){
            case 0:
                if(x[4]==10)
                    return 4;
                if(x[1]==10)
                    return 1;
                if(x[3]==10)
                    return 3;
                if(x[7]==10)
                    return 7;
                if(x[5]==10)
                    return 5;
                if(x[0]==10)
                    return 0;
                if(x[8]==10)
                    return 8;
                if(x[2]==10)
                    return 2;
                if(x[6]==10)
                    return 6;
                return 11;

            case 1:
                if(x[2]==10)
                    return 2;
                if(x[6]==10)
                    return 6;
                if(x[8]==10)
                    return 8;
                if(x[7]==10)
                    return 7;
                if(x[5]==10)
                    return 5;
                if(x[0]==10)
                    return 0;
                if(x[4]==10)
                    return 4;
                if(x[1]==10)
                    return 1;
                if(x[3]==10)
                    return 3;
                return 11;

            case 2:
                if(x[4]==10)
                    return 4;
                if(x[0]==10)
                    return 0;
                if(x[2]==10)
                    return 2;
                if(x[5]==10)
                    return 5;
                if(x[8]==10)
                    return 8;
                if(x[7]==10)
                    return 7;
                if(x[3]==10)
                    return 3;
                if(x[1]==10)
                    return 1;
                if(x[6]==10)
                    return 6;
                return 11;

            case 3:
                if(x[8]==10)
                    return 8;
                if(x[1]==10)
                    return 1;
                if(x[7]==10)
                    return 7;
                if(x[4]==10)
                    return 4;
                if(x[2]==10)
                    return 2;
                if(x[5]==10)
                    return 5;
                if(x[0]==10)
                    return 0;
                if(x[3]==10)
                    return 3;
                if(x[6]==10)
                    return 6;

                return 11;

            case 4:
                if(x[5]==10)
                    return 5;
                if(x[3]==10)
                    return 3;
                if(x[0]==10)
                    return 0;
                if(x[7]==10)
                    return 7;
                if(x[1]==10)
                    return 1;
                if(x[6]==10)
                    return 6;
                if(x[2]==10)
                    return 2;
                if(x[8]==10)
                    return 8;
                if(x[4]==10)
                    return 4;
                return 11;

            case 5:
                if(x[0]==10)
                    return 0;
                if(x[2]==10)
                    return 2;
                if(x[4]==10)
                    return 4;
                if(x[1]==10)
                    return 1;
                if(x[7]==10)
                    return 7;
                if(x[5]==10)
                    return 5;
                if(x[3]==10)
                    return 3;
                if(x[6]==10)
                    return 6;
                if(x[8]==10)
                    return 8;
                return 11;

            case 6:
                if(x[4]==10)
                    return 4;
                if(x[2]==10)
                    return 2;
                if(x[8]==10)
                    return 8;
                if(x[3]==10)
                    return 3;
                if(x[0]==10)
                    return 0;
                if(x[6]==10)
                    return 6;
                if(x[7]==10)
                    return 7;
                if(x[1]==10)
                    return 1;
                if(x[5]==10)
                    return 5;
                return 11;

            case 7:
                if(x[6]==10)
                    return 6;
                if(x[8]==10)
                    return 8;
                if(x[0]==10)
                    return 0;
                if(x[5]==10)
                    return 5;
                if(x[7]==10)
                    return 7;
                if(x[1]==10)
                    return 1;
                if(x[2]==10)
                    return 2;
                if(x[3]==10)
                    return 3;
                if(x[4]==10)
                    return 4;
                return 11;

        }
        return 11;
    }

    private int vittoria(){
        int i=0;
        //controllo righe
        while(i<=6)
        {
            if(x[i]*x[i+1]*x[i+2]==8)
                return 8;
            else
            if(x[i]*x[i+1]*x[i+2]==27)
                return 27;
            i+=3;
        }

        i=0;
        //controllo colonne
        while(i<=2)
        {
            if(x[i]*x[i+3]*x[i+6]==8)
                return 8;
            else
            if(x[i]*x[i+3]*x[i+6] ==27)
                return 27;
            i++;
        }

        //controllo diagonale principale
        if(x[0]*x[4]*x[8]==8)
            return 8;
        else
        if(x[0]*x[4]*x[8]==27)
            return 27;

        //controllo diagonale secondaria
        if(x[2]*x[4]*x[6]==8)
            return 8;
        else
            if(x[2]*x[4]*x[6]==27)
                return 27;
        return 0;

    }

    private void creacampo() {
        ImageButton b1 = (ImageButton) findViewById(R.id.imageButton1);
        ImageButton b2 = (ImageButton) findViewById(R.id.imageButton2);
        ImageButton b3 = (ImageButton) findViewById(R.id.imageButton3);
        ImageButton b4 = (ImageButton) findViewById(R.id.imageButton4);
        ImageButton b5 = (ImageButton) findViewById(R.id.imageButton5);
        ImageButton b6 = (ImageButton) findViewById(R.id.imageButton6);
        ImageButton b7 = (ImageButton) findViewById(R.id.imageButton7);
        ImageButton b8 = (ImageButton) findViewById(R.id.imageButton8);
        ImageButton b9 = (ImageButton) findViewById(R.id.imageButton9);
        b1.setImageResource(R.drawable.classic1);
        b2.setImageResource(R.drawable.classic2);
        b3.setImageResource(R.drawable.classic3);
        b4.setImageResource(R.drawable.classic4);
        b5.setImageResource(R.drawable.classic5);
        b6.setImageResource(R.drawable.classic6);
        b7.setImageResource(R.drawable.classic7);
        b8.setImageResource(R.drawable.classic8);
        b9.setImageResource(R.drawable.classic9);
    }

    private void fine_partita(String s){
        ImageButton b1=(ImageButton)findViewById(R.id.imageButton1);
        ImageButton b2=(ImageButton)findViewById(R.id.imageButton2);
        ImageButton b3=(ImageButton)findViewById(R.id.imageButton3);
        ImageButton b4=(ImageButton)findViewById(R.id.imageButton4);
        ImageButton b5=(ImageButton)findViewById(R.id.imageButton5);
        ImageButton b6=(ImageButton)findViewById(R.id.imageButton6);
        ImageButton b7=(ImageButton)findViewById(R.id.imageButton7);
        ImageButton b8=(ImageButton)findViewById(R.id.imageButton8);
        ImageButton b9=(ImageButton)findViewById(R.id.imageButton9);
        b1.setEnabled(false);
        b2.setEnabled(false);
        b3.setEnabled(false);
        b4.setEnabled(false);
        b5.setEnabled(false);
        b6.setEnabled(false);
        b7.setEnabled(false);
        b8.setEnabled(false);
        b9.setEnabled(false);
        TextView mano=(TextView)findViewById(R.id.textView1);
        mano.setVisibility(View.INVISIBLE);

        final Dialog fine= new Dialog(GameTrisActivity.this);
        fine.setContentView(R.layout.dialog_fine_tris);
        fine.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        fine.setCancelable(false);

        TextView t = (TextView) fine.findViewById(R.id.titolo);
        t.setText(s);

        ImageView image = (ImageView) fine.findViewById(R.id.image);
        image.setImageResource(R.drawable.go);

        Button dialogButtonRigioca = (Button) fine.findViewById(R.id.dialogButtonOK);
        Button dialogButtonNo = (Button) fine.findViewById(R.id.dialogButtonNO);

        dialogButtonRigioca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fine.dismiss();
                int s=getIntent().getExtras().getInt("s");
                if(modalita.equals("1")||modalita.equals("2")){
                    if(modalita.equals("1"))
                        modalita="2";
                    else
                        modalita="1";
                    Intent openPage= new Intent(GameTrisActivity.this,GameTrisActivity.class);
                    openPage.putExtra("modalita", modalita);
                    openPage.putExtra("s",s);
                    finish();
                    startActivity(openPage);
                }
                if(modalita.equals("3")){
                    if (s==0)
                        s=1;
                    else
                        s=0;
                    Intent openPage= new Intent(GameTrisActivity.this,GameTrisActivity.class);
                    openPage.putExtra("modalita", modalita);
                    openPage.putExtra("s", s);
                    finish();
                    startActivity(openPage);
                }
            }
        });
        dialogButtonNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fine.dismiss();
                finish();
                Intent intent = new Intent(GameTrisActivity.this, HomeActivity.class);
                startActivity(intent);
                //menu principale
            }
        });
        fine.show();
        return;
    }

    private int fine_partita_giocatore(){
        if(vittoria()==8||vittoria()==27){
            /*vittoria giocatore*/
            fine_partita("Vince "+nickname);
            return 1;
        }
        if (turno>8){
            /*pareggio*/
            fine_partita("Pareggio");
            return 2;
        }
        return 0;
    }


    private void palyer_vs_player(){
        int s=getIntent().getExtras().getInt("s");
        if (s==0){
            if(vittoria()==8) {
                fine_partita("Vince " + nickname);
                return;
            }
            if (vittoria()==27) {
                fine_partita("Vince Ospite");
                return;
            }
        }

        if (s==1){
            if(vittoria()==8) {
                fine_partita("Vince Ospite");
                return;
            }
            if (vittoria()==27) {
                fine_partita("Vince " + nickname);
                return;
            }
        }

        if(turno>8){
            /*pareggio*/
            fine_partita("Pareggio");
            return;
        }
        return;
    }


    private void casella1_1x(){
        ImageButton b=(ImageButton)findViewById(R.id.imageButton1);
        b.setImageResource(R.drawable.classic1_1x);
    }

    private void casella1_1o(){
        ImageButton b=(ImageButton)findViewById(R.id.imageButton1);
        b.setImageResource(R.drawable.classic1_1o);

    }

    private void casella2_2x(){
        ImageButton b=(ImageButton)findViewById(R.id.imageButton2);
        b.setImageResource(R.drawable.classic2_2x);
    }

    private void casella2_2o(){
        ImageButton b=(ImageButton)findViewById(R.id.imageButton2);
        b.setImageResource(R.drawable.classic2_2o);
    }

    private void casella3_3x(){
        ImageButton b=(ImageButton)findViewById(R.id.imageButton3);
        b.setImageResource(R.drawable.classic3_3x);
    }

    private void casella3_3o(){
        ImageButton b=(ImageButton)findViewById(R.id.imageButton3);
        b.setImageResource(R.drawable.classic3_3o);
    }

    private void casella4_4x(){
        ImageButton b=(ImageButton)findViewById(R.id.imageButton4);
        b.setImageResource(R.drawable.classic4_4x);
    }

    private void casella4_4o(){
        ImageButton b=(ImageButton)findViewById(R.id.imageButton4);
        b.setImageResource(R.drawable.classic4_4o);
    }

    private void casella5_5x(){
        ImageButton b=(ImageButton)findViewById(R.id.imageButton5);
        b.setImageResource(R.drawable.classic5_5x);
    }

    private void casella5_5o(){
        ImageButton b=(ImageButton)findViewById(R.id.imageButton5);
        b.setImageResource(R.drawable.classic5_5o);
    }

    private void casella6_6x(){
        ImageButton b=(ImageButton)findViewById(R.id.imageButton6);
        b.setImageResource(R.drawable.classic6_6x);
    }

    private void casella6_6o(){
        ImageButton b=(ImageButton)findViewById(R.id.imageButton6);
        b.setImageResource(R.drawable.classic6_6o);
    }

    private void casella7_7x(){
        ImageButton b=(ImageButton)findViewById(R.id.imageButton7);
        b.setImageResource(R.drawable.classic7_7x);
    }

    private void casella7_7o(){
        ImageButton b=(ImageButton)findViewById(R.id.imageButton7);
        b.setImageResource(R.drawable.classic7_7o);
    }

    private void casella8_8x(){
        ImageButton b=(ImageButton)findViewById(R.id.imageButton8);
        b.setImageResource(R.drawable.classic8_8x);
    }

    private void casella8_8o(){
        ImageButton b=(ImageButton)findViewById(R.id.imageButton8);
        b.setImageResource(R.drawable.classic8_8o);
    }


    private void casella9_9x(){
        ImageButton b=(ImageButton)findViewById(R.id.imageButton9);
        b.setImageResource(R.drawable.classic9_9x);
    }

    private void casella9_9o(){
        ImageButton b=(ImageButton)findViewById(R.id.imageButton9);
        b.setImageResource(R.drawable.classic9_9o);
    }

    private class MyCount extends CountDownTimer {
        public MyCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }
        @Override
        public void onFinish() {
            TextView mano=(TextView)findViewById(R.id.textView1);
            mano.setText("Turno "+nickname+" fai la tua mossa...");
        }
        @Override
        public void onTick(long millisUntilFinished) {
            TextView mano=(TextView)findViewById(R.id.textView1);
            mano.setText("Attendere turno BizBong...");
        }
    }
}
