package com.ereyes.jctodoapp.addtasks.data

import com.ereyes.jctodoapp.addtasks.data.database.dao.TaskDao
import com.ereyes.jctodoapp.addtasks.ui.model.toDomain
import kotlinx.coroutines.flow.map
import com.ereyes.jctodoapp.addtasks.data.database.entities.toDataBase
import com.ereyes.jctodoapp.addtasks.ui.model.TaskModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

/****
 * Project: JCTodoApp
 * From: com.ereyes.jctodoapp.addtasks.data
 * Created by Edgar Reyes Gonzalez on 11/25/2023 at 9:04 AM
 * All rights reserved 2023.
 ****/

@Singleton
class JcTodoAppRepository @Inject constructor(
    private val taskDao: TaskDao
) {

    val tasks: Flow<List<TaskModel>> = taskDao.getTasks().map { items -> items.map { it.toDomain() } }

    suspend fun addTask(taskModel: TaskModel){
        taskDao.addTask(taskModel.toDataBase())
    }

    suspend fun updateTask(task: TaskModel){
        taskDao.updateTask(task.toDataBase())
    }

    suspend fun deleteTask(task: TaskModel){
        taskDao.deleteTask(task.toDataBase())
    }
}