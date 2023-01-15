package ru.kitfactory.mixingautopaint.presentation.addFragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import ru.kitfactory.domain.models.PaintDomainModel
import ru.kitfactory.mixingautopaint.App
import ru.kitfactory.mixingautopaint.R
import ru.kitfactory.mixingautopaint.databinding.AddMixPaintFragmentBinding
import ru.kitfactory.mixingautopaint.di.factory.ViewModelFactory
import ru.kitfactory.mixingautopaint.domain.models.FieldForCheck
import ru.kitfactory.mixingautopaint.domain.usecase.CalcMixUseCase
import ru.kitfactory.mixingautopaint.domain.usecase.CheckFieldsUseCase
import ru.kitfactory.mixingautopaint.viewmodel.AddMixPaintViewModel
import javax.inject.Inject

class AddMixPaintFragment : Fragment() {
    private var _binding: AddMixPaintFragmentBinding? = null
    private val binding get() = _binding!!
    @Inject
    lateinit var vmFactory: ViewModelFactory
    private val viewModel: AddMixPaintViewModel by lazy {
        ViewModelProvider(this, vmFactory)[AddMixPaintViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = AddMixPaintFragmentBinding.inflate(inflater,container,false)
        val view = binding.root
        val inTitleInput = binding.inTitleInput
        val inPaintPart = binding.inPaintPart
        val inPaintPartInput = binding.inPaintPartInput
        val inHardenerPart = binding.inHardenerPart
        val inHardenerPartInput = binding.inHardenerPartInput
        val inDiluentPart = binding.inDiluentPart
        val inDiluentPartInput = binding.inDiluentPartInput
        val inMassPaint = binding.inMassPaint
        val inMassPaintInput = binding.inMassPaintInput
        val fieldsIn = FieldForCheck(binding.inTitle, inTitleInput, inPaintPart, inPaintPartInput,
            inHardenerPart, inHardenerPartInput, inDiluentPart, inDiluentPartInput,
            inMassPaint, inMassPaintInput)
        val errorMsg: List<String> = listOf(getString(R.string.title_error_msg),
            getString(R.string.error_msg))

        binding.saveEditButton.setOnClickListener {
            // получаем данные из фрагмента
            val title = inTitleInput.text.toString()
            val paintPart = inPaintPartInput.text.toString()
            val hardenerPart = inHardenerPartInput.text.toString()
            val diluentPart = inDiluentPartInput.text.toString()
            val massPaint = inMassPaintInput.text.toString()
            /* валидация ввода */
            val checkFieldsUseCase = CheckFieldsUseCase(fieldsIn, errorMsg)
            if (checkFieldsUseCase.execute()) {
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectDagger()
    }

    private fun injectDagger() {
        App.instance.appComponent.inject(this)
    }

    private fun insertDataToDatabase(forMix: ru.kitfactory.mixingautopaint.domain.models.PaintForMix) {

            // расчитываем краску
            val mixPaint = CalcMixUseCase(forMix).execute()
            val paint = PaintDomainModel(
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
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}