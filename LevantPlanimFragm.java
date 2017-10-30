package com.lag.altanizio.conceitosdetopografia;


import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class LevantPlanimFragm extends Fragment {

    private ViewPager viewPager;
    private View inflatedView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        inflatedView = inflater.inflate(R.layout.fragment_levant_planim, container, false);

        TabLayout tabLayout = (TabLayout) inflatedView.findViewById(R.id.tabLayout);
        tabLayout.addTab(tabLayout.newTab().setText("Introdução"));
        tabLayout.addTab(tabLayout.newTab().setText("Montagem"));
        tabLayout.addTab(tabLayout.newTab().setText("Nivelamento"));
        tabLayout.addTab(tabLayout.newTab().setText("Medição"));
        tabLayout.addTab(tabLayout.newTab().setText("Salvar dados"));
        tabLayout.addTab(tabLayout.newTab().setText("Manuais"));
        viewPager = (ViewPager) inflatedView.findViewById(R.id.viewpager);

        viewPager.setAdapter(new PagerAdapter
                (getFragmentManager(), tabLayout.getTabCount()));
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

        Drawable back = getResources().getDrawable(R.drawable.img_estacao_backg);
        back.setAlpha(50);
        viewPager.setBackground(back);

        return inflatedView;
    }

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
                    TextTabFragment tab1 = new TextTabFragment();
                    return tab1;
                case 1:
                    TextTabEstacMont tab2 = new TextTabEstacMont();
                    return tab2;
                case 2:
                    TextTabEstacNiv tab3 = new TextTabEstacNiv();
                    return tab3;
                case 3:
                    TextTabEstacLeitura tab4 = new TextTabEstacLeitura();
                    return tab4;
                case 4:
                    TextTabEstacDados tab5 = new TextTabEstacDados();
                    return tab5;
                case 5:
                    TextTabEstacBio tab6 = new TextTabEstacBio();
                    return tab6;
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return mNumOfTabs;
        }


    }

    @Override
    public void onStop() {
        super.onStop();
        Runtime.getRuntime().gc();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Runtime.getRuntime().gc();
    }

    @Override
    public void onResume() {
        super.onResume();

    }
}


