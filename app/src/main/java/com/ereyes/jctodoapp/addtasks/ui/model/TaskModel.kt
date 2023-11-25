package com.ereyes.jctodoapp.addtasks.ui.model

import com.ereyes.jctodoapp.addtasks.data.database.entities.TaskEntity

/****
 * Project: JCTodoApp
 * From: com.ereyes.jctodoapp.addtasks.ui.model
 * Created by Edgar Reyes Gonzalez on 11/24/2023 at 9:43 AM
 * All rights reserved 2023.
 ****/
data class TaskModel(
    val id: Int = System.currentTimeMillis().hashCode(),
    val task: String,
    var selected: Boolean = false
)

fun TaskEntity.toDomain(): TaskModel = TaskModel(id, task, selected)
