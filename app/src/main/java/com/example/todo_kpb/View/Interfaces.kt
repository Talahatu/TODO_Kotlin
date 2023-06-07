package com.example.todo_kpb.View

import android.view.View
import android.widget.CompoundButton
import com.example.todo_kpb.model.Todo

interface TodoCheckedChangeListener{
    fun onCheckChange(cb:CompoundButton,isChecked:Boolean,obj:Todo)
}
interface TodoEditClick{
    fun onTodoEditClick(v: View)
}