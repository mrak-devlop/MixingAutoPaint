package ru.kitfactory.mixingautopaint.domain.usecase

import android.text.TextUtils

class InputChekUseCase(val title: String,
                       val paintPart: Int,
                       val hardenerPart: Int,
                       val diluentPart: Int,
                       val massPaint: Int) {
    fun execute() : Boolean {
        return !(TextUtils.isEmpty(title) &&
                paintPart == null &&
                hardenerPart == null &&
                diluentPart == null &&
                massPaint == null)
    }
}