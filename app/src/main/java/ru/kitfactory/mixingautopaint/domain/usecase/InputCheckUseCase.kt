package ru.kitfactory.mixingautopaint.domain.usecase

class InputCheckUseCase(
    private val title: String,
    private val paintPart: Int,
    private val hardenerPart: Int,
    private val diluentPart: Int,
    private val massPaint: Int
) {
    private val none = 0

    fun execute(): Boolean {
        return !(title.length == none &&
                paintPart.toString().length == none &&
                hardenerPart.toString().length == none &&
                diluentPart.toString().length == none &&
                massPaint.toString().length == none)
    }
}