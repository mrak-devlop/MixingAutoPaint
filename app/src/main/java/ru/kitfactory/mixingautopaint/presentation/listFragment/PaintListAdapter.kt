package ru.kitfactory.mixingautopaint.presentation.listFragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.kitfactory.mixingautopaint.R
import ru.kitfactory.mixingautopaint.data.storage.db.Paint

class PaintListAdapter():RecyclerView.Adapter<PaintListAdapter.ViewHolder>() {
    private var paintList = emptyList<Paint>()
    class ViewHolder(view:View): RecyclerView.ViewHolder(view){
        val titleText: TextView
        val partsText: TextView
        val mixText: TextView
        init {
            titleText = view.findViewById(R.id.title_text)
            partsText = view.findViewById(R.id.parts_text)
            mixText = view.findViewById(R.id.mix_text)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_fragment_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = paintList[position]
        holder.titleText.text = (currentItem.titleMix + " " + currentItem.paintMass.toString())
        holder.partsText.text = (currentItem.partPaint.toString() + ":" +
                currentItem.partHardener.toString() + ":" +
                currentItem.partDiluent.toString())
        holder.mixText.text = (currentItem.paintMassForMix.toString() + " add " +
                currentItem.massHardenerForMix.toString() + " = " +
                currentItem.paintPlusHardener.toString() + " add " +
                currentItem.massDiluentForMix)

    }

    override fun getItemCount(): Int {
       return paintList.size
    }

    fun setData(paint: List<Paint>) {
        this.paintList = paint
        notifyDataSetChanged()
    }
}