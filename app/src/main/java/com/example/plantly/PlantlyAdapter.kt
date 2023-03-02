package com.example.plantly

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.plantly.R.layout.chore_todo
import com.example.plantly.databinding.ChoreTodoBinding


class PlantAdapter  (
    private val chores: MutableList<Chore>

) : RecyclerView.Adapter<PlantAdapter.PlantViewHolder>(){

    private lateinit var binding: ChoreTodoBinding
    class PlantViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlantViewHolder {
        return PlantViewHolder(
             LayoutInflater.from(parent.context).inflate(
                 chore_todo,
                 parent,
                 false
             )
        )
    }

    fun addChore(chore: Chore) {
        chores.add(chore)
        notifyItemInserted(chores.size -1)
    }

    fun deleteChore() {
        chores.removeAll { chore ->
            chore.isChecked
        }

        notifyDataSetChanged()
    }

    private fun toggleStrikeTrough(tvPlantTitle: TextView, isChecked: Boolean ) {
        if (isChecked) {
            tvPlantTitle.paintFlags = tvPlantTitle.paintFlags or STRIKE_THRU_TEXT_FLAG
        } else {
            tvPlantTitle.paintFlags = tvPlantTitle.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()

        }
    }

    override fun onBindViewHolder(holder: PlantViewHolder, position: Int) {
        var curChore = chores[position]
        holder.itemView.apply {
            binding.tvPlantTitle.text = curChore.title
            binding.cbDone.isChecked = curChore.isChecked
            toggleStrikeTrough(binding.tvPlantTitle, curChore.isChecked)
            binding.cbDone.setOnCheckedChangeListener { _, isChecked ->
                toggleStrikeTrough(binding.tvPlantTitle, isChecked)
                curChore.isChecked = !curChore.isChecked
            }
        }
    }

    override fun getItemCount(): Int {
        return chores.size
    }
}