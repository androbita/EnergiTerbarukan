package com.android.energiterbarukan.ui.addupdatedelete

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.android.energiterbarukan.R
import com.android.energiterbarukan.database.DataEnergi
import com.android.energiterbarukan.databinding.ActivityDataEnergiAudBinding
import com.android.energiterbarukan.helper.ViewModelFactory

class DataEnergiAUDActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_DATA = "extra_data"
        const val ALERT_DIALOG_CLOSE = 10
        const val ALERT_DIALOG_DELETE = 20
    }

    private var isEdit = false
    private var dataEnergi: DataEnergi? = null
    private lateinit var dataEnergiAUDViewModel: DataEnergiAUDViewModel

    private var _binding: ActivityDataEnergiAudBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data_energi_aud)

        _binding = ActivityDataEnergiAudBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dataEnergiAUDViewModel = obtainViewModel(this@DataEnergiAUDActivity)

        dataEnergi = intent.getParcelableExtra(EXTRA_DATA)
        if (dataEnergi != null) {
            isEdit = true
        } else {
            dataEnergi = DataEnergi()
        }

        val actionBarTitle: String
        val btnTitle: String

        if (isEdit) {
            actionBarTitle = "Ubah"
            btnTitle = "Update"
            if (dataEnergi != null) {
                dataEnergi?.let { dataEnergi ->
                    binding.edtTitle.setText(dataEnergi.title)
                    binding.edtDescription.setText(dataEnergi.description)
                }
            }
        } else {
            actionBarTitle = "Tambah"
            btnTitle = "Simpan"
        }

        supportActionBar?.title = actionBarTitle
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.btnSubmit.text = btnTitle

        binding.btnSubmit.setOnClickListener {
            val title = binding.edtTitle.text.toString().trim()
            val description = binding.edtDescription.text.toString().trim()

            when {
                title.isEmpty() -> {
                    binding.edtTitle.error = "Field can not be blank"
                }

                description.isEmpty() -> {
                    binding.edtDescription.error = "Field can not be blank"
                }

                else -> {
                    dataEnergi.let { dataEnergi ->
                        dataEnergi?.title = title
                        dataEnergi?.description = description
                    }
                    if (isEdit) {
                        dataEnergiAUDViewModel.update(dataEnergi as DataEnergi)
                        Toast.makeText(this, "Satu item berhasil diedit", Toast.LENGTH_SHORT).show()
                    } else {
                        dataEnergiAUDViewModel.insert(dataEnergi as DataEnergi)
                        Toast.makeText(this, "Satu item berhasil disimpan", Toast.LENGTH_SHORT)
                            .show()
                    }
                    finish()
                }
            }
        }

        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                showAlertDialog(ALERT_DIALOG_CLOSE)
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        if (isEdit){
            menuInflater.inflate(R.menu.menu_form, menu)
        }
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.action_delete -> showAlertDialog(ALERT_DIALOG_DELETE)
            android.R.id.home -> showAlertDialog(ALERT_DIALOG_CLOSE)
        }
        return super.onOptionsItemSelected(item)
    }

    private fun showAlertDialog(type: Int) {
        val isDialogClose = type == ALERT_DIALOG_CLOSE
        val dialogTitle: String
        val dialogMessage: String
        if (isDialogClose) {
            dialogTitle = "Cancel"
            dialogMessage = "Dibatalkan"
        } else {
            dialogMessage = "Dihapus"
            dialogTitle = "Delete"
        }
        val alertDialogBuilder = AlertDialog.Builder(this)
        with(alertDialogBuilder) {
            setTitle(dialogTitle)
            setMessage(dialogMessage)
            setCancelable(false)
            setPositiveButton("Ya") { _, _ ->
                if (!isDialogClose) {
                    dataEnergiAUDViewModel.delete(dataEnergi as DataEnergi)
                    Toast.makeText(this@DataEnergiAUDActivity, "Satu item berhasil dihapus", Toast.LENGTH_SHORT).show()
                }
                finish()
            }
            setNegativeButton("Tidak") { dialog, _ -> dialog.cancel() }
        }
        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun obtainViewModel(activity: AppCompatActivity): DataEnergiAUDViewModel {
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory).get(DataEnergiAUDViewModel::class.java)
    }

}