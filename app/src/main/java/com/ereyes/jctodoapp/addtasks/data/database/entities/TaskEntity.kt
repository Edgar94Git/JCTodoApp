package com.ereyes.jctodoapp.addtasks.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ereyes.jctodoapp.addtasks.ui.model.TaskModel

/****
 * Project: JCTodoApp
 * From: com.ereyes.jctodoapp.addtasks.data.database.entities
 * Created by Edgar Reyes Gonzalez on 11/25/2023 at 8:37 AM
 * All rights reserved 2023.
 ****/
@Entity(tableName = "T_Task")
data class TaskEntity(
    @PrimaryKey
    @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "task") val task: String,
    @ColumnInfo(name = "selected") val selected: Boolean = false
)

fun TaskModel.toDataBase() = TaskEntity(id, task, selected)
