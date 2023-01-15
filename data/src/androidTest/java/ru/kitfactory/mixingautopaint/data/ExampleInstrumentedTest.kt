package ru.kitfactory.data

import androidx.room.Room
import androidx.test.InstrumentationRegistry.getContext
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.junit.After

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import ru.kitfactory.mixingautopaint.data.db.DbDao
import ru.kitfactory.mixingautopaint.data.db.LocalDatabase
import ru.kitfactory.mixingautopaint.data.db.PaintDbModel
import java.security.AccessController.getContext

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    @OptIn(DelicateCoroutinesApi::class)
    @Test
    fun testAddAndReadPaint() {

    }

    @Before
    fun createDb() {

    }
    @After
    fun closeDb() {

    }
}