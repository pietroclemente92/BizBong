package gamesoftitalia.bizbong.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import gamesoftitalia.bizbong.R;

/**
 * Created by Raffaella on 19/12/2016.
 */

public class ProfiloFragment extends Fragment{
    private static View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // View
        view =  inflater.inflate(R.layout.fragment_profilo, container, false);

        return view;
    }
}
