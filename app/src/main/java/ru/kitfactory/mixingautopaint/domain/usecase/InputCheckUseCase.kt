package ru.kitfactory.mixingautopaint.domain.usecase

import android.text.TextUtils

class InputCheckUseCase(private val title: String,
                        private val paintPart: Int,
                        private val hardenerPart: Int,
                        private val diluentPart: Int,
                        private val massPaint: Int) {
    fun execute() : Boolean {
        return !(TextUtils.isEmpty(title) &&
                TextUtils.isEmpty(paintPart.toString()) &&
                TextUtils.isEmpty(hardenerPart.toString()) &&
                TextUtils.isEmpty(diluentPart.toString()) &&
                TextUtils.isEmpty(massPaint.toString()))
    }
}