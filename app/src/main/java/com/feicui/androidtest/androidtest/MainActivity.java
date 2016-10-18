package com.feicui.androidtest.androidtest;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.feicui.androidtest.androidtest.adapter.PicAdapter;
import com.feicui.androidtest.androidtest.adapter.PictAdapter;
import com.feicui.androidtest.androidtest.adapter.WeatherAdapter;
import com.feicui.androidtest.androidtest.entity.PictureData;
import com.feicui.androidtest.androidtest.entity.WeatherData;
import com.feicui.androidtest.androidtest.entity.WeekData;
import com.feicui.androidtest.androidtest.netApi.NetClient;
import com.feicui.androidtest.androidtest.netApi.WeatherApi;
import com.feicui.androidtest.androidtest.util.CommonUtil;
import com.feicui.androidtest.androidtest.view.LineChartView;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private final String TAG = "MainActivity";
    @Bind(R.id.swipelayout)
    SwipeRefreshLayout mSwipeLayout;
    @Bind(R.id.listview)
    ListView mListView;
    @Bind(R.id.indicator_llayout)
    LinearLayout mIndicatorLinearLayout;
    private WeatherApi mWeatherApi;
    private List<WeatherData.DataBean> mWeatherList;
    private WeatherAdapter mWeatherAdapter;
    private ViewPager mViewPager;
    private PicAdapter mPicAdapter;

    private ImageHandler handler = new ImageHandler(new WeakReference<>(this));
    private LineChartView mChart;
    private PictAdapter mPictAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        addHeaderToListView();
        initData();
        initEvent();
    }

    private void addHeaderToListView() {
        mWeatherList = new ArrayList<>();
        mWeatherAdapter = new WeatherAdapter(mWeatherList,this);

        View view = LayoutInflater.from(this).inflate(R.layout.layout_listview,null);
        mListView.addHeaderView(view);
        //获取ViewPager
        mViewPager = ((ViewPager) findViewById(R.id.viewpager));
        //获取折线图
        mChart = ((LineChartView) view.findViewById(R.id.layoutchartView));

        mPictAdapter = new PictAdapter();
        ArrayList<ImageView> imgList = new ArrayList<>();
        for(int i = 0; i< 5; i++){
            ImageView imgView = new ImageView(MainActivity.this);
            imgView.setScaleType(ImageView.ScaleType.FIT_XY);
            imgList.add(imgView);
        }
        mPictAdapter.addList(imgList);
        mViewPager.setAdapter(mPictAdapter);
        mViewPager.setCurrentItem(Integer.MAX_VALUE / 2);
        addIndicator(5);
        //开始轮播效果
       // handler.sendEmptyMessageDelayed(ImageHandler.MSG_UPDATE_IMAGE, ImageHandler.MSG_DELAY);
    }

    private void initData() {
        //设置天气adapter
        mListView.setAdapter(mWeatherAdapter);
        NetClient netClient = NetClient.getInstance();
        mWeatherApi = netClient.getWeatherApi();
        //接口1的数据
       getInterfaceOne();
        //接口2的数据
        getInterfaceTwo();
        //接口3的数据
        getInterfaceThree();
    }
    //获得接口1的数据
    private void getInterfaceOne(){
        Call<PictureData> pictureData = mWeatherApi.getPictureData(CommonUtil.getUuid());
        pictureData.enqueue(new Callback<PictureData>() {
            @Override
            public void onResponse(Call<PictureData> call, Response<PictureData> response) {
                PictureData picData = response.body();
                Log.e(TAG, "onResponse: "+picData );
                List<String> picList = picData.getData();
                ArrayList<ImageView> list = mPictAdapter.getList();
                for (int pos = 0;pos<picList.size();pos++) {
                    ImageView view = list.get(pos);
                    Glide.with(MainActivity.this).load(picList.get(pos)).into(view);
                }
            }
            @Override
            public void onFailure(Call<PictureData> call, Throwable t) {

            }
        });
    }
    //根据获取的数据，得到有几个导航点
    private void addIndicator(int n){
        mIndicatorLinearLayout.removeAllViews();
        for (int i = 0; i < n; i++) {
            ImageView img = new ImageView(this);
            if(i == 0){
                img.setBackgroundResource(R.drawable.circle_sel);
            }else{
                img.setBackgroundResource(R.drawable.circle_unsel);
            }
            mIndicatorLinearLayout.addView(img);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(img.getLayoutParams());
            lp.setMargins(10, 0, 10, 0);
            img.setLayoutParams(lp);
        }
        //设置显示第一页
       // mViewPager.setCurrentItem(Integer.MAX_VALUE / 2,false);
    }
    //获得接口2的数据
    private void getInterfaceTwo(){
        Call<WeekData> pictureData = mWeatherApi.getWeekData(CommonUtil.getUuid());
        pictureData.enqueue(new Callback<WeekData>() {
            @Override
            public void onResponse(Call<WeekData> call, Response<WeekData> response) {
                WeekData weekData = response.body();
                WeekData.DataBean week = weekData.getData();
                mChart.setData(week);
            }

            @Override
            public void onFailure(Call<WeekData> call, Throwable t) {

            }
        });
    }

    //获得接口3的数据
    private void getInterfaceThree(){
        Call<WeatherData> pictureData = mWeatherApi.getWeatherData(CommonUtil.getUuid());
        pictureData.enqueue(new Callback<WeatherData>() {
            @Override
            public void onResponse(Call<WeatherData> call, Response<WeatherData> response) {
                WeatherData weatherData = response.body();
                List<WeatherData.DataBean> weatherList = weatherData.getData();
                mWeatherAdapter.addList(weatherList);
                mWeatherAdapter.update();
            }

            @Override
            public void onFailure(Call<WeatherData> call, Throwable t) {

            }
        });
    }


    private void initEvent() {
        //解决ListView和SwipeRefershLayout滑动冲突
        mListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                boolean enable = false;
                if(mListView != null && mListView.getChildCount() > 0){
                    //检查第一个条目是否显示
                    boolean firstItemVisible = mListView.getFirstVisiblePosition() == 0;
                    //检查第一个条目顶部是否显示
                    boolean topOfFirstItemVisible = mListView.getChildAt(0).getTop() == 0;
                    //设置SwipeRefreashLayout可以刷新的控制
                    enable = firstItemVisible && topOfFirstItemVisible;
                }
                mSwipeLayout.setEnabled(enable);
            }
        });
        //为ViewPager设置监听
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                changeIndicator(position);
                //handler.sendMessage(Message.obtain(handler, ImageHandler.MSG_PAGE_CHANGED, position, 0));
            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
                /*switch (arg0) {
                    case ViewPager.SCROLL_STATE_DRAGGING:
                        handler.sendEmptyMessage(ImageHandler.MSG_KEEP_SILENT);
                        break;
                    case ViewPager.SCROLL_STATE_IDLE:
                        handler.sendEmptyMessageDelayed(ImageHandler.MSG_UPDATE_IMAGE, ImageHandler.MSG_DELAY);
                        break;
                    default:
                        break;
                }*/
            }
        });
        //下拉刷新
        mSwipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initData();
                mSwipeLayout.setRefreshing(false);
                Toast.makeText(MainActivity.this, "已刷新", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * 根据ViewPager改变导航点的状态
     * @param position
     */
    private void changeIndicator(int position){
        position %= 5;
        if (position<0){
            position = 5+position;
        }
        int indicatorCount = mIndicatorLinearLayout.getChildCount();
        for (int i = 0; i < indicatorCount; i++) {
            ImageView imgView = (ImageView) mIndicatorLinearLayout.getChildAt(i);
            imgView.setBackgroundResource(R.drawable.circle_unsel);
        }
        ImageView curImgView = (ImageView) mIndicatorLinearLayout.getChildAt(position);
        curImgView.setBackgroundResource(R.drawable.circle_sel);
    }


    private static class ImageHandler extends Handler {
        /**
         * 请求更新显示的View。
         */
        protected static final int MSG_UPDATE_IMAGE  = 1;
        /**
         * 请求暂停轮播。
         */
        protected static final int MSG_KEEP_SILENT   = 2;
        /**
         * 请求恢复轮播。
         */
        protected static final int MSG_BREAK_SILENT  = 3;
        /**
         * 记录最新的页号，当用户手动滑动时需要记录新页号，否则会使轮播的页面出错。
         * 例如当前如果在第一页，本来准备播放的是第二页，而这时候用户滑动到了末页，
         * 则应该播放的是第一页，如果继续按照原来的第二页播放，则逻辑上有问题。
         */
        protected static final int MSG_PAGE_CHANGED  = 4;

        //轮播间隔时间
        protected static final long MSG_DELAY = 3000;

        //使用弱引用避免Handler泄露.这里的泛型参数可以不是Activity，也可以是Fragment等
        private WeakReference<MainActivity> weakReference;
        private int currentItem = 0;

        protected ImageHandler(WeakReference<MainActivity> wk){
            weakReference = wk;
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            MainActivity activity = weakReference.get();
            if (activity==null){
                //Activity已经回收，无需再处理UI了
                return ;
            }
            //检查消息队列并移除未发送的消息，这主要是避免在复杂环境下消息出现重复等问题。
            if (activity.handler.hasMessages(MSG_UPDATE_IMAGE)){
                activity.handler.removeMessages(MSG_UPDATE_IMAGE);
            }
            switch (msg.what) {
                case MSG_UPDATE_IMAGE:
                    currentItem++;
                    activity.mViewPager.setCurrentItem(currentItem);
                    //准备下次播放
                    activity.handler.sendEmptyMessageDelayed(MSG_UPDATE_IMAGE, MSG_DELAY);
                    break;
                case MSG_KEEP_SILENT:
                    //只要不发送消息就暂停了
                    break;
                case MSG_BREAK_SILENT:
                    activity.handler.sendEmptyMessageDelayed(MSG_UPDATE_IMAGE, MSG_DELAY);
                    break;
                case MSG_PAGE_CHANGED:
                    //记录当前的页号，避免播放的时候页面显示不正确。
                    currentItem = msg.arg1;
                    break;
                default:
                    break;
            }
        }
    }
}
