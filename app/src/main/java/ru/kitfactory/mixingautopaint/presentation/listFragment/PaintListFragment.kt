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
import androidx.lifecycle.coroutineScope
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.kitfactory.mixingautopaint.App
import ru.kitfactory.mixingautopaint.R
import ru.kitfactory.mixingautopaint.R.drawable
import ru.kitfactory.mixingautopaint.databinding.FragmentPaintListBinding
import ru.kitfactory.mixingautopaint.di.factory.ViewModelFactory
import ru.kitfactory.mixingautopaint.presentation.model.PrintResText
import ru.kitfactory.mixingautopaint.viewmodel.PaintListViewModel
import javax.inject.Inject


class PaintListFragment : Fragment() {
    companion object {
        private val THRESHOLD = 0.7f
    }
    private var _binding: FragmentPaintListBinding? = null
    private val binding get() = _binding!!
    @Inject
    lateinit var vmFactory: ViewModelFactory
    private val viewModel: PaintListViewModel by lazy {
        ViewModelProvider(this, vmFactory)[PaintListViewModel::class.java]
    }
    private lateinit var trashBinIcon: Drawable

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPaintListBinding.inflate(inflater, container, false)
        val view = binding.root
        @SuppressLint("UseCompatLoadingForDrawables")
        trashBinIcon = activity?.resources?.getDrawable(
            drawable.ic_baseline_delete_forever, null
        ) as Drawable
        return view
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectDagger()
    }

    private fun injectDagger() {
        App.instance.appComponent.inject(this)
    }

    override fun onStart() {
        super.onStart()
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
                return THRESHOLD
            }


        }
        // привязываем обработчик свайпов к recyclerview
        val myHelper = ItemTouchHelper(itemTouchHelperCallback)
        myHelper.attachToRecyclerView(binding.paintMixRecyclerView)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.addMixButton.setOnClickListener{
           viewModel.onFabButtonClick(view)
        }
    }

    fun loadListPaint(textData: PrintResText) {
        val adapter = PaintListAdapter(textData)
        binding.paintMixRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.paintMixRecyclerView.adapter = adapter
        lifecycleScope.launchWhenStarted {
            val data = viewModel.dataState.
            data
        }
        binding.paintMixRecyclerView.adapter = adapter


    }

    fun deletePaint(textData: PrintResText, viewHolder: RecyclerView.ViewHolder) {
        val adapter = PaintListAdapter(textData)
        viewModel.removePaint(viewHolder.adapterPosition)
        adapter.notifyItemRemoved(viewHolder.adapterPosition)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
