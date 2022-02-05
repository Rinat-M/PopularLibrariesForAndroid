package com.rino.imageconverter

import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts.GetContent
import androidx.core.view.isVisible
import com.rino.imageconverter.databinding.ActivityMainBinding
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter

class MainActivity : MvpAppCompatActivity(), MainView {

    companion object {
        private const val TAG = "MainActivity"
    }

    private val presenter by moxyPresenter { MainPresenter() }

    private lateinit var viewBinding: ActivityMainBinding

    private val getContent = registerForActivityResult(GetContent()) { uri: Uri ->
        try {
            val source = ImageDecoder.createSource(contentResolver, uri)
            val bitmap = ImageDecoder.decodeBitmap(source)

            with(viewBinding) {
                image.apply {
                    setImageBitmap(bitmap)
                    isVisible = true
                }

                selectButton.isVisible = false
                convertButton.isVisible = true
            }
        } catch (e: Exception) {
            Log.e(TAG, "Error while getting image", e)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        viewBinding.selectButton.setOnClickListener { presenter.selectImage() }
    }

    override fun selectImage() {
        getContent.launch("image/jpeg")
    }
}