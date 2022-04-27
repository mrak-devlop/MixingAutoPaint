package ru.kitfactory.mixingautopaint.presentation.detailFragment

interface Callbacks {
    fun onShowNotification(printTitle: String, printMix: String)

    fun onDeleteNotification()
}