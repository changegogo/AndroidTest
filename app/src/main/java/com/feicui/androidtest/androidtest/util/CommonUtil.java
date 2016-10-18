package com.feicui.androidtest.androidtest.util;

import java.util.UUID;

public class CommonUtil {

	public static String getUuid() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
	}
}
