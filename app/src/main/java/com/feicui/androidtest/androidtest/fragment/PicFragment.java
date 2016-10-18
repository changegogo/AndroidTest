package com.feicui.androidtest.androidtest.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.feicui.androidtest.androidtest.R;

/**
 * Author: dlw on 2016/9/25 17:30
 * Email: dailongshao@126.com
 */
public class PicFragment extends Fragment {
    private String mPic;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_fragment,container,false);
        Bundle bundle = getArguments();
        mPic = bundle.getString("pic");
        initView(view);
        return view;
    }

    private void initView(View view) {
        ImageView img = (ImageView) view.findViewById(R.id.iv_frag);
        Glide.with(getActivity()).load(mPic).into(img);
    }

    public static PicFragment getPicFragment(String picUrl){
        Bundle bundle = new Bundle();
        bundle.putString("pic",picUrl);
        PicFragment fragment = new PicFragment();
        fragment.setArguments(bundle);
        return fragment;
    }
}
