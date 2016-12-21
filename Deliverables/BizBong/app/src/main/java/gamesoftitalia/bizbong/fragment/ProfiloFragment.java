package gamesoftitalia.bizbong.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import gamesoftitalia.bizbong.HomeActivity;
import gamesoftitalia.bizbong.R;

/**
 * Created by Raffaella on 19/12/2016.
 */

public class ProfiloFragment extends Fragment{
    private static View view;
    private static TextView nicknameText, email;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Context
        context = getActivity();

        //Shared
        sharedPreferences = context.getSharedPreferences("sessionUtente", context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        // View
        view =  inflater.inflate(R.layout.fragment_profilo, container, false);

        // String
        nicknameText = (TextView) view.findViewById(R.id.nicknameProfilo);
        if(sharedPreferences.getAll().containsKey("nickname"))
            nicknameText.setText("Session:"+sharedPreferences.getAll().get("nickname").toString());

        return view;
    }
}
