package com.ereyes.jctodoapp.addtasks.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
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

    fun onDialogClose(){
        _showDialog.value = false
    }

    fun onTaskCreate(task: String){
        onDialogClose()
        Log.i("My task", task)
    }

    fun onShowDialogClick(){
        _showDialog.value = true
    }
}