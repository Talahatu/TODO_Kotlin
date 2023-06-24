package com.example.todo_kpb.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todo_kpb.R
import com.example.todo_kpb.viewModel.ListTodoVM
import com.google.android.material.floatingactionbutton.FloatingActionButton

class TodoListFragment : Fragment() {

    private lateinit var viewModel:ListTodoVM
    private val todoListAdapter = TodoListAdapter(arrayListOf()) { item -> viewModel.clearTask(item) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(ListTodoVM::class.java)
        viewModel.refresh()


        var recView = view.findViewById<RecyclerView>(R.id.recViewTodo)
        recView.layoutManager=LinearLayoutManager(context)
        recView.adapter=todoListAdapter

        view.findViewById<FloatingActionButton>(R.id.fabAddTodo).setOnClickListener {
            Navigation.findNavController(it).navigate(TodoListFragmentDirections.actionTodoListFragmentToCreateTodoFragment())
        }
        observe()
    }
    fun observe(){
        viewModel.todos.observe(viewLifecycleOwner, Observer {
            todoListAdapter.updateTodoList(it)
            var txtEmpty = view?.findViewById<TextView>(R.id.textViewEmpty)
            if(it.isEmpty()){
                txtEmpty?.visibility=View.VISIBLE
            }
            else{
                txtEmpty?.visibility=View.GONE
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_todo_list, container, false)
    }
}