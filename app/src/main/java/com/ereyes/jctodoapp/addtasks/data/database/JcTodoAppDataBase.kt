package com.ereyes.jctodoapp.addtasks.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ereyes.jctodoapp.addtasks.data.database.dao.TaskDao
import com.ereyes.jctodoapp.addtasks.data.database.entities.TaskEntity

/****
 * Project: JCTodoApp
 * From: com.ereyes.jctodoapp.addtasks.data.database
 * Created by Edgar Reyes Gonzalez on 11/25/2023 at 8:38 AM
 * All rights reserved 2023.
 ****/

@Database(entities = [TaskEntity::class], version = 1)
abstract class JcTodoAppDataBase: RoomDatabase() {
    abstract fun getTaskDao(): TaskDao
}