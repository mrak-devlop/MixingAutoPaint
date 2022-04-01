package ru.kitfactory.mixingautopaint.presentation.listFragment



import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.kitfactory.mixingautopaint.R
import ru.kitfactory.mixingautopaint.data.storage.db.Paint
import ru.kitfactory.mixingautopaint.presentation.model.PrintResText

const val space = " "
const val colon = ":"

class PaintListAdapter(private val textData: PrintResText):RecyclerView.Adapter<PaintListAdapter.ViewHolder>() {
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
        val printTitle = (currentItem.titleMix + space +
                currentItem.paintMass.toString() + space + textData.printGram)
        holder.titleText.text = printTitle

        val partsTitle = textData.partsTitle
        val printParts = (partsTitle + space + currentItem.partPaint.toString() + colon +
                currentItem.partHardener.toString() + colon +
                currentItem.partDiluent.toString())
        holder.partsText.text = printParts

        val text1Mix = textData.text1Mix
        val text2Mix = textData.text2Mix
        val text3Mix = textData.text3Mix
        val text4Mix = textData.text4Mix
        val text5Mix = textData.text5Mix
        val printMix = (text1Mix + currentItem.paintMassForMix.toString() + text2Mix +
                currentItem.massHardenerForMix.toString() + text3Mix +
                currentItem.paintPlusHardener.toString() + text4Mix +
                currentItem.massDiluentForMix + text5Mix)
        holder.mixText.text = printMix

    }

    override fun getItemCount(): Int {
       return paintList.size
    }

    fun setData(paint: List<Paint>) {
        this.paintList = paint
        notifyDataSetChanged()
    }

}