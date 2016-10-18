package com.feicui.androidtest.androidtest.entity;

import java.util.List;

/**
 * Author: dlw on 2016/9/25 16:37
 * Email: dailongshao@126.com
 */
public class WeatherData {

    /**
     * success : true
     * error : null
     * data : [{"district":"东城","weather":"大到暴雪"},{"district":"西城","weather":"大到暴雪"},{"district":"海淀","weather":"雷阵雨"},{"district":"朝阳","weather":"特强沙尘暴"},{"district":"丰台","weather":"中到大雨"},{"district":"门头沟","weather":"冻雨"},{"district":"石景山","weather":"飑线"},{"district":"房山","weather":"中到大雨"},{"district":"通州","weather":"特强沙尘暴"},{"district":"顺义","weather":"特强沙尘暴"},{"district":"昌平","weather":"中到大雨"},{"district":"大兴","weather":"浮尘"},{"district":"怀柔","weather":"冻雨"},{"district":"延庆","weather":"冻雨"},{"district":"密云","weather":"特强沙尘暴"},{"district":"平谷","weather":"重度霾"}]
     */

    private boolean success;
    private Object error;
    /**
     * district : 东城
     * weather : 大到暴雪
     */

    private List<DataBean> data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Object getError() {
        return error;
    }

    public void setError(Object error) {
        this.error = error;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        private String district;
        private String weather;

        public String getDistrict() {
            return district;
        }

        public void setDistrict(String district) {
            this.district = district;
        }

        public String getWeather() {
            return weather;
        }

        public void setWeather(String weather) {
            this.weather = weather;
        }
    }

    @Override
    public String toString() {
        return "WeatherData{" +
                "success=" + success +
                ", error=" + error +
                ", data=" + data +
                '}';
    }
}
