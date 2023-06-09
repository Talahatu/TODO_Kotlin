package com.example.todo_kpb.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.todo_kpb.model.Todo
import com.example.todo_kpb.util.buildDb
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class ListTodoVM(application: Application):AndroidViewModel(application),CoroutineScope {

    val todos = MutableLiveData<List<Todo>>()
    val errorStatus = MutableLiveData<Boolean>()
    val loadingStatus = MutableLiveData<Boolean>()
    private var job= Job()

    override val coroutineContext: CoroutineContext
        get() = job+Dispatchers.IO

    fun refresh(){
        loadingStatus.value = true
        errorStatus.value = false
        launch {
            val db = buildDb(getApplication())
            todos.postValue(db.todoDao().selectAllTodo())
        }
    }
    fun updateTodoCheck(isDone:Boolean,uuid:Int){
        launch {
            Log.d("Update TODO","CHECKED!!!")
            val db = buildDb(getApplication())
            db.todoDao().updateTodoCheck(!isDone,uuid)
            todos.postValue(db.todoDao().selectAllTodo())
        }
    }
    fun clearTask(todo: Todo){
        launch {
            val db = buildDb(getApplication())
            db.todoDao().deleteTodo(todo)
            todos.postValue(db.todoDao().selectAllTodo())
        }
    }
}