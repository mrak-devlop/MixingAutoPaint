package ru.kitfactory.mixingautopaint.domain.usecase

import ru.kitfactory.mixingautopaint.domain.models.FieldForCheck

class ChekFieldsUseCase(private val field: FieldForCheck, private val errors:List<String>) {
    fun execute(): Boolean{
        if (field.inTitleInput.length() == 0){
            field.inTitle.error = errors[0]
            return false
        }else{
            field.inTitle.error = null
        }
        if (field.inPaintPartInput.length() == 0){
            field.inPaintPart.error = errors[1]
            return false
        }else{
            field.inPaintPart.error = null
        }
        if (field.inHardenerPartInput.length() == 0){
            field.inHardenerPart.error = errors[1]
            return false
        }else{
            field.inHardenerPart.error = null
        }
        if (field.inDiluentPartInput.length() == 0){
            field.inDiluentPart.error = errors[1]
            return false
        }else{
            field.inDiluentPart.error = null
        }
        if (field.inMassPaintInput.length() == 0){
            field.inMassPaint.error = errors[1]
            return false
        }else{
            field.inMassPaint.error = null
        }

        return true
    }
}