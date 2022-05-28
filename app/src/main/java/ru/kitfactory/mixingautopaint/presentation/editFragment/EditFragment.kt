package ru.kitfactory.mixingautopaint.presentation.editFragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import ru.kitfactory.mixingautopaint.R
import ru.kitfactory.mixingautopaint.data.storage.db.Paint
import ru.kitfactory.mixingautopaint.domain.models.FieldForCheck
import ru.kitfactory.mixingautopaint.domain.usecase.CalcMixUseCase
import ru.kitfactory.mixingautopaint.domain.usecase.ChekFieldsUseCase
import ru.kitfactory.mixingautopaint.presentation.model.PaintForMix

class EditFragment : Fragment() {
    private val argsForEdit by navArgs<EditFragmentArgs>()
    private lateinit var saveEditButton: Button
    private lateinit var editTitle: TextInputLayout
    private lateinit var editTitleInput: TextInputEditText
    private lateinit var editPaintPart: TextInputLayout
    private lateinit var editPaintPartInput: TextInputEditText
    private lateinit var editHardenerPart: TextInputLayout
    private lateinit var editHardenerPartInput: TextInputEditText
    private lateinit var editDiluentPart: TextInputLayout
    private lateinit var editDiluentPartInput: TextInputEditText
    private lateinit var editMassPaint: TextInputLayout
    private lateinit var editMassPaintInput: TextInputEditText
    private val viewModel: EditViewModel by lazy {
        ViewModelProvider(this)[EditViewModel::class.java]
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.edit_fragment, container, false)
        saveEditButton = view.findViewById(R.id.saveEditButton)
        editTitle = view.findViewById(R.id.editTitle)
        editTitleInput = view.findViewById(R.id.editTitleInput)
        editPaintPart = view.findViewById(R.id.editPaintPart)
        editPaintPartInput = view.findViewById(R.id.editPaintPartInput)
        editHardenerPart = view.findViewById(R.id.editHardenerPart)
        editHardenerPartInput = view.findViewById(R.id.editHardenerPartInput)
        editDiluentPart = view.findViewById(R.id.editDiluentPart)
        editDiluentPartInput = view.findViewById(R.id.editDiluentPartInput)
        editMassPaint = view.findViewById(R.id.editMassPaint)
        editMassPaintInput = view.findViewById(R.id.editMassPaintInput)

        editTitleInput.setText(argsForEdit.currentPaintForEdit.titleMix)
        editPaintPartInput.setText(argsForEdit.currentPaintForEdit.partPaint.toString())
        editHardenerPartInput.setText(argsForEdit.currentPaintForEdit.partHardener.toString())
        editDiluentPartInput.setText(argsForEdit.currentPaintForEdit.partDiluent.toString())
        editMassPaintInput.setText(argsForEdit.currentPaintForEdit.paintMass.toString())
        val fieldsIn = FieldForCheck(
            editTitle, editTitleInput, editPaintPart, editPaintPartInput,
            editHardenerPart, editHardenerPartInput, editDiluentPart, editDiluentPartInput,
            editMassPaint, editMassPaintInput
        )
        val errorMsg: List<String> = listOf(
            getString(R.string.title_error_msg),
            getString(R.string.error_msg)
        )

        saveEditButton.setOnClickListener {
            val title = editTitleInput.text.toString()
            val paintPart = editPaintPartInput.text.toString()
            val hardenerPart = editHardenerPartInput.text.toString()
            val diluentPart = editDiluentPartInput.text.toString()
            val massPaint = editMassPaintInput.text.toString()
            if (ChekFieldsUseCase(fieldsIn, errorMsg).execute()) {
                val forMix = PaintForMix(title, paintPart, hardenerPart, diluentPart, massPaint)
                updateDataToDatabase(argsForEdit.currentPaintForEdit.id, forMix)
            }
        }
        return view
    }

    private fun updateDataToDatabase(
        id: Int,
        forMix: PaintForMix
    ) {
        //пересчидываем краску
        val mixPaint = CalcMixUseCase(forMix).execute()
        val paint = Paint(
            id,
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
        // обновляем запись в бд
        viewModel.updatePaint(paint)
        findNavController().navigate(R.id.action_editFragment_to_paintListFragment)
    }

}