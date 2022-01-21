package com.rino.counters

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.rino.counters.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), MainView {

    private lateinit var binding: ActivityMainBinding
    private val presenter = MainPresenterImpl(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCounter1.setOnClickListener { presenter.onCounter1Clicked() }
        binding.btnCounter2.setOnClickListener { presenter.onCounter2Clicked() }
        binding.btnCounter3.setOnClickListener { presenter.onCounter3Clicked() }
    }

    //Подсказка к ПЗ: поделить на 3 отдельные функции и избавиться от index
    override fun setButtonText(index: Int, text: String) {
        when (index) {
            0 -> binding.btnCounter1.text = text
            1 -> binding.btnCounter2.text = text
            2 -> binding.btnCounter3.text = text
        }
    }
}