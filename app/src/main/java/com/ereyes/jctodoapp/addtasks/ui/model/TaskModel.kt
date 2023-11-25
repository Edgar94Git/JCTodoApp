package com.ereyes.jctodoapp.addtasks.ui.model

/****
 * Project: JCTodoApp
 * From: com.ereyes.jctodoapp.addtasks.ui.model
 * Created by Edgar Reyes Gonzalez on 11/24/2023 at 9:43 AM
 * All rights reserved 2023.
 ****/
data class TaskModel(val id: Long = System.currentTimeMillis(), val task: String, val selected: Boolean = false)
