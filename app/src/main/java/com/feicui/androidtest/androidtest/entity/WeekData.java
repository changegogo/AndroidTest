package com.feicui.androidtest.androidtest.entity;

/**
 * Author: dlw on 2016/9/25 16:35
 * Email: dailongshao@126.com
 */
public class WeekData {

    /**
     * success : true
     * error : null
     * data : {"Mon":14,"Tue":12,"Wed":21,"Thu":18,"Fri":17,"Sat":27,"Sun":25}
     */

    private boolean success;
    private Object error;
    /**
     * Mon : 14
     * Tue : 12
     * Wed : 21
     * Thu : 18
     * Fri : 17
     * Sat : 27
     * Sun : 25
     */

    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private int Mon;
        private int Tue;
        private int Wed;
        private int Thu;
        private int Fri;
        private int Sat;
        private int Sun;

        public int getMon() {
            return Mon;
        }

        public void setMon(int Mon) {
            this.Mon = Mon;
        }

        public int getTue() {
            return Tue;
        }

        public void setTue(int Tue) {
            this.Tue = Tue;
        }

        public int getWed() {
            return Wed;
        }

        public void setWed(int Wed) {
            this.Wed = Wed;
        }

        public int getThu() {
            return Thu;
        }

        public void setThu(int Thu) {
            this.Thu = Thu;
        }

        public int getFri() {
            return Fri;
        }

        public void setFri(int Fri) {
            this.Fri = Fri;
        }

        public int getSat() {
            return Sat;
        }

        public void setSat(int Sat) {
            this.Sat = Sat;
        }

        public int getSun() {
            return Sun;
        }

        public void setSun(int Sun) {
            this.Sun = Sun;
        }
    }

    @Override
    public String toString() {
        return "WeekData{" +
                "success=" + success +
                ", error=" + error +
                ", data=" + data +
                '}';
    }
}
