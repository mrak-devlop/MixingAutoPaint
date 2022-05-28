package ru.kitfactory.mixingautopaint.presentation.listFragment

import android.annotation.SuppressLint
import android.graphics.*
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
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
    private lateinit var trashBinIcon: Drawable
    private val viewModel: PaintListViewModel by lazy {
        ViewModelProvider(this)[PaintListViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_paint_list, container, false)
        addButton = view.findViewById(R.id.addMixButton) as FloatingActionButton
        recyclerView = view.findViewById(R.id.paint_mix_recycler_view) as RecyclerView
        @SuppressLint("UseCompatLoadingForDrawables")
        trashBinIcon = activity?.resources?.getDrawable(
            drawable.ic_baseline_delete_forever, null
        ) as Drawable
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
            getString(R.string.gram_in_list),
            getString(R.string.parts_in_list), getString(R.string.mix1_list),
            getString(R.string.mix2_list), getString(R.string.mix3_list),
            getString(R.string.mix4_list), getString(R.string.mix5_list)
        )
        // заполняем recyclerview
        loadListPaint(textData)

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
                builder.setPositiveButton(getText(R.string.confirm_delete)) { _, _ ->
                    deletePaint(textData, viewHolder)
                }
                // если не подтверждено ничего не делаем
                builder.setNegativeButton(getText(R.string.cancel_delete)) { _, _ ->
                    loadListPaint(textData)
                }
                // устанавливаем заголовок и текст диалога
                builder.setTitle(getText(R.string.title_delete))
                builder.setMessage(getText(R.string.msg_delete))
                // показать диалог
                builder.create().show()
            }

            override fun onChildDraw(
                c: Canvas, recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                dX: Float, dY: Float, actionState: Int,
                isCurrentlyActive: Boolean
            ) {
                val itemView = viewHolder.itemView
                val itemHeight = itemView.bottom - itemView.top
                val intrinsicWidth = trashBinIcon.intrinsicWidth
                val intrinsicHeight = trashBinIcon.intrinsicHeight
                val trashBinIconTop = itemView.top + (itemHeight - intrinsicHeight) / 2
                val trashBinIconMargin = (itemHeight - intrinsicHeight) / 2
                val trashBinIconLeft = itemView.left + trashBinIconMargin - intrinsicWidth
                val trashBinIconRight = itemView.left + trashBinIconMargin
                val trashBinIconBottom = trashBinIconTop + intrinsicHeight
                trashBinIcon.bounds = Rect(
                    trashBinIconLeft, trashBinIconTop,
                    trashBinIconRight, trashBinIconBottom
                )
                trashBinIcon.draw(c)
                super.onChildDraw(
                    c, recyclerView, viewHolder,
                    dX, dY, actionState, isCurrentlyActive
                )
            }

            override fun getSwipeThreshold(viewHolder: RecyclerView.ViewHolder): Float {
                return 0.7f
            }


        }
        // привязываем обработчик свайпов к recyclerview
        val myHelper = ItemTouchHelper(itemTouchHelperCallback)
        myHelper.attachToRecyclerView(recyclerView)

    }

    fun loadListPaint(textData: PrintResText) {
        val adapter = PaintListAdapter(textData)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        viewModel.readAllData.observe(viewLifecycleOwner, { paint ->
            adapter.setData(paint)
        })
    }

    fun deletePaint(textData: PrintResText, viewHolder: RecyclerView.ViewHolder) {
        val adapter = PaintListAdapter(textData)
        viewModel.removePaint(viewHolder.adapterPosition)
        adapter.notifyItemRemoved(viewHolder.adapterPosition)
    }


}
