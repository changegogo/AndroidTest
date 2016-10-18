package com.feicui.androidtest.androidtest.entity;

import java.util.List;

/**
 * Author: dlw on 2016/9/25 16:29
 * Email: dailongshao@126.com
 */
public class PictureData {

    /**
     * success : true
     * error : null
     * data : ["http://7xsct4.com1.z0.glb.clouddn.com/16-9-21/44621585.jpg","http://7xsct4.com1.z0.glb.clouddn.com/16-9-21/98887668.jpg","http://7xsct4.com1.z0.glb.clouddn.com/16-9-21/65897578.jpg","http://7xsct4.com1.z0.glb.clouddn.com/16-9-21/41331577.jpg","http://7xsct4.com1.z0.glb.clouddn.com/16-9-21/17331620.jpg"]
     */

    private boolean success;
    private Object error;
    private List<String> data;

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

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "PictureData{" +
                "success=" + success +
                ", error=" + error +
                ", data=" + data +
                '}';
    }
}
