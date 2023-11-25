package com.ereyes.jctodoapp.addtasks.ui

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ereyes.jctodoapp.addtasks.ui.model.TaskModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/****
 * Project: JCTodoApp
 * From: com.ereyes.jctodoapp.addtasks.ui
 * Created by Edgar Reyes Gonzalez on 11/22/2023 at 9:12 AM
 * All rights reserved 2023.
 ****/

@HiltViewModel
class TasksViewModel @Inject constructor(): ViewModel() {

    private var _showDialog: MutableLiveData<Boolean> = MutableLiveData<Boolean>()
    val showDialog: LiveData<Boolean> = _showDialog

    private var _showDialogConfirm: MutableLiveData<Boolean> = MutableLiveData<Boolean>()
    val showDialogConfirm: LiveData<Boolean> = _showDialogConfirm

    private var _tastks = mutableStateListOf<TaskModel>()
    val tasks: List<TaskModel> = _tastks

    fun onDialogClose(){
        _showDialog.value = false
    }

    fun onTaskCreate(task: String){
        onDialogClose()
        _tastks.add(TaskModel(task = task))
    }

    fun onShowDialogClick(){
        _showDialog.value = true
    }

    fun onCheckBoxSelected(task: TaskModel) {
        val index = _tastks.indexOf(task)
        _tastks[index] = _tastks[index].let {
            it.copy(selected = !it.selected)
        }
    }

    fun onItemRemove(task: TaskModel) {
        val taskRemove = _tastks.find { it.id == task.id }
        _tastks.remove(taskRemove)
        onDialogConfirmClose()
    }

    fun onDialogConfirmClose(){
        _showDialogConfirm.value = false
    }

    fun onShowDialogConfirmClick(){
        _showDialogConfirm.value = true
    }
}