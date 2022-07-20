/**
 * Copyright (c) 2020- ThunderSoft
 * All Rights Reserved by Thunder Software Technology Co., Ltd and its affiliates.
 * You may not use, copy, distribute, modify, transmit in any form this file
 * except in compliance with ThunderSoft in writing by applicable law.
 */

package com.thundersoft.mvvm.common.lib.entity;

/**
 * Music Lyrics.
 */

public class AudioLrcBean {
    private String mContent;
    private String mTimeText;
    private long mTime;

    public String getContent() {
        return mContent;
    }

    public void setContent(String content) {
        mContent = content;
    }

    public String getTimeText() {
        return mTimeText;
    }

    public void setTimeText(String timeText) {
        mTimeText = timeText;
    }

    public long getTime() {
        return mTime;
    }

    public void setTime(long time) {
        mTime = time;
    }
}