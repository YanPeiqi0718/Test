/**
 * Copyright (c) 2020- ThunderSoft
 * All Rights Reserved by Thunder Software Technology Co., Ltd and its affiliates.
 * You may not use, copy, distribute, modify, transmit in any form this file
 * except in compliance with ThunderSoft in writing by applicable law.
 */

package com.thundersoft.mvvm.service.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.thundersoft.mvvm.service.entity.DemoEntity;

import java.util.List;

@Dao
public interface DemoDao {
    @Insert//增
    public void insertDemo(DemoEntity demoEntity);

    @Insert
    public void insertDemos(DemoEntity demoEntity1, DemoEntity demoEntity2);

    @Insert
    public void insertDemosAndMore(DemoEntity student, List<DemoEntity> classmate);

    @Update//改
    public void updateDemos(DemoEntity ... demoEntities);

    @Delete//删
    public void deleteDemos(DemoEntity ... demoEntities);

    @Query( "SELECT * FROM demo_table" )//查
    public DemoEntity[] getAllDemo();

    @Query( "SELECT * FROM demo_table WHERE demo_message LIKE:message" )//带参数
    public DemoEntity[] getDemoByMessage(String message);
}
