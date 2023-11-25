package com.ereyes.jctodoapp.addtasks.domain.usecase

import com.ereyes.jctodoapp.addtasks.data.JcTodoAppRepository
import com.ereyes.jctodoapp.addtasks.ui.model.TaskModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/****
 * Project: JCTodoApp
 * From: com.ereyes.jctodoapp.addtasks.domain.usecase
 * Created by Edgar Reyes Gonzalez on 11/25/2023 at 9:29 AM
 * All rights reserved 2023.
 ****/
class GetTasksUseCase @Inject constructor(
    private val repository: JcTodoAppRepository
) {

    operator fun invoke(): Flow<List<TaskModel>> {
        return repository.tasks
    }
}