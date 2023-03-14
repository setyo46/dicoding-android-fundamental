package com.setyo.mybackgroundthread

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val startButton = findViewById<Button>(R.id.starButton)
        val statusTextView = findViewById<TextView>(R.id.statusTextView)

        startButton.setOnClickListener {
            lifecycleScope.launch(Dispatchers.Default) {
                // simulate process in background thread
                for (i in 0..10) {
                    delay(500)
                    val percentage = i * 10
                    withContext(Dispatchers.Main) {
                        // update ui in main thread
                        if (percentage == 100) {
                            statusTextView.setText(R.string.task_completed)
                        } else {
                            statusTextView.text = String.format(getString(R.string.compressing), percentage)
                        }
                    }
                }
            }
        }
    }
}