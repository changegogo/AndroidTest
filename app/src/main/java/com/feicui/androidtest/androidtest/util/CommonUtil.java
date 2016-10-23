package com.feicui.androidtest.androidtest.util;

import android.content.Context;
import android.graphics.Point;
import android.view.WindowManager;

import java.util.UUID;

public class CommonUtil {

    //获取随机uuid
	public static String getUuid() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString().replace("-","");
	}
    //获取屏幕的宽高
    public static Point getScreenWidthHeight(Context context){
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Point point = new Point();
        wm.getDefaultDisplay().getSize(point);
        return point;
    }
}
