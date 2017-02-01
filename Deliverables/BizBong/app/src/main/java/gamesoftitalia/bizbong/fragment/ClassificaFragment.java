package gamesoftitalia.bizbong.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Spinner;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;

import gamesoftitalia.bizbong.R;
import gamesoftitalia.bizbong.adapters.ClassificaAdapter;
import gamesoftitalia.bizbong.adapters.ItemDataSpinner;
import gamesoftitalia.bizbong.adapters.SpinnerAdapter;
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

        String[] array = getResources().getStringArray(R.array.classifica_arrays);

        // Spinner
        ArrayList<ItemDataSpinner> list=new ArrayList<>();
        list.add(new ItemDataSpinner(array[0],R.mipmap.ita));
        list.add(new ItemDataSpinner(array[1],R.mipmap.ita));
        list.add(new ItemDataSpinner(array[2],R.mipmap.ita));
        list.add(new ItemDataSpinner(array[3],R.mipmap.ita));
        list.add(new ItemDataSpinner(array[4],R.mipmap.ita));
        list.add(new ItemDataSpinner(array[5],R.mipmap.ita));
        list.add(new ItemDataSpinner(array[6],R.mipmap.ita));
        list.add(new ItemDataSpinner(array[7],R.mipmap.ita));
        list.add(new ItemDataSpinner(array[8],R.mipmap.ita));
        list.add(new ItemDataSpinner(array[9],R.mipmap.ita));
        list.add(new ItemDataSpinner(array[10],R.mipmap.ita));
        list.add(new ItemDataSpinner(array[11],R.mipmap.ita));
        list.add(new ItemDataSpinner(array[12],R.mipmap.ita));
        list.add(new ItemDataSpinner(array[13],R.mipmap.ita));
        list.add(new ItemDataSpinner(array[14],R.mipmap.ita));
        list.add(new ItemDataSpinner(array[15],R.mipmap.ita));
        list.add(new ItemDataSpinner(array[16],R.mipmap.ita));
        list.add(new ItemDataSpinner(array[17],R.mipmap.ita));
        list.add(new ItemDataSpinner(array[18],R.mipmap.ita));
        list.add(new ItemDataSpinner(array[19],R.mipmap.ita));



        classifica_spinner = (Spinner) view.findViewById(R.id.classifica_spinner);

        SpinnerAdapter adapter=new SpinnerAdapter((Activity) getContext(), R.layout.spinner_layout,R.id.txt,list);
        classifica_spinner.setAdapter(adapter);

        /*************************************************************************************************************************/
        /*    Ogni volta che viene scelto un elemento dello spinner viene invocato la sync con il server e viene scaricato       */
        /*    l' oggetto Classifica della modalita scelta                                                                        */
        /*************************************************************************************************************************/
        classifica_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                ItemDataSpinner item = (ItemDataSpinner) parent.getItemAtPosition(pos);


                try {
                    resultGson = new ClassificaAsync(container.getContext()).execute(item.getText().toLowerCase()).get();
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