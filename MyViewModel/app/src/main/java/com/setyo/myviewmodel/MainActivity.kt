package com.setyo.myviewmodel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import com.setyo.myviewmodel.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var activityMainBinding: ActivityMainBinding
//    private lateinit var viewModel: MainViewModel
    private val viewModel: MainViewModel by viewModels() // with delegation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

//        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        displayResult()

        activityMainBinding.calculateButton.setOnClickListener {

            val width = activityMainBinding.widthEditText.text.toString()
            val height = activityMainBinding.heightEditText.text.toString()
            val length = activityMainBinding.lengthEditText.text.toString()

            when {
                width.isEmpty() -> {
                    activityMainBinding.widthEditText.error = "Masih kosong"
                }
                height.isEmpty() -> {
                    activityMainBinding.heightEditText.error = "Masih Kosong"
                }
                length.isEmpty() -> {
                    activityMainBinding.lengthEditText.error = "Masih Kosong"
                }
                else -> {
                    viewModel.calculate(width,height,length)
                    displayResult()
                }
            }
        }
    }

    private fun displayResult() {
        activityMainBinding.resultTextView.text = viewModel.result.toString()
    }
}