package com.ereyes.jctodoapp.addtasks.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ereyes.jctodoapp.addtasks.domain.usecase.AddTaskUseCase
import com.ereyes.jctodoapp.addtasks.domain.usecase.DeleteTaskUseCase
import com.ereyes.jctodoapp.addtasks.domain.usecase.GetTasksUseCase
import com.ereyes.jctodoapp.addtasks.domain.usecase.UpdateTaskUseCase
import com.ereyes.jctodoapp.addtasks.ui.TaskUiState.Success
import com.ereyes.jctodoapp.addtasks.ui.TaskUiState.Loading
import com.ereyes.jctodoapp.addtasks.ui.TaskUiState.Error
import com.ereyes.jctodoapp.addtasks.ui.model.TaskModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

/****
 * Project: JCTodoApp
 * From: com.ereyes.jctodoapp.addtasks.ui
 * Created by Edgar Reyes Gonzalez on 11/22/2023 at 9:12 AM
 * All rights reserved 2023.
 ****/

@HiltViewModel
class TasksViewModel @Inject constructor(
    private val addTaskUseCase: AddTaskUseCase,
    getTasksUseCase: GetTasksUseCase,
    private val updateTaskUseCase: UpdateTaskUseCase,
    private val deleteTaskUseCase: DeleteTaskUseCase
): ViewModel() {

    val uiState: StateFlow<TaskUiState> = getTasksUseCase().map(::Success)
        .catch { Error(it) }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), Loading)

    private var _showDialog: MutableLiveData<Boolean> = MutableLiveData<Boolean>()
    val showDialog: LiveData<Boolean> = _showDialog

    private var _showDialogConfirm: MutableLiveData<Boolean> = MutableLiveData<Boolean>()
    val showDialogConfirm: LiveData<Boolean> = _showDialogConfirm

    //private var _tastks = mutableStateListOf<TaskModel>()
    //val tasks: List<TaskModel> = _tastks

    fun onDialogClose(){
        _showDialog.value = false
    }

    fun onTaskCreate(task: String){
        onDialogClose()
        //_tastks.add(TaskModel(task = task))
        viewModelScope.launch {
            addTaskUseCase(TaskModel(task = task))
        }
    }

    fun onShowDialogClick(){
        _showDialog.value = true
    }

    fun onCheckBoxSelected(task: TaskModel) {
//        val index = _tastks.indexOf(task)
//        _tastks[index] = _tastks[index].let {
//            it.copy(selected = !it.selected)
//        }
        viewModelScope.launch {
            updateTaskUseCase(task.copy(selected = !task.selected))
        }
    }

    fun onItemRemove(task: TaskModel) {
//        val taskRemove = _tastks.find { it.id == task.id }
//        _tastks.remove(taskRemove)
//        onDialogConfirmClose()
        viewModelScope.launch {
            deleteTaskUseCase(task)
        }
    }

    fun onDialogConfirmClose(){
        _showDialogConfirm.value = false
    }

    fun onShowDialogConfirmClick(){
        _showDialogConfirm.value = true
    }
}