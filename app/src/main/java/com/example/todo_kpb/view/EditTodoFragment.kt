package com.example.todo_kpb.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.todo_kpb.R
import com.example.todo_kpb.viewModel.DetailTodoVM

class EditTodoFragment : Fragment() {
    private  lateinit var viewModel:DetailTodoVM
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[DetailTodoVM::class.java]
        view.findViewById<TextView>(R.id.txtJudulTodo).text = "Edit Todo"
        var btnAdd = view.findViewById<Button>(R.id.btnAdd)
        val uuid = EditTodoFragmentArgs.fromBundle(requireArguments()).uuid
        btnAdd.text = "Save Changes"
        btnAdd.setOnClickListener {
            val txxTitle = view.findViewById<EditText>(R.id.txtTitle)
            val txtNotes = view.findViewById<EditText>(R.id.txtNotes)
            var radioGroup = view.findViewById<RadioGroup>(R.id.radioGroupPriority)
            var radio = view.findViewById<RadioButton>(radioGroup.checkedRadioButtonId)
            viewModel.update(txxTitle.text.toString(),txtNotes.text.toString(),radio.tag.toString().toInt(),uuid)
            Toast.makeText(view.context,"Todo updated!",Toast.LENGTH_SHORT).show()
            Navigation.findNavController(it).popBackStack()
        }
        viewModel.fetch(uuid)
        observe()
    }
    fun observe(){
        viewModel.todo.observe(viewLifecycleOwner, Observer {
            view?.findViewById<EditText>(R.id.txtTitle)?.setText(it.title)
            view?.findViewById<EditText>(R.id.txtNotes)?.setText(it.notes)
            when(it.priority){
                1-> view?.findViewById<RadioButton>(R.id.radioLow)?.isChecked = true
                2-> view?.findViewById<RadioButton>(R.id.radioMedium)?.isChecked = true
                else -> view?.findViewById<RadioButton>(R.id.radioHigh)?.isChecked = true
            }
        })
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_todo, container, false)
    }

}