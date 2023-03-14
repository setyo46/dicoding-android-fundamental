package com.setyo.mybackgroundthread

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.TextView
import java.util.concurrent.Executors

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val startButton = findViewById<Button>(R.id.starButton)
        val statusTextView = findViewById<TextView>(R.id.statusTextView)

        val executor = Executors.newSingleThreadExecutor()
        val handler = Handler(Looper.getMainLooper())


        startButton.setOnClickListener {
            executor.execute {
                try {
                    // simulate process in background thread
                    for (i in 0..10) {
                        Thread.sleep(500)
                        val percentage = i * 10
                        handler.post {
                            // update ui in main thread
                            if (percentage == 100) {
                                statusTextView.setText(R.string.task_completed)
                            } else {
                                statusTextView.text = String.format(getString(R.string.compressing), percentage)
                            }
                        }
                    }
                }catch (e: InterruptedException) {
                    e.printStackTrace()
                }
            }
        }
    }
}