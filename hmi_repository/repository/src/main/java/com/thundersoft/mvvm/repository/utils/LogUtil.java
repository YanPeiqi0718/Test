/**
 * Copyright (c) 2020- ThunderSoft
 * All Rights Reserved by Thunder Software Technology Co., Ltd and its affiliates.
 * You may not use, copy, distribute, modify, transmit in any form this file
 * except in compliance with ThunderSoft in writing by applicable law.
 */

package com.thundersoft.mvvm.repository.utils;

import android.util.Log;

public class LogUtil {
    private static final String BUILD_TYPE = "ro.build.type";
    private static final String DEFAULT_BULIT_TYPE = "userdebug";
    private static final String USER_TYPE = "user";
    private static boolean sIsUser = false;

    /**
     * Print debug log info.
     *
     * @param tag  title
     * @param info description
     */
    public static void logD(String tag, String info) {
        if (!sIsUser) {
            Log.d(tag, info);
        }
    }

    /**
     * Print verbose log info.
     *
     * @param tag  title
     * @param info description
     */
    public static void logV(String tag, String info) {
        if (!sIsUser) {
            Log.v(tag, info);
        }
    }

    /**
     * Print info log info.
     *
     * @param tag  title
     * @param info description
     */
    public static void logI(String tag, String info) {
        if (!sIsUser) {
            Log.i(tag, info);
        }
    }

    /**
     * Print warn log info.
     *
     * @param tag  title
     * @param info description
     */
    public static void logW(String tag, String info) {
        if (!sIsUser) {
            Log.w(tag, info);
        }
    }

    /**
     * Print error log info.
     *
     * @param tag  title
     * @param info description
     */
    public static void logE(String tag, String info) {
        if (!sIsUser) {
            Log.e(tag, info);
        }
    }
}
