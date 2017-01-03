package gamesoftitalia.bizbong.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import gamesoftitalia.bizbong.fragment.ClassificaFragment;
import gamesoftitalia.bizbong.fragment.HomeFragment;
import gamesoftitalia.bizbong.fragment.ProfiloFragment;

/**
 * Created by GameSoftItalia on 19/12/2016.
 */

public class PagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public PagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                HomeFragment tab1 = new HomeFragment();
                return tab1;
            case 1:
                ClassificaFragment tab2 = new ClassificaFragment();
                return tab2;
            case 2:
                ProfiloFragment tab3 = new ProfiloFragment();
                return tab3;
           /* case 3:
                ImpostazioniFragment tab4 = new ImpostazioniFragment();
                return tab4;*/
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}