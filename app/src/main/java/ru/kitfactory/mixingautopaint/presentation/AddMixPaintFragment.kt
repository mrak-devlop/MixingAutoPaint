package ru.kitfactory.mixingautopaint.presentation

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
import ru.kitfactory.mixingautopaint.domain.usecase.InputChekUseCase

class AddMixPaintFragment : Fragment() {



    companion object {
        fun newInstance() = AddMixPaintFragment()
    }

    private lateinit var viewModel: AddMixPaintViewModel

    private lateinit var saveButton: Button
    private lateinit var inTitle: TextInputEditText
    private lateinit var inPaintPart: TextInputEditText
    private lateinit var inHardenerPart: TextInputEditText
    private lateinit var inDiluentPart: TextInputEditText
    private lateinit var inMassPaint: TextInputEditText
    private lateinit var paint: Paint

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.add_mix_paint_fragment, container, false)
        inTitle=view.findViewById(R.id.inTitleInput) as TextInputEditText
        inPaintPart=view.findViewById(R.id.inPaintPartInput) as TextInputEditText
        inHardenerPart=view.findViewById(R.id.inHardenerPartInput) as TextInputEditText
        inDiluentPart=view.findViewById(R.id.inDiluentPartInput) as TextInputEditText
        inMassPaint=view.findViewById(R.id.inMassPaintInput) as TextInputEditText
        saveButton=view.findViewById(R.id.saveButton) as Button

        saveButton.setOnClickListener {
            // получаем данные из фрагмента
            val title = inTitle.text.toString()
            val paintPart = inPaintPart.text.toString().toInt()
            val hardenerPart =  inHardenerPart.text.toString().toInt()
            val diluentPart = inDiluentPart.text.toString().toInt()
            val massPaint = inMassPaint.text.toString().toInt()

            //  сохраняем в данные
            insertDataToDatabase(title, paintPart, hardenerPart, diluentPart, massPaint)


        }
        return view
    }

    private fun insertDataToDatabase(title: String,
                                     paintPart: Int,
                                     hardenerPart: Int,
                                     diluentPart: Int,
                                     massPaint: Int ) {
        if (InputChekUseCase(title, paintPart, hardenerPart,diluentPart, massPaint).execute()){
            // расчитываем краску
            val mixPaint = CalcMixUseCase(paintPart, hardenerPart, diluentPart, massPaint).execute()
            val paint: Paint = Paint(0,
                title,
                paintPart,
                hardenerPart,
                diluentPart,
                massPaint, mixPaint.massPaint,
                mixPaint.massHardener,
                mixPaint.paintPlusHardener,
                mixPaint.massDiluent)
            viewModel.addPaint(paint)
            findNavController().navigate(R.id.action_addMixPaintFragment_to_paintListFragment)
        }
        else {
            Toast.makeText(requireContext(), R.string.error_msg, Toast.LENGTH_LONG).show()
        }

    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(AddMixPaintViewModel::class.java)
    }


}