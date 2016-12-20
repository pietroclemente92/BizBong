package gamesoftitalia.bizbong.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import gamesoftitalia.bizbong.HomeActivity;
import gamesoftitalia.bizbong.R;
import gamesoftitalia.bizbong.adapters.CustomBannerAdapter;

/**
 * Created by GameSoftItalia on 19/12/2016.
 */

public class HomeFragment extends android.support.v4.app.Fragment {

    private static View view;
    private ViewPager viewPager;
    private int image[];

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // View
        view = inflater.inflate(R.layout.fragment_home, container, false);

        // Image Array
        image = new int[]{R.drawable.news2, R.drawable.news1};

        // ViewPager
        viewPager = (ViewPager) view.findViewById(R.id.viewPagerBanner);
        viewPager.setAdapter(new CustomBannerAdapter(view.getContext()));

        return view;
    }
}
