package com.rino.imageconverter

import android.Manifest
import android.content.ContentValues
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.os.Environment.getExternalStoragePublicDirectory
import android.os.PersistableBundle
import android.provider.MediaStore
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.rino.imageconverter.databinding.ActivityMainBinding
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : MvpAppCompatActivity(), MainView {

    companion object {
        private const val TAG = "MainActivity"
        private const val BITMAP_URI_KEY = "BITMAP_URI_KEY"
    }

    private val presenter by moxyPresenter { MainPresenter() }

    private lateinit var binding: ActivityMainBinding

    private var bitmapUri: Uri? = null

    private val getContentLauncher =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            bitmapUri = uri
            presenter.showImage()
        }

    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted: Boolean ->
            if (isGranted) {
                presenter.convertImageToPng()
            } else {
                binding.root.showSnackBar(
                    R.string.need_permissions_to_write_storage,
                    actionStringId = R.string.settings,
                    action = { this.openAppSystemSettings() }
                )
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        savedInstanceState?.let {
            bitmapUri = it.getParcelable(BITMAP_URI_KEY)
        }

        with(binding) {
            selectButton.setOnClickListener { presenter.selectImage() }

            convertButton.setOnClickListener {
                presenter.convertImageToPngAfterCheckingPermission()
            }
        }
    }

    override fun selectImage() {
        getContentLauncher.launch("image/jpeg")
    }

    override fun showImage() {
        if (bitmapUri == null) return

        try {
            val source = ImageDecoder.createSource(contentResolver, bitmapUri!!)
            val bitmap = ImageDecoder.decodeBitmap(source)

            with(binding) {
                image.apply {
                    setImageBitmap(bitmap)
                    isVisible = true
                }

                convertButton.isVisible = true
            }
        } catch (e: Exception) {
            Log.e(TAG, "Error while getting image", e)
        }
    }

    override fun convertImageToPng() {
        val fileName: String =
            SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())

        val fileOutputStream: OutputStream
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val values = ContentValues().apply {
                put(MediaStore.MediaColumns.DISPLAY_NAME, fileName)
                put(MediaStore.MediaColumns.MIME_TYPE, "image/png")
                put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_PICTURES)
            }

            val uri =
                contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)
                    ?: return

            fileOutputStream =
                contentResolver.openOutputStream(uri) ?: return

            Log.d(TAG, "Saving image file via MediaStore.")
        } else {
            // These files are internal to the applications, and not typically visible to the user as media.
            // val storageDir: File? = getExternalFilesDir(Environment.DIRECTORY_PICTURES)

            val storageDir: File? =
                getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)

            val outputFile = File(storageDir!!.absolutePath, "$fileName.png")
            fileOutputStream = FileOutputStream(outputFile)

            Log.d(
                TAG,
                "Saving image file via getExternalStoragePublicDirectory. Path - $outputFile"
            )
        }

        val source = ImageDecoder.createSource(contentResolver, bitmapUri!!)
        val bitmap = ImageDecoder.decodeBitmap(source)

        fileOutputStream.use {
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream)
        }

        showToast(R.string.completed_successfully)
    }

    override fun convertImageToPngAfterCheckingPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            Log.d(
                TAG,
                "In SDK=${Build.VERSION.SDK_INT}  WRITE_EXTERNAL_STORAGE permission check is not required"
            )
            presenter.convertImageToPng()
            return
        }

        when {
            ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) == PackageManager.PERMISSION_GRANTED -> {
                Log.d(TAG, "WRITE_EXTERNAL_STORAGE permission is granted")
                presenter.convertImageToPng()
            }
            shouldShowRequestPermissionRationale(Manifest.permission.WRITE_EXTERNAL_STORAGE) -> {
                binding.root.showSnackBar(
                    R.string.need_permissions_to_write_storage,
                    actionStringId = R.string.settings,
                    action = { this.openAppSystemSettings() }
                )
            }
            else -> {
                requestPermissionLauncher.launch(Manifest.permission.WRITE_EXTERNAL_STORAGE)
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putParcelable(BITMAP_URI_KEY, bitmapUri)
        super.onSaveInstanceState(outState)
    }
}