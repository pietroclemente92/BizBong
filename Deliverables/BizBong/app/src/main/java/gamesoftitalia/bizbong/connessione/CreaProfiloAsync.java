package gamesoftitalia.bizbong.connessione;

/**
 * Created by Raffaella on 29/11/2016.
 */

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import gamesoftitalia.bizbong.MainActivity;

public class CreaProfiloAsync extends AsyncTask<String, Void, String> {

    ProgressDialog loadingDialog;
    Context context;

    public CreaProfiloAsync(Context context){
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        loadingDialog = ProgressDialog.show(context, "Please Wait",null, true, true);
    }

    @Override
    protected String doInBackground(String... params) {
        String data;
        String link;
        BufferedReader bufferedReader;
        String result;

        try{
            String nickname = params[0];
            String password = params[1];
            String email = params[2];

            data = "?nickname=" + URLEncoder.encode(nickname, "UTF-8");
            data += "&password=" + URLEncoder.encode(password, "UTF-8");
            data += "&email=" + URLEncoder.encode(email, "UTF-8");
            link = "http://bizbong.altervista.org/php/Control/RegistratiControl.php" + data;
            //Log.d("DEBUG:", link);
            URL url = new URL(link);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
            result = bufferedReader.readLine();
            return result;
        } catch(Exception e){
            return new String("Exception: " + e.getMessage());
        }
    }

    @Override
    protected void onPostExecute(String result) {
        loadingDialog.dismiss();
        if (result != null) {
            Boolean bool = new Gson().fromJson(result, Boolean.class);
            //Log.d("DEBUG:", "value--->"+bool);
            if(bool){
                Toast.makeText(context, "Utente registrato correttamente. Le preghiamo di accettare l'email sulla propria posta elettronica ai fini di convalidare la registrazione.", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, MainActivity.class);
                context.startActivity(intent);
            } else {
                Toast.makeText(context, "Errore: Problema riscontrato durante la procedura di registrazione. Controllare credenziali o collegamento a internet.", Toast.LENGTH_SHORT).show();
            }
                /*Utente utente = new Gson().fromJson(result, Utente.class);
                return utente.getNickname();*/
        }
    }
}
