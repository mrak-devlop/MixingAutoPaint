package ru.kitfactory.mixingautopaint.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.kitfactory.mixingautopaint.R
import ru.kitfactory.mixingautopaint.data.storage.DbSource

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val currentFragment = supportFragmentManager.findFragmentById(R.id.fragment_container_view)
        if (currentFragment == null){
            val fragment = PaintListFragment()
            supportFragmentManager.beginTransaction()
                .add(R.id.fragment_container_view, fragment)
                .commit()
        }

    }
}