package ru.kitfactory.mixingautopaint.presentation.listFragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import ru.kitfactory.mixingautopaint.R

class PaintListFragment : Fragment() {
    private lateinit var addButton: FloatingActionButton
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewModel: PaintListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_paint_list, container, false)
        addButton = view.findViewById(R.id.addMixButton) as FloatingActionButton
        recyclerView = view.findViewById(R.id.paint_mix_recycler_view) as RecyclerView
        return view
    }
    override fun onStart() {
        super.onStart()
        addButton.setOnClickListener {
           findNavController().navigate(R.id.action_paintListFragment_to_addMixPaintFragment)
        }
        val adapter = PaintListAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        viewModel.readAllData.observe(viewLifecycleOwner, { paint ->
            adapter.setData(paint)
        })
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(PaintListViewModel::class.java)
    }


}