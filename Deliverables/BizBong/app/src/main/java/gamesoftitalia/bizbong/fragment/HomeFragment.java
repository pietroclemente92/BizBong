package gamesoftitalia.bizbong.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import java.io.Serializable;

import gamesoftitalia.bizbong.HomeActivity;
import gamesoftitalia.bizbong.NuovaPartitaActivity;
import gamesoftitalia.bizbong.R;
import gamesoftitalia.bizbong.adapters.CustomHomePagerAdapter;
import gamesoftitalia.bizbong.entity.Impostazioni;
import gamesoftitalia.bizbong.gifanimator.PlayGifView;

/**
 * Created by GameSoftItalia on 19/12/2016.
 */

public class HomeFragment extends android.support.v4.app.Fragment {

    private static View view;
    private ViewPager  viewPagerGames;
    private int[]  imageGames;
    private Button nuovaPartita;
    private Button leftButton, rightButton;
    private Impostazioni entity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // View
        view = inflater.inflate(R.layout.fragment_home, container, false);

        entity = (Impostazioni) this.getArguments().getSerializable("impostazioni");

        // Image Array
        imageGames = new int[]{R.drawable.icon_bizbong, R.drawable.icon_sudoku, R.drawable.icon_tris};

        // ViewPager
        viewPagerGames = (ViewPager) view.findViewById(R.id.viewPagerGames);
        viewPagerGames.setAdapter(new CustomHomePagerAdapter(view.getContext(), imageGames));

        // Button
        leftButton = (Button) view.findViewById(R.id.precedente);
        leftButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(viewPagerGames.getCurrentItem() == 0)
                    viewPagerGames.setCurrentItem(2, true);
                else
                    viewPagerGames.setCurrentItem(viewPagerGames.getCurrentItem() - 1, true);
            }
        });

        rightButton = (Button) view.findViewById(R.id.successivo);
        rightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(viewPagerGames.getCurrentItem() == 2)
                    viewPagerGames.setCurrentItem(0, true);
                else
                    viewPagerGames.setCurrentItem(viewPagerGames.getCurrentItem() + 1, true);
            }
        });

        nuovaPartita = (Button) view.findViewById(R.id.nuovaPartitaButton);
        nuovaPartita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), NuovaPartitaActivity.class);
                intent.putExtra("position", viewPagerGames.getCurrentItem());
                intent.putExtra("Impostazioni", (Serializable) entity);
                startActivity(intent);
            }
        });

        return view;
    }
}
