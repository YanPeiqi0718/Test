/**
 * Copyright (c) 2020- ThunderSoft
 * All Rights Reserved by Thunder Software Technology Co., Ltd and its affiliates.
 * You may not use, copy, distribute, modify, transmit in any form this file
 * except in compliance with ThunderSoft in writing by applicable law.
 */

package com.thundersoft.mvvm.common.lib.utils;

import com.thundersoft.mvvm.common.lib.constant.MusicConstants;

import java.util.Formatter;
import java.util.Locale;

public class MusicUtils {
    private static final String TIME_FORMAT_HOUR_MINUTE_SECOND = "%d:%02d:%02d";
    private static final String TIME_FORMAT_HOUR_MINUTE = "%02d:%02d";
    private static final String INITIAL_TIME = "00:00";
    private static volatile MusicUtils sInstance;

    /**
     * Initialization.
     */
    public static MusicUtils getInstance() {
        if (null == sInstance) {
            synchronized (MusicUtils.class) {
                if (null == sInstance) {
                    sInstance = new MusicUtils();
                }
            }
        }
        return sInstance;
    }

    /**
     * Duration format.
     *
     * @param timeMs Unit millisecond
     * @return Formatted string time
     */
    public String stringForAudioTime(long timeMs) {
        if (timeMs <= 0 || timeMs >= MusicConstants.FormatPlayTime.FORMATTER_HOUR
                * MusicConstants.FormatPlayTime.FORMATTER_MINUTE
                * MusicConstants.FormatPlayTime.FORMATTER_MINUTE
                * MusicConstants.FormatPlayTime.FORMATTER_SECOND) {
            return INITIAL_TIME;
        }
        long totalSeconds = timeMs / MusicConstants.FormatPlayTime.FORMATTER_SECOND;
        int seconds = (int) (totalSeconds % MusicConstants.FormatPlayTime.FORMATTER_MINUTE);
        int minutes = (int) ((totalSeconds / MusicConstants.FormatPlayTime.FORMATTER_MINUTE)
                % MusicConstants.FormatPlayTime.FORMATTER_MINUTE);
        int hours = (int) (totalSeconds / MusicConstants.FormatPlayTime.FORMATTER_MILLISECOND);
        StringBuilder stringBuilder = new StringBuilder();
        Formatter formatter = new Formatter(stringBuilder, Locale.getDefault());
        if (hours > 0) {
            return formatter.format(TIME_FORMAT_HOUR_MINUTE_SECOND, hours, minutes, seconds)
                    .toString();
        } else {
            return formatter.format(TIME_FORMAT_HOUR_MINUTE, minutes, seconds).toString();
        }
    }
}