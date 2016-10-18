package com.feicui.androidtest.androidtest.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.feicui.androidtest.androidtest.R;
import com.feicui.androidtest.androidtest.entity.WeatherData;

import java.util.List;

/**
 * Author: dlw on 2016/9/25 17:05
 * Email: dailongshao@126.com
 */
public class WeatherAdapter extends BaseAdapter {
    private List<WeatherData.DataBean> list;
    private Context context;

    public WeatherAdapter(List<WeatherData.DataBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    public void addList(List<WeatherData.DataBean> oList){
        if(oList != null ){
            list.clear();
            list.addAll(oList);
        }
    }

    public void update(){
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        if(list != null)
            return list.size();
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_listview,parent,false);
            holder = new ViewHolder();
            holder.tvWeather = (TextView) convertView.findViewById(R.id.tv_weather);
            holder.tvArea = (TextView) convertView.findViewById(R.id.tv_area);
            convertView.setTag(holder);
        }
        holder = (ViewHolder) convertView.getTag();
        //String area = list.get(position).getDistrict();
        holder.tvArea.setText(list.get(position).getDistrict()+":");
        holder.tvWeather.setText(list.get(position).getWeather());
        return convertView;
    }

    static class ViewHolder{
        TextView tvWeather;
        TextView tvArea;
    }
}
