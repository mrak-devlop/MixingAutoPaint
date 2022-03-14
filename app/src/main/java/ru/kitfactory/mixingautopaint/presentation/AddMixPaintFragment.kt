package ru.kitfactory.mixingautopaint.presentation

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.google.android.material.textfield.TextInputEditText
import ru.kitfactory.mixingautopaint.R
import ru.kitfactory.mixingautopaint.domain.usecase.CalcMixUseCase

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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.add_mix_paint_fragment, container, false)
        inTitle.findViewById(R.id.inTitleInput) as TextInputEditText
        inPaintPart.findViewById(R.id.inPaintPartInput) as TextInputEditText
        inHardenerPart.findViewById(R.id.inHardenerPartInput) as TextInputEditText
        inDiluentPart.findViewById(R.id.inDiluentPartInput) as TextInputEditText
        inMassPaint.findViewById(R.id.inMassPaintInput) as TextInputEditText


        saveButton.findViewById(R.id.saveButton) as Button
        saveButton.setOnClickListener {

        }
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(AddMixPaintViewModel::class.java)
        // TODO: Use the ViewModel
    }

}