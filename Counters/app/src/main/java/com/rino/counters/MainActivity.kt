package com.rino.counters

import android.os.Bundle
import com.rino.counters.databinding.ActivityMainBinding
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter

class MainActivity : MvpAppCompatActivity(), MainView {

    private lateinit var binding: ActivityMainBinding
    private val presenter by moxyPresenter { MainPresenterImpl() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setListeners()
    }

    private fun setListeners() {
        with(binding) {
            btnCounter1.setOnClickListener { presenter.onCounter1Clicked() }
            btnCounter2.setOnClickListener { presenter.onCounter2Clicked() }
            btnCounter3.setOnClickListener { presenter.onCounter3Clicked() }
        }
    }

    override fun setCounter1(value: Int) {
        binding.btnCounter1.text = value.toString()
    }

    override fun setCounter2(value: Int) {
        binding.btnCounter2.text = value.toString()
    }

    override fun setCounter3(value: Int) {
        binding.btnCounter3.text = value.toString()
    }
}