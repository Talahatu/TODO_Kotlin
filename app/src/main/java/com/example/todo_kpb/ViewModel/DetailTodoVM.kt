package com.example.todo_kpb.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.todo_kpb.model.Todo
import com.example.todo_kpb.Util.buildDb
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class DetailTodoVM(application: Application):AndroidViewModel(application),CoroutineScope {
    val todo = MutableLiveData<Todo>()
    private val job = Job()
    override val coroutineContext: CoroutineContext
        get() = job+Dispatchers.IO
    fun addTodo(list:List<Todo>){
        launch {
            val db = buildDb(getApplication())
            db.todoDao().insertAll(*list.toTypedArray())
        }
    }
    fun fetch(uuid:Int){
        launch {
            val db = buildDb(getApplication())
            todo.postValue( db.todoDao().selectTodo(uuid))
        }
    }
    fun update(title:String,notes:String,priority:Int,uuid:Int){
        launch {
            val db = buildDb(getApplication())
            db.todoDao().update(title,notes,priority,uuid)
        }
    }
}
