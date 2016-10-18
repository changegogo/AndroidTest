package com.feicui.androidtest.androidtest.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: dlw on 2016/9/25 17:23
 * Email: dailongshao@126.com
 */
public class PicAdapter extends FragmentPagerAdapter {

    private List<Fragment> list;

    public PicAdapter(FragmentManager fm) {
        super(fm);
        this.list = new ArrayList<>();
    }

    public void addList(List<Fragment> olist){
        if(olist != null) {
            list.clear();
            list.addAll(olist);
        }
    }

    public List<Fragment> getList(){
        return list;
    }

    public void update(){
        this.notifyDataSetChanged();
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        if(list!=null)
            return list.size();
        return 0;
    }
}
