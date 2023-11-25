package com.ereyes.jctodoapp.addtasks.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.ereyes.jctodoapp.addtasks.data.database.entities.TaskEntity
import kotlinx.coroutines.flow.Flow

/****
 * Project: JCTodoApp
 * From: com.ereyes.jctodoapp.addtasks.data.database.dao
 * Created by Edgar Reyes Gonzalez on 11/25/2023 at 8:36 AM
 * All rights reserved 2023.
 ****/

@Dao
interface TaskDao {

    @Query("SELECT * FROM T_Task")
    fun getTasks(): Flow<List<TaskEntity>>

    @Insert
    suspend fun addTask(task: TaskEntity)

    @Update
    suspend fun updateTask(task: TaskEntity)

    @Delete
    suspend fun deleteTask(task: TaskEntity)
}