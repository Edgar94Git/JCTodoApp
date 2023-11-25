package com.ereyes.jctodoapp.addtasks.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import com.ereyes.jctodoapp.addtasks.ui.model.TaskModel

/****
 * Project: JCTodoApp
 * From: com.ereyes.jctodoapp.addtasks.ui
 * Created by Edgar Reyes Gonzalez on 11/22/2023 at 9:10 AM
 * All rights reserved 2023.
 ****/

@Composable
fun TasksScreen(tasksViewModel: TasksViewModel) {

    val showDialog: Boolean by tasksViewModel.showDialog.observeAsState(false)
    val lifecycle = LocalLifecycleOwner.current.lifecycle

    val uiState by produceState<TaskUiState>(
        initialValue = TaskUiState.Loading,
        key1 = lifecycle,
        key2 = tasksViewModel
    ) {
        lifecycle.repeatOnLifecycle(state = Lifecycle.State.STARTED) {
            tasksViewModel.uiState.collect { value = it }
        }
    }

    when (uiState) {
        is TaskUiState.Error -> {}
        TaskUiState.Loading -> {}
        is TaskUiState.Success -> {
            Box(modifier = Modifier.fillMaxWidth()) {
                TaskList((uiState as TaskUiState.Success).tasks, tasksViewModel)
                FabOpenDialog(Modifier.align(Alignment.BottomEnd), tasksViewModel)
                AddTasksDialog(
                    showDialog,
                    onDismiss = { tasksViewModel.onDialogClose() },
                    onTaskAdded = { tasksViewModel.onTaskCreate(it) }
                )
            }
        }
    }
}

@Composable
fun TaskList(tasks: List<TaskModel>, tasksViewModel: TasksViewModel) {
    //val tasks: List<TaskModel> = tasksViewModel.tasks
    LazyColumn {
        items(tasks, key = { it.id }) { task ->
            ItemTask(task, tasksViewModel)
        }
    }
}

@Composable
fun ItemTask(task: TaskModel, tasksViewModel: TasksViewModel) {

    val showDialogConfirm: Boolean by tasksViewModel.showDialogConfirm.observeAsState(initial = false)
    val taskDelete: TaskModel by tasksViewModel.task.observeAsState(initial = TaskModel(task = ""))

    DeleteTaskDialog(
        show = showDialogConfirm,
        task = taskDelete,
        onDismiss = { tasksViewModel.onDialogConfirmClose() },
        onTaskDelete = { tasksViewModel.onItemRemove(it) }
    )
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .pointerInput(Unit) {
                detectTapGestures(onLongPress = {
                    tasksViewModel.onShowDialogConfirmClick(task)
                })
            },
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = task.task,
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 4.dp)
            )
            Checkbox(
                checked = task.selected,
                onCheckedChange = { tasksViewModel.onCheckBoxSelected(task) })
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTasksDialog(show: Boolean, onDismiss: () -> Unit, onTaskAdded: (String) -> Unit) {
    var myTask: String by remember { mutableStateOf("") }
    if (show) {
        Dialog(onDismissRequest = { onDismiss() }) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(16.dp)
            ) {
                Text(
                    text = "Añade tu tarea",
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
                Spacer(modifier = Modifier.height(16.dp))
                TextField(
                    value = myTask,
                    onValueChange = { myTask = it },
                    singleLine = true,
                    maxLines = 1
                )
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = {
                        onTaskAdded(myTask)
                        myTask = ""
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "Añadir tarea")
                }
            }
        }
    }
}

@Composable
fun FabOpenDialog(modifier: Modifier, tasksViewModel: TasksViewModel) {
    FloatingActionButton(
        onClick = { tasksViewModel.onShowDialogClick() },
        modifier = modifier.padding(16.dp)
    ) {
        Icon(
            imageVector = Icons.Filled.Add,
            contentDescription = ""
        )
    }
}

@Composable
fun DeleteTaskDialog(
    show: Boolean,
    task: TaskModel,
    onDismiss: () -> Unit,
    onTaskDelete: (TaskModel) -> Unit
) {
    if (show) {
        Dialog(onDismissRequest = { }) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(16.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.CenterHorizontally)
                ) {
                    Icon(
                        imageVector = Icons.Filled.Delete,
                        contentDescription = "",
                        Modifier
                            .align(Alignment.CenterVertically)
                            .padding(end = 16.dp),
                        tint = Color.Red
                    )
                    Text(
                        text = "¿Está seguro de eliminar la tarea?",
                        modifier = Modifier.weight(1f),
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                Row(modifier = Modifier.fillMaxWidth()) {
                    Button(
                        onClick = { onDismiss() },
                        modifier = Modifier
                            .weight(1f)
                            .padding(start = 8.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Gray,
                            contentColor = Color.White
                        )
                    ) {
                        Text(text = "Cancelar", fontSize = 18.sp)
                    }
                    Button(
                        onClick = { onTaskDelete(task) },
                        modifier = Modifier
                            .weight(1f)
                            .padding(end = 8.dp)
                    ) {
                        Text(text = "Aceptar", fontSize = 18.sp)
                    }
                }
            }
        }
    }
}
