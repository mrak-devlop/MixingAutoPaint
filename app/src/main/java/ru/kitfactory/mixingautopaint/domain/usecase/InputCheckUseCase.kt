package ru.kitfactory.mixingautopaint.domain.usecase

const val none = ""

class InputCheckUseCase(private val title: String,
                        private val paintPart: Int,
                        private val hardenerPart: Int,
                        private val diluentPart: Int,
                        private val massPaint: Int) {

    fun execute() : Boolean {
        return !(title == none &&
                paintPart.toString() == none &&
                hardenerPart.toString() == none &&
                diluentPart.toString() == none &&
                massPaint.toString() == none )
    }
}