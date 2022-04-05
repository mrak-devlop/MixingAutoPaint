package ru.kitfactory.mixingautopaint.presentation.listFragment

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import ru.kitfactory.mixingautopaint.R
import ru.kitfactory.mixingautopaint.R.drawable
import ru.kitfactory.mixingautopaint.presentation.model.PrintResText


class PaintListFragment : Fragment() {
    private lateinit var addButton: FloatingActionButton
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewModel: PaintListViewModel
    private lateinit var trashBinIcon: Drawable


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_paint_list, container, false)
        addButton = view.findViewById(R.id.addMixButton) as FloatingActionButton
        recyclerView = view.findViewById(R.id.paint_mix_recycler_view) as RecyclerView
        @SuppressLint("UseCompatLoadingForDrawables")
        trashBinIcon = activity?.resources?.getDrawable(
            drawable.ic_baseline_delete_forever_24, null) as Drawable
        return view
    }
    override fun onStart() {
        super.onStart()
        // вызываем фрагмент добавления краски
        addButton.setOnClickListener {
            findNavController().navigate(R.id.action_paintListFragment_to_addMixPaintFragment)
        }
        // получаем текстовые данные из строковых ресурсов для отправки в адаптер
        val textData = PrintResText(
            getString(R.string.gramm_in_list),
            getString(R.string.parts_in_list), getString(R.string.mix1_list),
            getString(R.string.mix2_list), getString(R.string.mix3_list),
            getString(R.string.mix4_list), getString(R.string.mix5_list)
        )
        // заполняем recyclerview
        val adapter = PaintListAdapter(textData)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        viewModel.readAllData.observe(viewLifecycleOwner, { paint ->
            adapter.setData(paint)
        })

        // удаление свайпом вправо
        val itemTouchHelperCallback = object : ItemTouchHelper.SimpleCallback(
            0, ItemTouchHelper.RIGHT
        ) {

            // отключаем перетаскивание
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean = false
            // обработчик свайпа
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                // создаём диалог подтверждения
                val builder = AlertDialog.Builder(requireContext())
                // если подтверждено удаляем
                builder.setPositiveButton("Yes"){_, _ ->
                    viewModel.removePaint(viewHolder.adapterPosition)
                    adapter.notifyItemRemoved(viewHolder.adapterPosition)
                }
                // если не подтверждено ничего не делаем
                builder.setNegativeButton("NO"){_, _ ->
                    viewModel.readAllData.observe(viewLifecycleOwner, { paint ->
                        adapter.setData(paint)
                    })
                }
                // устанавливаем заголовок и текст диалога
                builder.setTitle("Confirm delete?")
                builder.setMessage("Are you sure to want to delete")
                // показать диалог
                builder.create().show()
            }
        }
        // привязываем обработчик свайпов к recyclerview
        val myHelper = ItemTouchHelper(itemTouchHelperCallback)
        myHelper.attachToRecyclerView(recyclerView)
    }

        @Deprecated("Deprecated in Java")
        override fun onActivityCreated(savedInstanceState: Bundle?) {
            super.onActivityCreated(savedInstanceState)
            viewModel = ViewModelProvider(this).get(PaintListViewModel::class.java)

        }


}
