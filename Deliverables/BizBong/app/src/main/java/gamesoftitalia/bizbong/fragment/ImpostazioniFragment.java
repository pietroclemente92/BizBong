package gamesoftitalia.bizbong.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import gamesoftitalia.bizbong.R;

/**
 * Created by Raffaella on 19/12/2016.
 */

public class ImpostazioniFragment extends android.support.v4.app.Fragment {

    private static View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // View
        view =  inflater.inflate(R.layout.fragment_impostazioni, container, false);

        return view;
    }
}