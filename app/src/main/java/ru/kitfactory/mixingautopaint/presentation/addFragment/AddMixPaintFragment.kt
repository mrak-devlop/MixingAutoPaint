package ru.kitfactory.mixingautopaint.presentation.addFragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import ru.kitfactory.mixingautopaint.R
import ru.kitfactory.mixingautopaint.data.storage.db.Paint
import ru.kitfactory.mixingautopaint.domain.models.FieldForCheck
import ru.kitfactory.mixingautopaint.domain.usecase.CalcMixUseCase
import ru.kitfactory.mixingautopaint.domain.usecase.ChekFieldsUseCase

class AddMixPaintFragment : Fragment() {


    private lateinit var saveButton: Button
    private lateinit var inTitle: TextInputLayout
    private lateinit var inTitleInput: TextInputEditText
    private lateinit var inPaintPart: TextInputLayout
    private lateinit var inPaintPartInput: TextInputEditText
    private lateinit var inHardenerPart: TextInputLayout
    private lateinit var inHardenerPartInput: TextInputEditText
    private lateinit var inDiluentPart: TextInputLayout
    private lateinit var inDiluentPartInput: TextInputEditText
    private lateinit var inMassPaint: TextInputLayout
    private lateinit var inMassPaintInput: TextInputEditText
    private val viewModel: AddMixPaintViewModel by lazy {
        ViewModelProvider(this)[AddMixPaintViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.add_mix_paint_fragment, container, false)
        inTitle = view.findViewById(R.id.inTitle) as TextInputLayout
        inTitleInput = view.findViewById(R.id.inTitleInput) as TextInputEditText
        inPaintPart = view.findViewById(R.id.inPaintPart) as TextInputLayout
        inPaintPartInput = view.findViewById(R.id.inPaintPartInput) as TextInputEditText
        inHardenerPart = view.findViewById(R.id.inHardenerPart) as TextInputLayout
        inHardenerPartInput = view.findViewById(R.id.inHardenerPartInput) as TextInputEditText
        inDiluentPart = view.findViewById(R.id.inDiluentPart) as TextInputLayout
        inDiluentPartInput = view.findViewById(R.id.inDiluentPartInput) as TextInputEditText
        inMassPaint = view.findViewById(R.id.inMassPaint) as TextInputLayout
        inMassPaintInput = view.findViewById(R.id.inMassPaintInput) as TextInputEditText
        saveButton = view.findViewById(R.id.saveEditButton) as Button
        val fieldsIn = FieldForCheck(
            inTitle, inTitleInput, inPaintPart, inPaintPartInput,
            inHardenerPart, inHardenerPartInput, inDiluentPart, inDiluentPartInput,
            inMassPaint, inMassPaintInput
        )
        val errorMsg: List<String> = listOf(
            getString(R.string.title_error_msg),
            getString(R.string.error_msg)
        )

        saveButton.setOnClickListener {
            // получаем данные из фрагмента
            val title = inTitleInput.text.toString()
            val paintPart = inPaintPartInput.text.toString()
            val hardenerPart = inHardenerPartInput.text.toString()
            val diluentPart = inDiluentPartInput.text.toString()
            val massPaint = inMassPaintInput.text.toString()
            /* валидация ввода */
            val chekFieldsUseCase = ChekFieldsUseCase(fieldsIn, errorMsg)
            if (chekFieldsUseCase.execute()) {
                val forMix = ru.kitfactory.mixingautopaint.domain.models.PaintForMix(
                    title,
                    paintPart,
                    hardenerPart,
                    diluentPart,
                    massPaint
                )
                /* сохраняем в данные */
                insertDataToDatabase(forMix)
            }

        }
        return view
    }

    private fun insertDataToDatabase(forMix: ru.kitfactory.mixingautopaint.domain.models.PaintForMix) {

        // расчитываем краску
        val mixPaint = CalcMixUseCase(forMix).execute()
        val paint = Paint(
            0,
            forMix.title,
            forMix.paintPart.toFloat(),
            forMix.hardenerPart.toFloat(),
            forMix.diluentPart.toFloat(),
            forMix.massPaint.toInt(),
            mixPaint.massPaint,
            mixPaint.massHardener,
            mixPaint.paintPlusHardener,
            mixPaint.massDiluent
        )
        // записываем данныев бд
        viewModel.addPaint(paint)
        findNavController().navigate(R.id.action_addMixPaintFragment_to_paintListFragment)
    }


}