package ru.kitfactory.mixingautopaint.domain.models

import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout


data class FieldForCheck(
    val inTitle: TextInputLayout,
    val inTitleInput: TextInputEditText,
    val inPaintPart: TextInputLayout,
    val inPaintPartInput: TextInputEditText,
    val inHardenerPart: TextInputLayout,
    val inHardenerPartInput: TextInputEditText,
    val inDiluentPart: TextInputLayout,
    val inDiluentPartInput: TextInputEditText,
    val inMassPaint: TextInputLayout,
    val inMassPaintInput: TextInputEditText
)