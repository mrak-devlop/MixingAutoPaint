package ru.kitfactory.mixingautopaint.domain.models

data class CompleteMixData(val title : String,
                           val massPaint: Double,
                           val massHardener: Double,
                           val paintPlusHardener : Double,
                           val massDiluent: Double,
                           val massMix: Double)
