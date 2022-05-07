package ru.kitfactory.mixingautopaint.presentation.addFragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.android.material.textfield.TextInputEditText
import ru.kitfactory.mixingautopaint.R
import ru.kitfactory.mixingautopaint.data.storage.db.Paint
import ru.kitfactory.mixingautopaint.domain.usecase.CalcMixUseCase
import ru.kitfactory.mixingautopaint.domain.usecase.InputCheckUseCase
import ru.kitfactory.mixingautopaint.presentation.model.PaintForMix

class AddMixPaintFragment : Fragment() {

    private lateinit var viewModel: AddMixPaintViewModel
    private lateinit var saveButton: Button
    private lateinit var inTitle: TextInputEditText
    private lateinit var inPaintPart: TextInputEditText
    private lateinit var inHardenerPart: TextInputEditText
    private lateinit var inDiluentPart: TextInputEditText
    private lateinit var inMassPaint: TextInputEditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.add_mix_paint_fragment, container, false)
        inTitle = view.findViewById(R.id.inTitleInput) as TextInputEditText
        inPaintPart = view.findViewById(R.id.inPaintPartInput) as TextInputEditText
        inHardenerPart = view.findViewById(R.id.inHardenerPartInput) as TextInputEditText
        inDiluentPart = view.findViewById(R.id.inDiluentPartInput) as TextInputEditText
        inMassPaint = view.findViewById(R.id.inMassPaintInput) as TextInputEditText
        saveButton = view.findViewById(R.id.saveEditButton) as Button

        saveButton.setOnClickListener {
            // получаем данные из фрагмента
            val title = inTitle.text.toString()
            val paintPart = inPaintPart.text.toString()
            val hardenerPart = inHardenerPart.text.toString()
            val diluentPart = inDiluentPart.text.toString()
            val massPaint = inMassPaint.text.toString()
            val forMix = PaintForMix(title, paintPart, hardenerPart, diluentPart, massPaint)

            //  сохраняем в данные
            insertDataToDatabase(forMix)


        }
        return view
    }

    private fun insertDataToDatabase(forMix: PaintForMix) {
        if (InputCheckUseCase(forMix).execute()) {
            // расчитываем краску
            val mixPaint = CalcMixUseCase(forMix).execute()
            val paint = Paint(
                0,
                forMix.title,
                forMix.paintPart.toInt(),
                forMix.hardenerPart.toInt(),
                forMix.diluentPart.toInt(),
                forMix.massPaint.toInt(),
                mixPaint.massPaint,
                mixPaint.massHardener,
                mixPaint.paintPlusHardener,
                mixPaint.massDiluent
            )
            viewModel.addPaint(paint)
            findNavController().navigate(R.id.action_addMixPaintFragment_to_paintListFragment)
        } else {
            Toast.makeText(requireContext(), R.string.error_msg,
                Toast.LENGTH_LONG).show()
        }

    }


    @Deprecated("Deprecated in Java")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(AddMixPaintViewModel::class.java)
    }


}