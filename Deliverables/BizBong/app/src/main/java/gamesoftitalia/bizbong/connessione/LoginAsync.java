package gamesoftitalia.bizbong.connessione;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import gamesoftitalia.bizbong.HomeActivity;

/**
 * Created by GameSoftItalia on 07/12/2016.
 */

public class LoginAsync extends AsyncTask<String, Void, String> {

    ProgressDialog loadingDialog;
    Context context;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private static String nicknameUtente;

    public LoginAsync(Context context){
        this.context = context;

        //Shared
        sharedPreferences = context.getSharedPreferences("sessionUtente", context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
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

            data = "?nickname=" + URLEncoder.encode(nickname, "UTF-8");
            data += "&password=" + URLEncoder.encode(password, "UTF-8");
            link = "http://bizbong.altervista.org/php/Control/LoginControl.php" + data;
            //Log.d("DEBUG:", link);
            URL url = new URL(link);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
            result = bufferedReader.readLine();
            nicknameUtente = nickname;
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
                Toast.makeText(context, "Accesso eseugito correttamente. Benvenuto "+nicknameUtente+"!!!", Toast.LENGTH_SHORT).show();
                editor.putString("nickname", nicknameUtente);
                editor.commit();
                Intent intent = new Intent(context, HomeActivity.class);
                context.startActivity(intent);
            } else {
                Toast.makeText(context, "Errore: Problema riscontrato durante la procedura di autenticazione. Controllare credenziali o collegamento a internet.", Toast.LENGTH_SHORT).show();
            }
                /*Utente utente = new Gson().fromJson(result, Utente.class);
                return utente.getNickname();*/
        }
    }
}
