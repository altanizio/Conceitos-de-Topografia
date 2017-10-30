package com.lag.altanizio.conceitosdetopografia;


import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class LevantAltimFragm extends Fragment {
    private ViewPager viewPager;

    public LevantAltimFragm() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View inflatedView = inflater.inflate(R.layout.fragment_levant_planim, container, false);

        TabLayout tabLayout = (TabLayout) inflatedView.findViewById(R.id.tabLayout);
        tabLayout.addTab(tabLayout.newTab().setText("Introdução"));
        tabLayout.addTab(tabLayout.newTab().setText("Montagem"));
        tabLayout.addTab(tabLayout.newTab().setText("Medição"));
        tabLayout.addTab(tabLayout.newTab().setText("Salvar dados"));
        tabLayout.addTab(tabLayout.newTab().setText("Manuais"));
        viewPager = (ViewPager) inflatedView.findViewById(R.id.viewpager);

        viewPager.setAdapter(new LevantAltimFragm.PagerAdapter
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

        Drawable back = getResources().getDrawable(R.drawable.img_nivel_backg);
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
                    TextTabNivelIntro tab1 = new TextTabNivelIntro();
                    return tab1;
                case 1:
                    TextTabNivelMont tab2 = new  TextTabNivelMont();
                    return tab2;
                case 2:
                    TextTabNivelMed tab3 = new TextTabNivelMed();
                    return tab3;
                case 3:
                    TextTabNivelDados tab4 = new TextTabNivelDados();
                    return tab4;
                case 4:
                    TextTabNivelBio tab5 = new TextTabNivelBio();
                    return tab5;
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



}
