package gamesoftitalia.bizbong.connessione;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.widget.Toast;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLEncoder;
import gamesoftitalia.bizbong.HomeActivity;

/**
 * Created by GameSoftItalia on 07/12/2016.
 */

public class LoginAsync extends AsyncTask<String, Void, String> {

    private ProgressDialog loadingDialog;
    private Context context;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private static String nickname, password;

    public LoginAsync(Context context){
        this.context = context;

        //Shared
        sharedPreferences = context.getSharedPreferences("sessioneUtente", context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        loadingDialog = ProgressDialog.show(context, "Please Wait",null, true, true);
        loadingDialog.setMessage("Connessione in corso");
        loadingDialog.setCancelable(false);
    }

    @Override
    protected String doInBackground(String... params) {
        String result = null;

        if(isNetworkAvailable(context)){
            HttpURLConnection con = null;
            //boolean redirect = false;
            try{
                // Parametri nickname e password passati da execute come parametri
                nickname = params[0];
                password = params[1];

                // Dati nickname e password inoltrati al server
                String data = "?nickname=" + URLEncoder.encode(nickname, "UTF-8");
                data += "&password=" + URLEncoder.encode(password, "UTF-8");

                // Link
                String link = "http://bizbong.altervista.org/php/Control/LoginControl.php" + data;
                // Log.d("DEBUG:", link);

                // URL
                URL url = new URL(link);

                // HttpURLConnection
                con = (HttpURLConnection) url.openConnection();

                // Timeout per la connessione
                int timeOutInMillis = 3000;
                con.setConnectTimeout(timeOutInMillis);

                // Connection
                con.connect();

                // Timeout nel ricerever informazioni
                int socketTimeOutinMillis = 3000;
                con.setReadTimeout(socketTimeOutinMillis);

                // InputStream risultato server
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                if(con.getHeaderField("Location") == null)
                    result = bufferedReader.readLine();
                bufferedReader.close();

                return result;
            }  catch (SocketTimeoutException e) {
                return null;
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                con.disconnect();
            }
        }
        return result;
    }

    @Override
    protected void onPostExecute(String result) {
        loadingDialog.dismiss();

        if (result != null) {
            // Bool di ritorno da server (true) --> Utente esistente; (false) --> Utente non trovato;
            Boolean bool = new Gson().fromJson(result, Boolean.class);

            if(bool){
                Toast.makeText(context, "Accesso eseugito correttamente. Benvenuto "+nickname+"!!!", Toast.LENGTH_SHORT).show();

                // Sessione inserisce nickname
                editor.putString("nickname", nickname);
                editor.putString("online", "true");
                editor.commit();

                // Intent
                Intent intent = new Intent(context, HomeActivity.class);
                context.startActivity(intent);
            } else {
                Toast.makeText(context, "Errore: Problema riscontrato durante la procedura di autenticazione. Controllare credenziali o collegamento a internet.", Toast.LENGTH_SHORT).show();
            }
        } else {
            String msg = "Connessione lenta o non funzionante";
            AlertDialog.Builder builder;
            builder = new AlertDialog.Builder(context);
            builder.setCancelable(false);
            builder.setTitle("Timeout connessione");
            builder.setMessage(msg);

            builder.setPositiveButton("Riprova", new DialogInterface.OnClickListener(){
                @Override
                public void onClick(DialogInterface dialog, int which)
                {
                    dialog.dismiss();
                    new LoginAsync(context).execute(nickname, password);
                }
            });

            builder.setNegativeButton("Esci", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        }
    }

    @Override
    public void onCancelled(){
        super.onCancelled();
        cancel(true);
    }

    private static boolean isNetworkAvailable(Context context){
        return  ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo() != null;
    }
}
