package ru.kitfactory.mixingautopaint.presentation.editFragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import ru.kitfactory.domain.models.PaintDomainModel
import ru.kitfactory.mixingautopaint.App
import ru.kitfactory.mixingautopaint.R
import ru.kitfactory.mixingautopaint.databinding.EditFragmentBinding
import ru.kitfactory.mixingautopaint.di.factory.ViewModelFactory
import ru.kitfactory.mixingautopaint.domain.models.FieldForCheck
import ru.kitfactory.mixingautopaint.domain.usecase.CalcMixUseCase
import ru.kitfactory.mixingautopaint.domain.usecase.CheckFieldsUseCase
import ru.kitfactory.mixingautopaint.viewmodel.EditViewModel
import javax.inject.Inject

class EditFragment : Fragment() {
    private val argsForEdit by navArgs<EditFragmentArgs>()
    private var _binding: EditFragmentBinding? = null
    private val binding get() = _binding!!
    @Inject
    lateinit var vmFactory: ViewModelFactory
    private val viewModel: EditViewModel by lazy {
        ViewModelProvider(this, vmFactory)[EditViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = EditFragmentBinding.inflate(inflater, container, false)
        val view = binding.root
        val editTitleInput = binding.editTitleInput
        editTitleInput.setText(argsForEdit.currentPaintForEdit.titleMix)
        val editPaintPartInput = binding.editPaintPartInput
        editPaintPartInput.setText(argsForEdit.currentPaintForEdit.partPaint.toString())
        val editHardenerPartInput = binding.editHardenerPartInput
        editHardenerPartInput.setText(argsForEdit.currentPaintForEdit.partHardener.toString())
        val editDiluentPartInput = binding.editDiluentPartInput
        editDiluentPartInput.setText(argsForEdit.currentPaintForEdit.partDiluent.toString())
        val editMassPaintInput = binding.editMassPaintInput
        editMassPaintInput.setText(argsForEdit.currentPaintForEdit.paintMass.toString())
        val fieldsIn = FieldForCheck(binding.editTitle, editTitleInput, binding.editPaintPart, editPaintPartInput,
        binding.editHardenerPart, editHardenerPartInput, binding.editDiluentPart, editDiluentPartInput,
            binding.editMassPaint, editMassPaintInput)
        val errorMsg: List<String> = listOf(getString(R.string.title_error_msg),
            getString(R.string.error_msg))

        binding.saveEditButton.setOnClickListener {
            val title = editTitleInput.text.toString()
            val paintPart = editPaintPartInput.text.toString()
            val hardenerPart = editHardenerPartInput.text.toString()
            val diluentPart = editDiluentPartInput.text.toString()
            val massPaint = editMassPaintInput.text.toString()
            val checkFieldsUseCase = CheckFieldsUseCase(fieldsIn, errorMsg)
            if (checkFieldsUseCase.execute()) {
                val forMix = ru.kitfactory.mixingautopaint.domain.models.PaintForMix(
                    title,
                    paintPart,
                    hardenerPart,
                    diluentPart,
                    massPaint
                )
                updateDataToDatabase(argsForEdit.currentPaintForEdit.id, forMix)
            }
        }
        return view
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectDagger()
    }

    private fun injectDagger() {
        App.instance.appComponent.inject(this)
    }

    private fun updateDataToDatabase(
        id: Int,
        forMix: ru.kitfactory.mixingautopaint.domain.models.PaintForMix
    ) {
        //пересчидываем краску
            val mixPaint = CalcMixUseCase(forMix).execute()
            val paint = PaintDomainModel(
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}