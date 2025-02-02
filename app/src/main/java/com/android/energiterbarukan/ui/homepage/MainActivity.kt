package com.android.energiterbarukan.ui.homepage

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.energiterbarukan.adapter.DataEnergiAdapter
import com.android.energiterbarukan.databinding.ActivityMainBinding
import com.android.energiterbarukan.helper.ViewModelFactory
import com.android.energiterbarukan.ui.addupdatedelete.DataEnergiAUDActivity

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: DataEnergiAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mainViewModel = obtainViewModel(this@MainActivity)
        mainViewModel.getAllDataEnergi().observe(this) { dataEnergiList ->
            if (dataEnergiList != null) {
                adapter.setListDataEnergi(dataEnergiList)
            }
        }

        adapter = DataEnergiAdapter()

        binding.rvDataEnergi.layoutManager = LinearLayoutManager(this)
        binding.rvDataEnergi.setHasFixedSize(true)
        binding.rvDataEnergi.adapter = adapter

        binding.fabAdd.setOnClickListener {
            val intent = Intent(this@MainActivity, DataEnergiAUDActivity::class.java)
            startActivity(intent)
        }
    }

    private fun obtainViewModel(activity: AppCompatActivity): MainViewModel {
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory).get(MainViewModel::class.java)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}