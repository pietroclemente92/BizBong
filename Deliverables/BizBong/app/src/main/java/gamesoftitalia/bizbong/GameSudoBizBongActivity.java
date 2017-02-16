package gamesoftitalia.bizbong;

import android.app.Dialog;
    import android.content.Context;
    import android.content.Intent;
    import android.content.SharedPreferences;
    import android.graphics.Color;
    import android.graphics.drawable.ColorDrawable;
    import android.media.MediaPlayer;
    import android.os.CountDownTimer;
    import android.os.Vibrator;
    import android.support.v7.app.AppCompatActivity;
    import android.os.Bundle;
    import android.view.View;
    import android.view.animation.Animation;
    import android.view.animation.AnimationUtils;
    import android.widget.Button;
    import android.widget.GridLayout;
    import android.widget.ImageButton;
    import android.widget.ImageView;
    import android.widget.RelativeLayout;
    import android.widget.TextView;

    import com.google.gson.Gson;

    import java.util.ArrayList;
    import java.util.concurrent.ExecutionException;

    import gamesoftitalia.bizbong.connessione.ModificaStatisticheAsync;
    import gamesoftitalia.bizbong.connessione.ProfiloAsync;
    import gamesoftitalia.bizbong.connessione.SudoBizBongAsync;
    import gamesoftitalia.bizbong.entity.FineSudoBizBong;
    import gamesoftitalia.bizbong.entity.Impostazioni;
    import gamesoftitalia.bizbong.entity.Profilo;
    import gamesoftitalia.bizbong.entity.SoluzioneSudoBizBong;
    import gamesoftitalia.bizbong.entity.Statistiche;
    import gamesoftitalia.bizbong.entity.SudoBizBong;
    import gamesoftitalia.bizbong.random.UniqueRandom;

    public class GameSudoBizBongActivity extends AppCompatActivity {

        private TextView timer;                                                               //time gioco
        private ImageView orologio;                                                           //orologio game
        private TextView totmosse;                                                            //mosse disponibili per l utente
        private TextView tmosse;                                                              //txt mosse
        private int [][] matrice;                                                             //matrice con le giocate dell utente
        private ArrayList<ImageButton> ListaButton=new ArrayList<ImageButton>();                        //button che stanno nel gridlayout
        private int[] numbers;                                                                //array delle mosse che l'utente può fare (es n1 corrisponde un immagine di un tema)
        private ArrayList<ImageButton> ListaButtonUtente=new ArrayList<ImageButton>();                  //button che scegli l'utente per inserirli nel sudoku
        private ImageView timerIamge;                                                         //immagine timer avvio partita 1/2/3/go
        private CountDownTimer Conteggio;                                                     //timer vero e proprio
        private SudoBizBong sudokuInizio;
        private Button fine;

        private SharedPreferences sharedPreferences;
        private SharedPreferences.Editor editor;
        private String nickname;
        private String profiloGson;
        private Profilo profilo;
        private Impostazioni entity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        entity= (Impostazioni) getIntent().getSerializableExtra("Impostazioni");     //ricevitore oggetto Impostazioni

        // SharedPrefernces
        sharedPreferences = this.getSharedPreferences("sessioneUtente", this.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        if(sharedPreferences.getAll().containsKey("nickname"))
            nickname = sharedPreferences.getAll().get("nickname").toString();

       try {
            profiloGson = new ProfiloAsync(GameSudoBizBongActivity.this).execute(nickname).get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        profilo = new Gson().fromJson(profiloGson, Profilo.class);



        String modalita=getIntent().getExtras().getString("modalita");
        startSudoBizBong(modalita);
    }


    //funzione che gestisce le varie difficoltà tramite lo switch
    private void startSudoBizBong(String mod){
        String s1="";
        String s2="";
        switch(mod) {
            case "2x2f":
                s1 = getResources().getString(R.string.t2x2f);
                s2 = getResources().getString(R.string.c2x2f);
                setContentView(R.layout.sudobizbong2x2);
                timer=(TextView)findViewById(R.id.time);
                tmosse=(TextView)findViewById(R.id.mosse);
                totmosse=(TextView)findViewById(R.id.n_mosse);
                orologio=(ImageView)findViewById(R.id.orologio);
                orologio.setImageResource(getResources().getIdentifier("@drawable/watch1", null, getPackageName()));
                timer.setText("35:00");
                break;
            case "2x2m":
                s1 = getResources().getString(R.string.t2x2m);
                s2 = getResources().getString(R.string.c2x2m);
                setContentView(R.layout.sudobizbong2x2);
                timer=(TextView)findViewById(R.id.time);
                tmosse=(TextView)findViewById(R.id.mosse);
                totmosse=(TextView)findViewById(R.id.n_mosse);
                orologio=(ImageView)findViewById(R.id.orologio);
                orologio.setImageResource(getResources().getIdentifier("@drawable/watch1", null, getPackageName()));
                timer.setText("30:00");
                break;
            case "2x2d":
                s1 = getResources().getString(R.string.t2x2d);
                s2 = getResources().getString(R.string.c2x2d);
                setContentView(R.layout.sudobizbong2x2);
                timer=(TextView)findViewById(R.id.time);
                tmosse=(TextView)findViewById(R.id.mosse);
                totmosse=(TextView)findViewById(R.id.n_mosse);
                orologio=(ImageView)findViewById(R.id.orologio);
                orologio.setImageResource(getResources().getIdentifier("@drawable/watch1", null, getPackageName()));
                timer.setText("20:00");
                break;
            case "3x3f":
                s1 = getResources().getString(R.string.t3x3f);
                s2 = getResources().getString(R.string.c3x3f);
                setContentView(R.layout.sudobizbong3x3);
                timer=(TextView)findViewById(R.id.time);
                tmosse=(TextView)findViewById(R.id.mosse);
                totmosse=(TextView)findViewById(R.id.n_mosse);
                orologio=(ImageView)findViewById(R.id.orologio);
                orologio.setImageResource(getResources().getIdentifier("@drawable/watch1", null, getPackageName()));
                timer.setText("35:00");
                break;
            case "3x3m":
                s1 = getResources().getString(R.string.t3x3m);
                s2 = getResources().getString(R.string.c3x3m);
                setContentView(R.layout.sudobizbong3x3);
                timer=(TextView)findViewById(R.id.time);
                tmosse=(TextView)findViewById(R.id.mosse);
                totmosse=(TextView)findViewById(R.id.n_mosse);
                orologio=(ImageView)findViewById(R.id.orologio);
                orologio.setImageResource(getResources().getIdentifier("@drawable/watch1", null, getPackageName()));
                timer.setText("30:00");
                break;
            case "3x3d":
                s1 = getResources().getString(R.string.t3x3d);
                s2 = getResources().getString(R.string.c3x3d);
                setContentView(R.layout.sudobizbong3x3);
                timer=(TextView)findViewById(R.id.time);
                tmosse=(TextView)findViewById(R.id.mosse);
                totmosse=(TextView)findViewById(R.id.n_mosse);
                orologio=(ImageView)findViewById(R.id.orologio);
                orologio.setImageResource(getResources().getIdentifier("@drawable/watch1", null, getPackageName()));
                timer.setText("20:00");
                break;
        }

        try{
            String result = new SudoBizBongAsync(GameSudoBizBongActivity.this).execute(mod).get();
            sudokuInizio = new Gson().fromJson(result,  SudoBizBong.class);
        } catch (Exception e){
            e.printStackTrace();
        }

        //controlla se l oggetto inviato dal server non è vuoto
        if(sudokuInizio!=null) {
            matrice = new int[sudokuInizio.getDimensione()][sudokuInizio.getDimensione()];
            //avvia partita//
            scelta_avvio_partita(s1, s2);
        }
        else{
            //fare messaggio di errore
        }
    }


    //funzione che comunica all utente se desidera giocare e in caso positivo avvia il gioco
    private void scelta_avvio_partita(String titolo,String corpo){
        final Dialog inizio = new Dialog(GameSudoBizBongActivity.this);
        inizio.setContentView(R.layout.dialog_avvia_sudobizbong);
        inizio.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        inizio.setCancelable(false);

        TextView t = (TextView) inizio.findViewById(R.id.titolo);
        t.setText(titolo);

        TextView c= (TextView) inizio.findViewById(R.id.text);
        c.setText(corpo);


        Button dialogButtonGioca = (Button) inizio.findViewById(R.id.dialogButtonOK);
        Button dialogButtonNo = (Button) inizio.findViewById(R.id.dialogButtonNO);


        /*vedere dove fare button guida */

        dialogButtonGioca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(entity.getEffetti()==true) {
                    MediaPlayer mp = MediaPlayer.create(GameSudoBizBongActivity.this, R.raw.bottoni);
                    mp.start();
                }
                if(entity.getVibrazione()){
                    Vibrator g = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    g.vibrate(100);
                }
                game(inizio);
            }
        });
        dialogButtonNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(entity.getEffetti()==true) {
                    MediaPlayer mp = MediaPlayer.create(GameSudoBizBongActivity.this, R.raw.bottoni);
                    mp.start();
                }
                if(entity.getVibrazione()){
                    Vibrator g = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    g.vibrate(100);
                }
                inizio.dismiss();
                finish();
                Intent intent = new Intent(GameSudoBizBongActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });
        inizio.show();
    }


    //funzione che gestisce tutto il gioco
    private void game(Dialog inizio){
        int mosse=sudokuInizio.getMosse();
        totmosse.setText(""+mosse);
        timerIamge=(ImageView) findViewById(R.id.timer);            //1,2,3 go
        timerIamge.setVisibility(View.VISIBLE);
        scegliImage(sudokuInizio.getDimensione());                 //scelta immagini fra i logo dei temi dispoibili
        creaSudoku(sudokuInizio.getDimensione());                  //avvalora matrice gioco
        applicaSudoku(sudokuInizio.getDimensione());               //il sudoku scelto dal db viene caricato sul gridlayout
        applicaPossibiliMosseUtente(sudokuInizio.getDimensione()); //carico le scelte che può fare l'utente per effettuare la mossa bottoni sotto
        selezionaCasellaSudoku(sudokuInizio.getDimensione());      //quando l'utente seleziona una casella dal sudoku*/
        fineGame();
        timer_intro_partita();
        inizio.dismiss();
    }

    //funzione che carica la traccia nella matrice dove gioca l'utente
    private void creaSudoku(int n){
        String [] o= sudokuInizio.getTraccia().split(",");
        for(int i = 0; i < n*n; i++){
            matrice[i/n][i%n]=Integer.parseInt(o[i]);
        }
    }

    //funzione che restituisce una matrice contenente solo la traccia del sudoku
    private int [][] creaTraccia(int n){
        int [][] traccia;
        traccia=new int[n][n];
        String [] o= sudokuInizio.getTraccia().split(",");
        for(int i = 0; i < n*n; i++){
            traccia[i/n][i%n]=Integer.parseInt(o[i]);    //matrice
        }
        return traccia;
    }


    //funzione che gestisce la fine del gioco e stabilisce se l'utente ha completato bene il sudoku
    private void fineGame(){
        fine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Conteggio != null) {    //ferma taimer
                    Conteggio.cancel();
                    Conteggio = null;
                }
                SoluzioneSudoBizBong soluzione = new SoluzioneSudoBizBong(sudokuInizio.getDimensione(), creaTraccia(sudokuInizio.getDimensione()), matrice);
                if (soluzione.getEsito()==true){
                    FineSudoBizBong fs = new FineSudoBizBong(sudokuInizio.getModalita(),Integer.parseInt((String) totmosse.getText()), "" + timer.getText());
                    String s=getResources().getString(R.string.fineSudov1);
                    String s1=getResources().getString(R.string.fineSudov2)+" "+fs.getPunteggio()+" "+getResources().getString(R.string.rigiocare);


                    // Aggiungi punti a statistiche
                    Statistiche statistiche =  profilo.getStatistiche();
                    for(int j = 0; j < statistiche.getModalitaList().length; j++){
                       if(fs.getModalita().equals(statistiche.getModalitaList()[j]))
                            profilo.getStatistiche().getPunteggiList()[j] += fs.getPunteggio();
                    }

                    // AggiornaStatistiche
                    String aggiornaProfiloGson = new Gson().toJson(profilo, Profilo.class);
                    new ModificaStatisticheAsync(GameSudoBizBongActivity.this).execute(aggiornaProfiloGson);

                    /*ImageView image = null;
                    image.setImageResource(R.drawable.go);*/
                    Dialog_fine(s,s1);
                } else{
                    String s=getResources().getString(R.string.fineSudop);
                    String s1=getResources().getString(R.string.rigiocare);
                    //quando perdi settare le stringhe e richiamare metodo*/
                    Dialog_fine(s,s1);
                }
            }
        });
    }


    //crea il messaggio che l'utente visualizza a fine partita
    private void Dialog_fine(String titolo,String corpo){
        final Dialog fine= new Dialog(GameSudoBizBongActivity.this);
        fine.setContentView(R.layout.dialog_fine_sudobizbong);
        fine.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        fine.setCancelable(false);

        TextView t = (TextView) fine.findViewById(R.id.titolo);
        t.setText(titolo);

        TextView c= (TextView) fine.findViewById(R.id.text);
        c.setText(corpo);

        ImageView image = (ImageView) fine.findViewById(R.id.image);
        image.setImageResource(R.mipmap.tema12);

        Button dialogButtonRigioca = (Button) fine.findViewById(R.id.dialogButtonOK);
        Button dialogButtonNo = (Button) fine.findViewById(R.id.dialogButtonNO);


        dialogButtonRigioca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(entity.getEffetti()==true) {
                    MediaPlayer mp = MediaPlayer.create(GameSudoBizBongActivity.this, R.raw.bottoni);
                    mp.start();
                }
                if(entity.getVibrazione()){
                    Vibrator g = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    g.vibrate(100);
                }
                fine.dismiss();
                finish();
                startActivity(getIntent());
            }
        });
        dialogButtonNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(entity.getEffetti()==true) {
                    MediaPlayer mp = MediaPlayer.create(GameSudoBizBongActivity.this, R.raw.bottoni);
                    mp.start();
                }
                if(entity.getVibrazione()){
                    Vibrator g = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    g.vibrate(100);
                }
                fine.dismiss();
                finish();
                Intent intent = new Intent(GameSudoBizBongActivity.this, HomeActivity.class);
                startActivity(intent);
                //menu principale
            }
        });
        fine.show();
    }


    //timer 1,2,3
    private void timer_intro_partita(){
        new CountDownTimer(4500, 1000) {
            @Override
            public void onTick(long l) {
                String pathName;
                if (l/1000==3){
                    if(entity.getEffetti()==true) {
                        MediaPlayer mp = MediaPlayer.create(GameSudoBizBongActivity.this, R.raw.sudoku_utente);
                        mp.start();
                    }
                    pathName = "@drawable/timer2";
                    timerIamge.setImageResource(getResources().getIdentifier(pathName, null, getPackageName()));
                }
                if (l/1000==2){
                    if(entity.getEffetti()==true) {
                        MediaPlayer mp = MediaPlayer.create(GameSudoBizBongActivity.this, R.raw.sudoku_utente);
                        mp.start();
                    }
                    pathName = "@drawable/timer1";
                    timerIamge.setImageResource(getResources().getIdentifier(pathName, null, getPackageName()));
                }
                if (l/1000==1){
                    if(entity.getEffetti()==true) {
                        MediaPlayer mp = MediaPlayer.create(GameSudoBizBongActivity.this, R.raw.sudoku_utente);
                        mp.start();
                    }
                    pathName = "@drawable/timer0";
                    timerIamge.setImageResource(getResources().getIdentifier(pathName, null, getPackageName()));
                }
            }

            @Override
            public void onFinish() {
                timerIamge.setVisibility(View.GONE);
                animazione_start_partita();
            }
        }.start();
    }


    //funzione che gestisce gli oggetti da visualizzare quando una partita a inzio (compresa animazione)
    private void animazione_start_partita(){
        final RelativeLayout mosseutente=(RelativeLayout) findViewById(R.id.mosseutente);
        final GridLayout campo=(GridLayout) findViewById(R.id.campo);
        Animation Bounce= AnimationUtils.loadAnimation(getApplicationContext(), R.anim.bounce);
        campo.startAnimation(Bounce);
        Bounce.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                campo.setVisibility(View.VISIBLE);
                mosseutente.setVisibility(View.VISIBLE);
                timer.setVisibility(View.VISIBLE);              //fare animazioni piu carine
                tmosse.setVisibility(View.VISIBLE);
                totmosse.setVisibility(View.VISIBLE);
                orologio.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                timer_start_partita();                         //avvio timer partita
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    //timer del gioco
    private void timer_start_partita(){
        Conteggio=new CountDownTimer(sudokuInizio.getTempo(), 1) {
            @Override
            public void onTick(long l) {
                l=l/1000;
                if (l%60<10)
                    timer.setText(""+l /60+":0"+l %60);
                else
                    timer.setText(""+l /60+":"+l %60);
                if ((l%60)%4==0){
                    String pathName = "@drawable/watch1";
                    orologio.setImageResource(getResources().getIdentifier(pathName, null, getPackageName()));
                }
                if ((l%60)%4==3){
                    String pathName = "@drawable/watch2";
                    orologio.setImageResource(getResources().getIdentifier(pathName, null, getPackageName()));
                }
                if ((l%60)%4==2){
                    String pathName = "@drawable/watch3";
                    orologio.setImageResource(getResources().getIdentifier(pathName, null, getPackageName()));
                }
                if ((l%60)%4==1){
                    String pathName = "@drawable/watch4";
                    orologio.setImageResource(getResources().getIdentifier(pathName, null, getPackageName()));
                }
            }

            @Override
            public void onFinish() {
                String s=getResources().getString(R.string.fineSudop);
                String s1=getResources().getString(R.string.rigiocare);
                Dialog_fine(s,s1);
            }
        }.start();
    }


    //funzione che utilizza la classe uniqueRandom per selezionare le possibili mosse dell utente
    private void scegliImage(int n) {
        numbers = new int[n];
        UniqueRandom random = new UniqueRandom();
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = random.nextInt(12);
        }
    }

    //funzione che inserisce nel gridlayout la traccia del sudoku
    /*in questa funzione mettere cose diverse per le caselle soluzione forse mipmap con sfondo diverso*/
    private void applicaSudoku(int n){
        ImageButton bi = null;
        for(int i=0;i<n*n;i++) {
            String strFlag = "@id/" + "b" + i;
            bi = (ImageButton) findViewById(getResources().getIdentifier(strFlag, null, getPackageName()));
            ListaButton.add(bi);
            if (matrice[i/n][i%n] != -1) {
                String pathName = "@mipmap/soluzione" +numbers[matrice[i/n][i%n]] ;               // se metto matrice[i/n][i%n]colore caselle matrice vengono presi dalla matrice quindi sono sempre quelli
                ListaButton.get(i).setImageResource(getResources().getIdentifier(pathName, null, getPackageName()));
                ListaButton.get(i).setEnabled(false);  //mettere qualcosa di diverso (robba grafica cambiare mipmap ) per segnalare che nn può essere modificato traccia
            }

        }
        fine = (Button) findViewById(R.id.finebutton);
    }


    //funzione che permette di fare la mossa all utente
    private void selezionaCasellaSudoku(final int n) {
        for(int i = 0; i < ListaButton.size(); i++){
            final int index=i;                    /****/
            ListaButton.get(index).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(entity.getEffetti()==true) {
                        MediaPlayer mp = MediaPlayer.create(GameSudoBizBongActivity.this, R.raw.bottoni);
                        mp.start();
                    }
                    if(entity.getVibrazione()){
                        Vibrator g = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        g.vibrate(100);
                    }
                    ListaButton.get(index).setBackgroundResource(R.drawable.button_modalita_true);


                    //attivo le possibili scelte dell utente
                    for (int j = 0; j < n; j++)
                        ListaButtonUtente.get(j).setEnabled(true);
                    sceltaUtente(n, index);  //funzione che aggiorna la matrice grafica(arraylistButton) + matrice per il sudoku a seconda della scelta dell'utente
                }
            });
        }
    }

    //applica le immagini al pannello dove l utente può scegliere la sua mossa
    private void applicaPossibiliMosseUtente(int n){
        ImageButton bi = null;
        for(int i=0;i<n;i++){
            String strFlag = "@id/" + "u" + i;
            bi = (ImageButton) findViewById(getResources().getIdentifier(strFlag, null, getPackageName()));
            ListaButtonUtente.add(bi);
            String pathName = "@mipmap/tema" +numbers[i] ;
            ListaButtonUtente.get(i).setImageResource(getResources().getIdentifier(pathName, null, getPackageName()));
            ListaButtonUtente.get(i).setEnabled(false);
        }
    }

    //funzione che gestisce la mossa dell utente
    private void sceltaUtente(final int n, final int x) {
        for (int i = 0; i < ListaButtonUtente.size(); i++) {
            final int index = i;                    //
            ListaButtonUtente.get(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(entity.getEffetti()==true) {
                        MediaPlayer mp = MediaPlayer.create(GameSudoBizBongActivity.this, R.raw.sudoku_utente);
                        mp.start();
                    }
                    if(entity.getVibrazione()){
                        Vibrator g = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        g.vibrate(100);
                    }
                    String pathName = "@mipmap/tema" + numbers[index];
                    ListaButton.get(x).setImageResource(getResources().getIdentifier(pathName, null, getPackageName())); //inserimento sudoku grafico
                    ListaButton.get(x).setBackgroundColor(0x00000000);
                    matrice[x / n][x % n] = index;     //assegno valore alla matrice sudoku

                    for (int i = 0; i < n; i++)
                        ListaButtonUtente.get(i).setEnabled(false);

                    if (controlloFineSudoku(n) == true)
                        fine.setVisibility(View.VISIBLE);  //se la matrice e piena compare tasto controlla

                     decrementaMosse();

                    /*fine mosse previste */
                    if (n==0){
                        String s=getResources().getString(R.string.fineSudop);
                        String s1=getResources().getString(R.string.rigiocare);
                        Dialog_fine(s,s1);
                    }
                }
            });
        }
    }

    private void decrementaMosse(){
        int n= Integer.parseInt((String) totmosse.getText());
        n--;
        totmosse.setText(""+n);
    }

    /*metodo che controllo se il sudoku e completato*/
    private boolean controlloFineSudoku(int n){
        int i=0;
        while (i<n*n){
            if(matrice[i/n][i%n]==-1)
                return false;
            i++;
        }
        return true;
    }



}
