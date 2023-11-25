package com.ereyes.jctodoapp.addtasks.ui

import com.ereyes.jctodoapp.addtasks.ui.model.TaskModel

/****
 * Project: JCTodoApp
 * From: com.ereyes.jctodoapp.addtasks.ui
 * Created by Edgar Reyes Gonzalez on 11/25/2023 at 9:48 AM
 * All rights reserved 2023.
 ****/
sealed interface TaskUiState{
    object Loading: TaskUiState
    data class Error(val throwable: Throwable): TaskUiState
    data class Success(val tasks: List<TaskModel>): TaskUiState
}
