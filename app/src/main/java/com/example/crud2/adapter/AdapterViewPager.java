package com.example.crud2.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.crud2.fragment.FragmentInfo;
import com.example.crud2.fragment.FragmentList;
import com.example.crud2.fragment.FragmentSearch;

public class AdapterViewPager extends FragmentStatePagerAdapter {

    private int numPage;

    public AdapterViewPager(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
        this.numPage = behavior;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new FragmentList();
            case 1:
                return new FragmentInfo();
            case 2:
                return new FragmentSearch();
        }
        return new FragmentList();
    }

    @Override
    public int getCount() {
        return numPage;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "List";
            case 1:
                return "Info";
            case 2:
                return "Search";
        }
        return super.getPageTitle(position);
    }
}
