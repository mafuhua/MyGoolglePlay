package com.hellomc.mygoogle.fragment;

import android.support.v4.app.Fragment;

import java.util.HashMap;

/**
 * Created by hellomc on 2015/12/25.
 */
public class FragmentFactory {
    private static FragmentFactory instance = new FragmentFactory();
    private HashMap<Integer, Fragment> cacheFragment = new HashMap<>();

    public FragmentFactory() {
    }

    public static FragmentFactory getInstance() {
        return instance;
    }

    public Fragment creatFragment(int position) {
        Fragment fragment = null;
        fragment = cacheFragment.get(position);
        if (fragment == null) {
            if (position == 0) {
                fragment = new HomeFragment();
            } else if (position == 1) {
                fragment = new AppFragment();
            } else if (position == 2) {
                fragment = new GameFragment();
            } else if (position == 3) {
                fragment = new TopicFragment();
            } else if (position == 4) {
                fragment = new CategoryFragment();
            } else {
                fragment = new OrderFragment();
            }
            cacheFragment.put(position, fragment);
        }
        return fragment;
    }
}
