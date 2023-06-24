package com.example.todo_kpb.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf
import com.example.todo_kpb.model.Todo
import com.example.todo_kpb.R
import com.example.todo_kpb.util.NotificationHelper
import com.example.todo_kpb.util.TodoWorker
import com.example.todo_kpb.viewModel.DetailTodoVM
import java.util.concurrent.TimeUnit

class CreateTodoFragment : Fragment() {
    private lateinit var viewModel:DetailTodoVM
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(DetailTodoVM::class.java)
        val btnAdd = view.findViewById<Button>(R.id.btnAdd)
        btnAdd.setOnClickListener {
            val txxTitle = view.findViewById<EditText>(R.id.txtTitle)
            val txtNotes = view.findViewById<EditText>(R.id.txtNotes)
            var radioGroup = view.findViewById<RadioGroup>(R.id.radioGroupPriority)
            var radio = view.findViewById<RadioButton>(radioGroup.checkedRadioButtonId)

            var todo = Todo(txxTitle.text.toString(),txtNotes.text.toString(),radio.tag.toString().toInt())
            val list = listOf(todo)
            viewModel.addTodo(list)

            val myWorkRequest = OneTimeWorkRequestBuilder<TodoWorker>()
                .setInitialDelay(10,TimeUnit.SECONDS)
                .setInputData(workDataOf("title" to "Todo Created","message" to "Todo successfully added!"))
                .build()
            WorkManager.getInstance(requireContext()).enqueue(myWorkRequest)
            Navigation.findNavController(it).popBackStack()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_todo, container, false)
    }
}