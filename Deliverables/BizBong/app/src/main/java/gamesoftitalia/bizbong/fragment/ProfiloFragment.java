package gamesoftitalia.bizbong.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import gamesoftitalia.bizbong.HomeActivity;
import gamesoftitalia.bizbong.R;
import gamesoftitalia.bizbong.entity.Profilo;

/**
 * Created by Raffaella on 19/12/2016.
 */

public class ProfiloFragment extends Fragment{
    private static View view;
    private Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Context
        context = getActivity();

        // View
        view =  inflater.inflate(R.layout.fragment_profilo, container, false);

        return view;
    }
}
