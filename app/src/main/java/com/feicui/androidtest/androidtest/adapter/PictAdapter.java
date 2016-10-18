package com.feicui.androidtest.androidtest.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: dlw on 2016/9/25 18:03
 * Email: dailongshao@126.com
 */
public class PictAdapter extends PagerAdapter {
    private ArrayList<ImageView> viewlist;
    public PictAdapter() {
        this.viewlist = new ArrayList<>();
    }

    public ArrayList<ImageView> getList(){
        return viewlist;
    }

    public void addList(List<ImageView> oList){
        if(oList !=null){
            viewlist.clear();
            viewlist.addAll(oList);
            //update();
        }
    }
    private void update(){
        this.notifyDataSetChanged();
    }
    @Override
    public int getCount() {
        //设置成最大，使用户看不到边界
        //return viewlist.size();
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0==arg1;
    }

    @Override
    public void destroyItem(ViewGroup container, int position,
                            Object object) {
        //container.removeView(viewlist.get(position));//删除页卡
    }
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        //container.addView(viewlist.get(position), 0);//添加页卡
        //return viewlist.get(position);
       position %= viewlist.size();
        if (position<0){
            position = viewlist.size()+position;
        }
        ImageView view = viewlist.get(position);
        ViewParent vp =view.getParent();
        if (vp!=null){
            ViewGroup parent = (ViewGroup)vp;
            parent.removeView(view);
        }
        container.addView(view);
        return view;
    }
}
