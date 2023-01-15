package ru.kitfactory.mixingautopaint.presentation.detailFragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import ru.kitfactory.mixingautopaint.R
import ru.kitfactory.mixingautopaint.databinding.DetailMixPaintFragmentBinding

class DetailMixPaintFragment : Fragment() {
    companion object{
        private const val SPACE = " "
        private const val COLON = ":"
    }
    private var _binding: DetailMixPaintFragmentBinding? = null
    private val binding get() = _binding!!

    private val args by navArgs<DetailMixPaintFragmentArgs>()
    private var callbacks: Callbacks? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callbacks = context as Callbacks?
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DetailMixPaintFragmentBinding.inflate(inflater, container, false)
        val view = binding.root
        binding.editButton.setOnClickListener {
            val directions = DetailMixPaintFragmentDirections
                .actionDetailMixPaintFragmentToEditFragment(args.currentPaint)
            findNavController().navigate(directions)
        }

        val titleMix = args.currentPaint.titleMix
        val paintMass = args.currentPaint.paintMass.toString()
        val gramString = getString(R.string.gram_in_list)
        val printTitle = (titleMix + SPACE + paintMass + SPACE + gramString)
        binding.detailTitleText.text = printTitle

        val partsTitle = getString(R.string.parts_in_list)
        val partPaint = args.currentPaint.partPaint.toString()
        val partHardener = args.currentPaint.partHardener.toString()
        val partDiluent = args.currentPaint.partDiluent.toString()
        binding.detailPartsText.text = partsTitle
        val printParts = (partPaint + COLON + partHardener + COLON + partDiluent)
        binding.detailParts.text = printParts

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
        binding.detailMixText.text = printMix
        val checkBoxNotification = binding.showNotification
        checkBoxNotification.setOnClickListener {
            if (checkBoxNotification.isChecked)
                callbacks?.onShowNotification(printTitle, printMix)
            else {
                callbacks?.onDeleteNotification()
            }

        }
        return view
    }

    override fun onDetach() {
        super.onDetach()
        callbacks = null
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
