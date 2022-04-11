package ru.kitfactory.mixingautopaint.presentation.detailFragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.fragment.navArgs
import ru.kitfactory.mixingautopaint.R

const val SPACE = " "
const val COLON = ":"

class DetailMixPaintFragment : Fragment() {
    private val args by navArgs<DetailMixPaintFragmentArgs>()
    private lateinit var viewModel: DetailMixPaintViewModel
    private lateinit var titleText: TextView
    private lateinit var partsText: TextView
    private lateinit var mixText: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.detail_mix_paint_fragment, container, false)
        titleText = view.findViewById(R.id.detail_title_text)
        partsText = view.findViewById(R.id.detail_parts_text)
        mixText = view.findViewById(R.id.detail_mix_text)

        val titleMix = args.currentPaint.titleMix
        val paintMass = args.currentPaint.paintMass.toString()
        val gramString = getString(R.string.gram_in_list)
        val printTitle = (titleMix + SPACE + paintMass + SPACE + gramString)
        titleText.text = printTitle

        val partsTitle = getString(R.string.parts_in_list)
        val partPaint = args.currentPaint.partPaint.toString()
        val partHardener = args.currentPaint.partHardener.toString()
        val partDiluent = args.currentPaint.partDiluent.toString()
        val printParts = (partsTitle + SPACE + partPaint +
                COLON + partHardener + COLON + partDiluent)
        partsText.text = printParts

        val text1Mix = getString(R.string.mix1_list)
        val text2Mix = getString(R.string.mix2_list)
        val text3Mix = getString(R.string.mix3_list)
        val text4Mix = getString(R.string.mix4_list)
        val text5Mix = getString(R.string.mix5_list)
        val paintMassForMix = args.currentPaint.paintMassForMix.toString()
        val massHardenerForMix = args.currentPaint.massHardenerForMix.toString()
        val paintPlusHardener = args.currentPaint.paintPlusHardener.toString()
        val massDiluentForMix = args.currentPaint.massDiluentForMix.toString()
        val printMix = (text1Mix + paintMassForMix + text2Mix +
                massHardenerForMix + text3Mix + paintPlusHardener +
                text4Mix + massDiluentForMix + text5Mix)
        mixText.text = printMix
        
        return view
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this)[DetailMixPaintViewModel::class.java]
    }

}