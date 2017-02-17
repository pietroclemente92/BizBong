package gamesoftitalia.bizbong;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.res.Configuration;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.Switch;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Locale;

import gamesoftitalia.bizbong.adapters.ItemDataSpinner;
import gamesoftitalia.bizbong.adapters.SpinnerAdapter;
import gamesoftitalia.bizbong.entity.Impostazioni;
import gamesoftitalia.bizbong.service.MusicServiceBase;

public class ImpostazioniActivity extends AppCompatActivity {

    static final int READ_BLOCK_SIZE = 100;
    private Impostazioni entity;
    private Button ok;
    private Intent music=new Intent();
    private boolean audioAssociato;
    private MusicServiceBase mServ;

    private Activity context;

    ServiceConnection Scon =new ServiceConnection(){
        /*implementazione metodi astratti*/
        public void onServiceConnected(ComponentName name, IBinder binder) {
            mServ = ((MusicServiceBase.ServiceBinder)binder).getService();
        }
        public void onServiceDisconnected(ComponentName name) {
            mServ = null;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_impostazioni);
        context= ImpostazioniActivity.this;
        caricaImpostazioni();

        //audio activity
        audioAssociato=entity.getAudio();
        if (audioAssociato){
            music.setClass(this, MusicServiceBase.class);
            startService(music);
        }

        //Spinner per la scelta della lingua
        String[] array = getResources().getStringArray(R.array.lingua_arrays);


        ArrayList<ItemDataSpinner> list=new ArrayList<>();
        list.add(new ItemDataSpinner(array[0],R.mipmap.ita));
        list.add(new ItemDataSpinner(array[1],R.mipmap.eng));
        list.add(new ItemDataSpinner(array[2],R.mipmap.ukr));
        Spinner sp=(Spinner)findViewById(R.id.spinner1);

        SpinnerAdapter adapter=new SpinnerAdapter(this, R.layout.spinner_layout,R.id.txt,list);
        sp.setAdapter(adapter);
        //imposto spinner con la lingua corrente
        impostaSpinner(sp);

        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                Configuration config = new Configuration();
                String[] array = getResources().getStringArray(R.array.lingua_arrays);
                switch (arg2) {
                    case 0:
                        config.locale = Locale.ITALIAN;
                        entity.setLingua("ita");
                        break;
                    case 1:
                        config.locale = Locale.ENGLISH;
                        entity.setLingua("eng");
                        break;
                    case 2:
                        entity.setLingua("ukr");
                        Locale locale = new Locale("uk");
                        Locale.setDefault(locale);
                        config.locale = locale;
                        break;
                }
                //aggiorno file
                WriteBtn(entity.getLingua(),String.valueOf(entity.getAudio()),String.valueOf(entity.getEffetti()),String.valueOf(entity.getVibrazione()));
                getResources().updateConfiguration(config, null);
            }

            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });

        //abilitare-disbilitare audio
        Switch swAudio=(Switch) findViewById(R.id.switch1);
        //imposto switch con audio corrente
        impostaSwitchAudio(swAudio);
        swAudio.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    entity.setAudio(true);
                    music.setClass(context, MusicServiceBase.class);
                    bindService(music, Scon, Context.BIND_AUTO_CREATE);
                    audioAssociato=true;
                    startService(music);
                }
                else {
                    entity.setAudio(false);
                    try {
                        stopService(music);
                        unbindService(Scon);
                        audioAssociato = false;
                    }catch(IllegalArgumentException e){}
                }
                //aggiorno file
                WriteBtn(entity.getLingua(),String.valueOf(entity.getAudio()),String.valueOf(entity.getEffetti()),String.valueOf(entity.getVibrazione()));
            }

        });

        //abilitare-disabilitare effetti
        Switch swEffetti=(Switch) findViewById(R.id.switch2);
        //imposto switch con effetti corrente
        impostaSwitchEffetti(swEffetti);
        swEffetti.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                    entity.setEffetti(true);
                else
                    entity.setEffetti(false);
                //aggiorno file
                WriteBtn(entity.getLingua(),String.valueOf(entity.getAudio()),String.valueOf(entity.getEffetti()),String.valueOf(entity.getVibrazione()));
            }

        });

        //abilitare-disabilitare vibrazione
        Switch swVibrazione=(Switch) findViewById(R.id.switch3);
        //imposto switch con vibrazione corrente
        impostaSwitchVibrazione(swVibrazione);
        swVibrazione.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                    entity.setVibrazione(true);
                else
                    entity.setVibrazione(false);
                //aggiorno file
                WriteBtn(entity.getLingua(),String.valueOf(entity.getAudio()),String.valueOf(entity.getEffetti()),String.valueOf(entity.getVibrazione()));
            }

        });

        //button pe tornare all'home
        ok = (Button) findViewById(R.id.button1);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(entity.getEffetti()==true) {
                    MediaPlayer mp = MediaPlayer.create(ImpostazioniActivity.this, R.raw.bottoni);
                    mp.start();
                }
                if(entity.getVibrazione()){
                    Vibrator g = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    g.vibrate(100);
                }
                Intent intent = new Intent(ImpostazioniActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });

    }//onCrate

    private void caricaImpostazioni(){
        //stringa da dividere
        String imp=ReadBtn();

        //divisione stringa
        String[] i=imp.split("\\-");

        String lingua=i[0];
        String audioS=i[1];
        String effettiS=i[2];
        String vibrazioneS=i[3];

        //conversione stringhe
        boolean audio= Boolean.valueOf(audioS);             //String-->boolean
        boolean effetti= Boolean.valueOf(effettiS);         //String-->boolean
        boolean vibrazione= Boolean.valueOf(vibrazioneS);   //String-->boolean

        //oggetto impostazioni da avvalorare con i valori presi da file
        entity=new Impostazioni(lingua,audio,effetti,vibrazione);
    }

    private void impostaSpinner(Spinner x){
        String[] array = getResources().getStringArray(R.array.lingua_arrays);
        if (entity.getLingua().equals("ita"))
                                      x.setSelection(0);
        else if (entity.getLingua().equals("eng"))
                                           x.setSelection(1);
             else if (entity.getLingua().equals("ukr"))
                                                x.setSelection(2);
                   else
                     x.setSelection(0);
    }

    private void impostaSwitchEffetti(Switch x){
        if (entity.getEffetti())
            x.setChecked(true);
        else
            x.setChecked(false);
    }

    private void impostaSwitchVibrazione(Switch x){
        if (entity.getVibrazione())
            x.setChecked(true);
        else
            x.setChecked(false);
    }

    private void impostaSwitchAudio(Switch x){
        if (entity.getAudio())
            x.setChecked(true);
        else
            x.setChecked(false);
    }

    private void WriteBtn(String lingua,String audio,String effetti,String vibrazione) {
        try {
            FileOutputStream fileout=openFileOutput("BizBong.txt", MODE_PRIVATE);
            OutputStreamWriter outputWriter=new OutputStreamWriter(fileout);
            outputWriter.write(lingua);
            outputWriter.write("-"+audio);
            outputWriter.write("-"+effetti);
            outputWriter.write("-"+vibrazione);
            outputWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private String ReadBtn() {
        String s="";
        try {
            FileInputStream fileIn=openFileInput("BizBong.txt");
            InputStreamReader InputRead= new InputStreamReader(fileIn);
            char[] inputBuffer= new char[READ_BLOCK_SIZE];
            int charRead;
            while ((charRead=InputRead.read(inputBuffer))>0) {
                String readstring=String.copyValueOf(inputBuffer,0,charRead);
                s +=readstring;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return s;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            Log.d("messaggio","mm");
            return false;

        }
        return super.onKeyDown(keyCode, event);
    }


    @Override
    protected void onResume() {     /*quando l'app viene riattivata*/
        super.onResume();
        if (audioAssociato==true)
            if(mServ!=null) {
                bindService(music, Scon, Context.BIND_AUTO_CREATE);
                mServ.resumeMusic();
            }

    };

    @Override
    protected void onPause() {            /*quando l'app va in background viene stoppato*/
        if (audioAssociato==true)
            if(mServ!=null){
                mServ.pauseMusic();
                unbindService(Scon);
            }
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        try {
            audioAssociato = entity.getAudio();
            if (audioAssociato) {
                mServ.stopMusic();
                stopService(music);
                unbindService(Scon);   //togliere il contesto al servizio
            }
        } catch (NullPointerException |IllegalArgumentException e){}
        super.onDestroy();
    }

}
