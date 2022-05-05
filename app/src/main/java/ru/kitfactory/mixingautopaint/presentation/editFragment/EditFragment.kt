package ru.kitfactory.mixingautopaint.presentation.editFragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.textfield.TextInputEditText
import ru.kitfactory.mixingautopaint.R
import ru.kitfactory.mixingautopaint.data.storage.db.Paint
import ru.kitfactory.mixingautopaint.domain.usecase.CalcMixUseCase
import ru.kitfactory.mixingautopaint.domain.usecase.InputCheckUseCase

class EditFragment : Fragment() {
    private val argsForEdit by navArgs<EditFragmentArgs>()
    private lateinit var viewModel: EditViewModel
    private lateinit var saveEditButton: Button
    private lateinit var editTitle: TextInputEditText
    private lateinit var editPaintPart: TextInputEditText
    private lateinit var editHardenerPart: TextInputEditText
    private lateinit var editDiluentPart: TextInputEditText
    private lateinit var editMassPaint: TextInputEditText


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.edit_fragment, container, false)
        saveEditButton = view.findViewById(R.id.saveEditButton)
        editTitle = view.findViewById(R.id.editTitleInput)
        editPaintPart = view.findViewById(R.id.editPaintPartInput)
        editHardenerPart = view.findViewById(R.id.editHardenerPartInput)
        editDiluentPart = view.findViewById(R.id.editDiluentPartInput)
        editMassPaint = view.findViewById(R.id.editMassPaintInput)

        editTitle.setText(argsForEdit.currentPaintForEdit.titleMix)
        editPaintPart.setText(argsForEdit.currentPaintForEdit.partPaint.toString())
        editHardenerPart.setText(argsForEdit.currentPaintForEdit.partHardener.toString())
        editDiluentPart.setText(argsForEdit.currentPaintForEdit.partDiluent.toString())
        editMassPaint.setText(argsForEdit.currentPaintForEdit.paintMass.toString())

        saveEditButton.setOnClickListener {
            val title = editTitle.text.toString()
            val paintPart = editPaintPart.text.toString().toInt()
            val hardenerPart = editHardenerPart.text.toString().toInt()
            val diluentPart = editDiluentPart.text.toString().toInt()
            val massPaint = editMassPaint.text.toString().toInt()
            updateDataToDatabase(
                argsForEdit.currentPaintForEdit.id,
                title,
                paintPart,
                hardenerPart,
                diluentPart,
                massPaint
            )
        }
        return view
    }

    private fun updateDataToDatabase(
        id: Int,
        title: String,
        paintPart: Int,
        hardenerPart: Int,
        diluentPart: Int,
        massPaint: Int
    ) {
        if (InputCheckUseCase(title, paintPart, hardenerPart, diluentPart, massPaint).execute()) {
            // расчитываем краску
            val mixPaint = CalcMixUseCase(paintPart, hardenerPart, diluentPart, massPaint).execute()
            val paint = Paint(
                id,
                title,
                paintPart,
                hardenerPart,
                diluentPart,
                massPaint,
                mixPaint.massPaint,
                mixPaint.massHardener,
                mixPaint.paintPlusHardener,
                mixPaint.massDiluent
            )
            viewModel.updatePaint(paint)
            findNavController().navigate(R.id.action_editFragment_to_paintListFragment)
        } else {
            Toast.makeText(requireContext(), R.string.error_msg, Toast.LENGTH_LONG).show()
        }

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(EditViewModel::class.java)
        // TODO: Use the ViewModel
    }

}