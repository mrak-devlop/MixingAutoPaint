package ru.kitfactory.mixingautopaint.presentation

import android.app.Activity
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit
import com.google.android.material.floatingactionbutton.FloatingActionButton
import ru.kitfactory.mixingautopaint.R

class PaintListFragment : Fragment() {
    private lateinit var addButton: FloatingActionButton
    companion object {
        fun newInstance() = PaintListFragment()
    }

    private lateinit var viewModel: PaintListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_paint_list, container, false)
        addButton = view.findViewById(R.id.addMixButton) as FloatingActionButton
        addButton.setOnClickListener {
         activity?.supportFragmentManager?.beginTransaction()?.
         replace(R.id.fragment_container_view, AddMixPaintFragment())?.
         addToBackStack("List")?.commit()
        }
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(PaintListViewModel::class.java)
        // TODO: Use the ViewModel
    }


}