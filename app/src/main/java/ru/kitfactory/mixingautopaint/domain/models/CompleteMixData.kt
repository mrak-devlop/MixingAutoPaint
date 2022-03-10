package ru.kitfactory.mixingautopaint.domain.models

data class CompleteMixData(val title : String,
                           val massPaint: Float,
                           val massHardener: Float,
                           val paintPlusHardener : Float,
                           val massDiluent: Float,
                           val massMix: Float)
