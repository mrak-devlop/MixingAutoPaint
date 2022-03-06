package ru.kitfactory.mixingautopaint.presentation

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.kitfactory.mixingautopaint.R

class AddMixPaintFragment : Fragment() {

    companion object {
        fun newInstance() = AddMixPaintFragment()
    }

    private lateinit var viewModel: AddMixPaintViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.add_mix_paint_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(AddMixPaintViewModel::class.java)
        // TODO: Use the ViewModel
    }

}