package ru.kitfactory.mixingautopaint.presentation.listFragment


import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import ru.kitfactory.mixingautopaint.R
import ru.kitfactory.mixingautopaint.data.storage.db.PaintImpl
import ru.kitfactory.mixingautopaint.presentation.model.PrintResText

const val SPACE = " "
const val COLON = ":"

class PaintListAdapter(private val textData: PrintResText) :
    RecyclerView.Adapter<PaintListAdapter.ViewHolder>() {
    private var paintList = emptyList<PaintImpl>()

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titleText: TextView
        val partsText: TextView
        val mixText: TextView
        val rowLayout: LinearLayout

        init {
            titleText = view.findViewById(R.id.title_text)
            partsText = view.findViewById(R.id.parts_text)
            mixText = view.findViewById(R.id.mix_text)
            rowLayout = view.findViewById(R.id.row_layout)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_fragment_list, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = paintList[position]
        val titleMix = currentItem.titleMix
        val paintMass = currentItem.paintMass.toString()
        val printGram = textData.printGram
        val printTitle = (titleMix + SPACE + paintMass + SPACE + printGram)
        holder.titleText.text = printTitle

        val partsTitle = textData.partsTitle
        val partPaint = currentItem.partPaint.toString()
        val partHardener = currentItem.partHardener.toString()
        val partDiluent = currentItem.partDiluent.toString()
        val printParts = (partsTitle + SPACE + partPaint +
                COLON + partHardener + COLON + partDiluent)
        holder.partsText.text = printParts

        val text1Mix = textData.text1Mix
        val text2Mix = textData.text2Mix
        val text3Mix = textData.text3Mix
        val text4Mix = textData.text4Mix
        val text5Mix = textData.text5Mix
        val paintMassForMix = currentItem.paintMassForMix.toString()
        val massHardenerForMix = currentItem.massHardenerForMix.toString()
        val paintPlusHardener = currentItem.paintPlusHardener.toString()
        val massDiluentForMix = currentItem.massDiluentForMix.toString()
        val printMix = (text1Mix + paintMassForMix + text2Mix +
                massHardenerForMix + text3Mix +
                paintPlusHardener + text4Mix +
                massDiluentForMix + text5Mix)
        holder.mixText.text = printMix

        holder.rowLayout.setOnClickListener {
            val directions = PaintListFragmentDirections
                .actionPaintListFragmentToDetailMixPaintFragment(currentItem)
            holder.itemView.findNavController().navigate(directions)
        }

    }

    override fun getItemCount(): Int {
        return paintList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(paint: List<PaintImpl>) {
        this.paintList = paint
        notifyDataSetChanged()
    }

}