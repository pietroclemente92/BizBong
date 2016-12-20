package gamesoftitalia.bizbong.service;


import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import gamesoftitalia.bizbong.R;

/**
 * Created by Michele on 20/12/2016.
 */

public class MusicServiceBase extends Service  implements MediaPlayer.OnErrorListener{
    public final IBinder mBinder = new ServiceBinder();
    MediaPlayer mPlayer;

    private int length = 0;

    public MusicServiceBase() { }


    public class ServiceBinder extends Binder {
        public MusicServiceBase getService()
        {
            return MusicServiceBase.this;
        }
    }



    @Override
    public IBinder onBind(Intent arg0){return mBinder;}

    @Override
    public void onCreate (){
        super.onCreate();

        mPlayer = MediaPlayer.create(this, R.raw.base);
        mPlayer.setOnErrorListener(this);

        if(mPlayer!= null)
            mPlayer.setLooping(true);


        mPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {

            public boolean onError(MediaPlayer mp, int cosa, int extra){
                onError(mPlayer, cosa, extra);
                return true;
            }
        });
    }

    @Override
    public int onStartCommand (Intent intent, int flags, int startId) {
        mPlayer.start();
        return START_STICKY;                        /*Per i servizi avviati, ci sono due principali
                                                      modalità aggiuntive di funzionamento che possono
                                                      decidere per l'esecuzione in, a seconda del
                                                      valore ritornano da onStartCommand ():
                                                      START_STICKY viene utilizzato per i servizi che
                                                      vengono esplicitamente avviato e fermato, se necessario,
                                                      mentre START_NOT_STICKY o START_REDELIVER_INTENT vengono utilizzati
                                                      per i servizi che dovrebbero rimanere solo in esecuzione durante
                                                      l'elaborazione di tutti i comandi inviati loro.*/
    }

    public void pauseMusic() {
        if(mPlayer.isPlaying()) {
            mPlayer.pause();
            length=mPlayer.getCurrentPosition();
        }
    }

    public void resumeMusic() {
        if(mPlayer.isPlaying()==false) {
            mPlayer.seekTo(length);
            mPlayer.start();
        }
    }

    public void stopMusic() {
        mPlayer.stop();
        mPlayer.release();
        mPlayer = null;
    }

    @Override
    public void onDestroy () {
        super.onDestroy();
        if(mPlayer != null)
        {
            try{
                mPlayer.stop();
                mPlayer.release();
            }finally {
                mPlayer = null;
            }
        }
    }

    public boolean onError(MediaPlayer mp, int what, int extra) {
        if(mPlayer != null) {
            try{
                mPlayer.stop();
                mPlayer.release();
            }finally {
                mPlayer = null;
            }
        }
        return false;
    }
}

