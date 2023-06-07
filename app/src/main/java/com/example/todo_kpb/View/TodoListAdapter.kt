package com.example.todo_kpb.View

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.CompoundButton
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.todo_kpb.model.Todo
import com.example.todo_kpb.R
import com.example.todo_kpb.databinding.TodoItemLayoutBinding

class TodoListAdapter(val todoList:ArrayList<Todo>,val adapterOnClick:(Todo)->Unit):RecyclerView.Adapter<TodoListAdapter.TodoViewHolder>(),TodoCheckedChangeListener,TodoEditClick {
    class TodoViewHolder(var view: TodoItemLayoutBinding):RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<TodoItemLayoutBinding>(inflater,R.layout.todo_item_layout,parent,false)
        return TodoViewHolder(view)
    }

    override fun getItemCount(): Int {
        return todoList.size
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
//        var checktask = holder.view.findViewById<CheckBox>(R.id.checkTask)
//        checktask.text = todoList[position].title
//        checktask.setOnCheckedChangeListener{ _, isChecked->
//            if(isChecked) adapterOnClick(todoList[position])
//        }
//        var pencilEdit = holder.view.findViewById<ImageView>(R.id.imgEdit)
//        pencilEdit.bringToFront()
//        pencilEdit.setOnClickListener {
//            Navigation.findNavController(it).navigate( TodoListFragmentDirections.actionTodoListFragmentToEditTodoFragment(todoList[position].uuid))
//        }
//        holder.view.todo = todoList[position]
       // holder.view.listener = this
       // holder.view.editListener = this
    }
    fun updateTodoList(newTodoList:List<Todo>){
        todoList.clear()
        todoList.addAll(newTodoList)
        notifyDataSetChanged()
    }

    override fun onCheckChange(cb: CompoundButton, isChecked: Boolean, obj: Todo) {
        if(isChecked){
            adapterOnClick(obj)
        }
    }

    override fun onTodoEditClick(v: View) {
        val uuid = v.tag.toString().toInt()
        val action = TodoListFragmentDirections.actionTodoListFragmentToEditTodoFragment(uuid)
        Navigation.findNavController(v).navigate(action)
    }

}