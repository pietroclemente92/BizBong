package gamesoftitalia.bizbong.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Spinner;

import com.google.gson.Gson;

import java.util.concurrent.ExecutionException;

import gamesoftitalia.bizbong.R;
import gamesoftitalia.bizbong.adapters.ClassificaAdapter;
import gamesoftitalia.bizbong.connessione.ClassificaAsync;
import gamesoftitalia.bizbong.entity.Classifica;

/**
 * Created by GameSoftItalia on 19/12/2016.
 */

public class ClassificaFragment extends android.support.v4.app.Fragment {

    private static View view;
    private ListView classifica_list;
    private Spinner classifica_spinner;
    private String resultGson;

    private String[] nicknamesClassificati, iconeProfiloClassificati;
    private int[] punteggiClassificati;
    private int numeroClassificati;

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        // View
        view = inflater.inflate(R.layout.fragment_classifica, container, false);

        // ListView
        classifica_list = (ListView) view.findViewById(R.id.classifica_list);

        // Spinner
        classifica_spinner = (Spinner) view.findViewById(R.id.classifica_spinner);

        /*************************************************************************************************************************/
        /*    Ogni volta che viene scelto un elemento dello spinner viene invocato la sync con il server e viene scaricato       */
        /*    l' oggetto Classifica della modalita scelta                                                                        */
        /*************************************************************************************************************************/
        classifica_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                Object item = parent.getItemAtPosition(pos);

                try {
                    resultGson = new ClassificaAsync(container.getContext()).execute(item.toString().toLowerCase()).get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }

                Classifica classifica = new Gson().fromJson(resultGson, Classifica.class);
                numeroClassificati = classifica.getClassificatiList().size();

                // Classifica Variable
                nicknamesClassificati = new String[numeroClassificati];
                iconeProfiloClassificati = new String[numeroClassificati];
                punteggiClassificati = new int[numeroClassificati];

                for(int i = 0; i < numeroClassificati; i++){
                    nicknamesClassificati[i] = classifica.getClassificatiList().get(i).getNickname();
                    iconeProfiloClassificati[i] = "@mipmap/"+classifica.getClassificatiList().get(i).getImgProfilo();
                    punteggiClassificati[i] = classifica.getClassificatiList().get(i).getPunteggio();
                }

                classifica_list.setAdapter(new ClassificaAdapter(container.getContext(), iconeProfiloClassificati,
                        nicknamesClassificati, punteggiClassificati));
            }
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        return view;
    }
}