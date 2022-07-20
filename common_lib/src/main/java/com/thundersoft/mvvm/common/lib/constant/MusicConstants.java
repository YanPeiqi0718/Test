/**
 * Copyright (c) 2020- ThunderSoft
 * All Rights Reserved by Thunder Software Technology Co., Ltd and its affiliates.
 * You may not use, copy, distribute, modify, transmit in any form this file
 * except in compliance with ThunderSoft in writing by applicable law.
 */

package com.thundersoft.mvvm.common.lib.constant;

public class MusicConstants {

    public static class LoadFragmentType {
        public static final int RADIO_FM_FRAGMENT = 0x10001;
        public static final int RADIO_AM_FRAGMENT = 0x10002;
        public static final int MUSIC_MAIN_FRAGMENT = 0x10003;
    }

    public static class FragmentLabel {
        public static final String RADIO_FM_FRAGMENT = "RadioFmFragment";
        public static final String RADIO_AM_FRAGMENT = "RadioAmFragment";
        public static final String MUSIC_MAIN_FRAGMENT = "MusicFragment";
    }

    public static class UsbMusicLrc {
        public static final int LRC_TEXT_SIZE = 30;
        public static final int INDICATOR_TOUCH_DELAY = 1000;
        public static final int ICON_LINE_GAP = 5;
        public static final int INDICATOR_LINE_WIDTH = 1;
        public static final float OVER_SCROLLER = 0.1f;
        public static final int PLAY_RECT_TOP = 2;
        public static final float LRC_HEIGHT = 2f;
        public static final int LINE_POSITION = 1;
        public static final float TIME_WIDTH = 1.5f;
        public static final float BASE_X = 1.1f;
        public static final float STATIC_LAYOUT_SPACING_X = 1f;
        public static final float STATIC_LAYOUT_SPACING_Y = 0f;
        public static final int LRC_COUNT_INDEX = 1;
        public static final int LRC_ANIMATOR_DURATION = 300;
        public static final float LRC_MOVE_Y = 3.5f;
        public static final int SCROLL_TO_POSITION = 0;
    }

    public static class FormatPlayTime {
        public static final int FORMATTER_HOUR = 24;
        public static final int FORMATTER_SECOND = 1000;
        public static final int FORMATTER_MINUTE = 60;
        public static final int FORMATTER_MILLISECOND = 3600;
    }

}