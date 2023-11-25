package com.ereyes.jctodoapp.addtasks.domain.usecase

import com.ereyes.jctodoapp.addtasks.data.JcTodoAppRepository
import com.ereyes.jctodoapp.addtasks.ui.model.TaskModel
import javax.inject.Inject

/****
 * Project: JCTodoApp
 * From: com.ereyes.jctodoapp.addtasks.domain.usecase
 * Created by Edgar Reyes Gonzalez on 11/25/2023 at 11:02 AM
 * All rights reserved 2023.
 ****/
class UpdateTaskUseCase @Inject constructor(
    private val repository: JcTodoAppRepository
) {
    suspend operator fun invoke(task: TaskModel){
        repository.updateTask(task)
    }
}