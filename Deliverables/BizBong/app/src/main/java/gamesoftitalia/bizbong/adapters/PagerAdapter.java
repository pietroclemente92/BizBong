package gamesoftitalia.bizbong.adapters;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import gamesoftitalia.bizbong.entity.Impostazioni;
import gamesoftitalia.bizbong.entity.Profilo;
import gamesoftitalia.bizbong.entity.Statistiche;
import gamesoftitalia.bizbong.fragment.ClassificaFragment;
import gamesoftitalia.bizbong.fragment.HomeFragment;
import gamesoftitalia.bizbong.fragment.StatisticheFragment;

/**
 * Created by GameSoftItalia on 19/12/2016.
 */

public class PagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;
    Profilo profilo;
    Impostazioni entity;

    public PagerAdapter(FragmentManager fm, int NumOfTabs, Profilo profilo, Impostazioni impostazioni) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
        this.profilo = profilo;
        this.entity =impostazioni;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                Bundle bundle = new Bundle();
                bundle.putSerializable("impostazioni", entity);
                HomeFragment tab1 = new HomeFragment();
                tab1.setArguments(bundle);
                return tab1;
            case 1:
                Bundle bundle1 = new Bundle();
                bundle1.putSerializable("impostazioni", entity);
                ClassificaFragment tab2 = new ClassificaFragment();
                tab2.setArguments(bundle1);
                return tab2;
            case 2:
                Bundle bundle2 = new Bundle();
                bundle2.putSerializable("profilo", profilo);
                bundle2.putSerializable("impostazioni", entity);
                StatisticheFragment tab3 = new StatisticheFragment();
                tab3.setArguments(bundle2);
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