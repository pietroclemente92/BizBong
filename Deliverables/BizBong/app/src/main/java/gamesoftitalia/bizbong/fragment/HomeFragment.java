package gamesoftitalia.bizbong.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import gamesoftitalia.bizbong.HomeActivity;
import gamesoftitalia.bizbong.NuovaPartitaActivity;
import gamesoftitalia.bizbong.R;
import gamesoftitalia.bizbong.adapters.CustomHomePagerAdapter;
import gamesoftitalia.bizbong.gifanimator.PlayGifView;

/**
 * Created by GameSoftItalia on 19/12/2016.
 */

public class HomeFragment extends android.support.v4.app.Fragment {

    private static View view;
    private ViewPager viewPagerBanner, viewPagerGames;
    private int[] imageBanner, imageGames;
    private Button nuovaPartita;
    private PlayGifView gif;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // View
        view = inflater.inflate(R.layout.fragment_home, container, false);

        // Image Array
        imageBanner = new int[]{R.drawable.news2, R.drawable.news1};
        imageGames = new int[]{R.drawable.icon_bizbong, R.drawable.icon_sudoku};

        // ViewPager
        viewPagerGames = (ViewPager) view.findViewById(R.id.viewPagerGames);
        viewPagerGames.setAdapter(new CustomHomePagerAdapter(view.getContext(), imageGames));

        viewPagerBanner = (ViewPager) view.findViewById(R.id.viewPagerBanner);
        viewPagerBanner.setAdapter(new CustomHomePagerAdapter(view.getContext(), imageBanner));

        // GIF
        gif = (PlayGifView) view.findViewById(R.id.gif);
        gif.setImageResource(R.drawable.cane);

        // Button
        nuovaPartita = (Button) view.findViewById(R.id.nuovaPartitaButton);
        nuovaPartita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), NuovaPartitaActivity.class);
                intent.putExtra("position", viewPagerGames.getCurrentItem());
                startActivity(intent);
            }
        });

        return view;
    }
}
