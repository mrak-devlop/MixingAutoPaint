package ru.kitfactory.mixingautopaint.presentation.listFragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import ru.kitfactory.mixingautopaint.R
import ru.kitfactory.mixingautopaint.presentation.model.PrintResText
import java.util.*

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
        val textData = PrintResText(
            getString(R.string.gramm_in_list),
            getString(R.string.parts_in_list), getString(R.string.mix1_list),
            getString(R.string.mix2_list), getString(R.string.mix3_list),
            getString(R.string.mix4_list), getString(R.string.mix5_list)
        )
        val adapter = PaintListAdapter(textData)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        viewModel.readAllData.observe(viewLifecycleOwner, { paint ->
            adapter.setData(paint)
        })

        val itemTouchHelperCallback = object : ItemTouchHelper.SimpleCallback(
            0, ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean = false

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                viewModel.removePaint(viewHolder.adapterPosition)
                adapter.notifyItemRemoved(viewHolder.adapterPosition)
            }
        }
        val myHelper = ItemTouchHelper(itemTouchHelperCallback)
        myHelper.attachToRecyclerView(recyclerView)
    }

        override fun onActivityCreated(savedInstanceState: Bundle?) {
            super.onActivityCreated(savedInstanceState)
            viewModel = ViewModelProvider(this).get(PaintListViewModel::class.java)
        }



}
