/**
 * Copyright (c) 2020- ThunderSoft
 * All Rights Reserved by Thunder Software Technology Co., Ltd and its affiliates.
 * You may not use, copy, distribute, modify, transmit in any form this file
 * except in compliance with ThunderSoft in writing by applicable law.
 */

package com.thundersoft.mvvm.service.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

//@Entity注释一个实体 tableName 数据库中的表名
@Entity(tableName = "demo_table")
public class DemoEntity {
    //主键 自增
    @PrimaryKey(autoGenerate = true)
    public int id;

    //demo_message才是真正在表中对应的字段名，message是该对象中的变量。
    @ColumnInfo(name="demo_message")
    public String message;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
