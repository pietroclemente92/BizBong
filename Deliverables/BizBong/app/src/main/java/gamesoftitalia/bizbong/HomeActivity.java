package gamesoftitalia.bizbong;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.view.LayoutInflater;
import android.widget.TextView;
import gamesoftitalia.bizbong.adapters.PagerAdapter;

public class HomeActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        /*
        // Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        */

        // TabLayout
        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("Tab 1"));
        tabLayout.addTab(tabLayout.newTab().setText("Tab 2"));
        tabLayout.addTab(tabLayout.newTab().setText("Tab 3"));
        tabLayout.addTab(tabLayout.newTab().setText("Tab 4"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        setupTabIcons();

        // ViewPager
        viewPager = (ViewPager) findViewById(R.id.pager);
        final PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void setupTabIcons() {

        TextView tabUno = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tablayout, null);
        tabUno.setText("ONE");
        tabUno.setCompoundDrawablesWithIntrinsicBounds(0, R.mipmap.ic_home, 0, 0);
        tabLayout.getTabAt(0).setCustomView(tabUno);

        TextView tabDue = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tablayout, null);
        tabDue.setText("TWO");
        tabDue.setCompoundDrawablesWithIntrinsicBounds(0, R.mipmap.ic_challenge, 0, 0);
        tabLayout.getTabAt(1).setCustomView(tabDue);

        TextView tabTre = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tablayout, null);
        tabTre.setText("THREE");
        tabTre.setCompoundDrawablesWithIntrinsicBounds(0, R.mipmap.ic_profile, 0, 0);
        tabLayout.getTabAt(2).setCustomView(tabTre);

        TextView tabQuattro = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tablayout, null);
        tabQuattro.setText("FOUR");
        tabQuattro.setCompoundDrawablesWithIntrinsicBounds(0, R.mipmap.ic_setting, 0, 0);
        tabLayout.getTabAt(3).setCustomView(tabQuattro);
    }

}
