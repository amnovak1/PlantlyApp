package com.example.plantly

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.plantly.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var choreAdapter: PlantAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        choreAdapter = PlantAdapter(mutableListOf())

        binding.rvPlantList.adapter = choreAdapter
        binding.rvPlantList.layoutManager = LinearLayoutManager(this)


        binding.btnAddChore.setOnClickListener {
            val choreTitle = binding.etPlantTitle.text.toString()
            if (choreTitle.isNotEmpty()) {
                val chore = Chore(choreTitle)
                choreAdapter.addChore(chore)
                binding.etPlantTitle.text.clear()
            }
        }

        binding.btnDeleteChore.setOnClickListener {
            choreAdapter.deleteChore()
        }

    }




}