package com.example.timing;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.List;

public class TabPagerAdapter extends FragmentStateAdapter {
    private List<Fragment> m_fragmentList;
    public TabPagerAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle, List<Fragment>fragmentList) {
        super(fragmentManager, lifecycle);
        m_fragmentList=fragmentList;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        try{
            return (Fragment) m_fragmentList.get(position);
        }catch (IllegalAccessError e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int getItemCount() {
        return m_fragmentList.size();
    }
}
