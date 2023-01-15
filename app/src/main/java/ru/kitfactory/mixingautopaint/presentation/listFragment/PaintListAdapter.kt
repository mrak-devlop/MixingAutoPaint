package ru.kitfactory.mixingautopaint.presentation.listFragment


import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import ru.kitfactory.domain.models.PaintDomainModel
import ru.kitfactory.mixingautopaint.databinding.ItemFragmentListBinding
import ru.kitfactory.mixingautopaint.models.PaintAppModel
import ru.kitfactory.mixingautopaint.presentation.model.PrintResText


class PaintListAdapter(private val textData: PrintResText) :
    RecyclerView.Adapter<PaintListAdapter.ViewHolder>() {
    companion object {
        const val SPACE = " "
        const val COLON = ":"
    }
    private var paintList = emptyList<PaintAppModel>()

    class ViewHolder(val binding: ItemFragmentListBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemFragmentListBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = paintList[position]
        val titleMix = currentItem.titleMix
        val paintMass = currentItem.paintMass.toString()
        val printGram = textData.printGram
        val printTitle = (titleMix + SPACE + paintMass + SPACE + printGram)
        holder.binding.titleText.text = printTitle

        val partsTitle = textData.partsTitle
        val partPaint = currentItem.partPaint.toString()
        val partHardener = currentItem.partHardener.toString()
        val partDiluent = currentItem.partDiluent.toString()
        val printParts = (partsTitle + SPACE + partPaint +
                COLON + partHardener + COLON + partDiluent)
        holder.binding.partsText.text = printParts

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
        holder.binding.mixText.text = printMix

        holder.binding.rowLayout.setOnClickListener {
            val directions = PaintListFragmentDirections
                .actionPaintListFragmentToDetailMixPaintFragment(currentItem)
            holder.itemView.findNavController().navigate(directions)
        }

    }

    override fun getItemCount(): Int {
        return paintList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(paint: List<PaintAppModel>) {
        this.paintList = paint
        notifyDataSetChanged()
    }

}