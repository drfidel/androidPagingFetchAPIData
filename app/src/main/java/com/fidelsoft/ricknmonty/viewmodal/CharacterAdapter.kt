package com.fidelsoft.ricknmonty.viewmodal

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.fidelsoft.ricknmonty.utils.CharacterDiffUtil
import com.fidelsoft.ricknmonty.data.modal.Result
import com.fidelsoft.ricknmonty.databinding.ItemViewBinding

class CharacterAdapter :
    PagingDataAdapter<Result, CharacterAdapter.CharacterViewHolder>(CharacterDiffUtil) {

    inner class CharacterViewHolder(private val binding: ItemViewBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind (character: Result){
            binding.tvName.text = character.name
            binding.Tvorigin.text = character.origin.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val binding = ItemViewBinding.inflate(LayoutInflater.from(parent.context))
        return CharacterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val character:Result? = getItem(position)
        if (character != null){
            holder.bind(character)
        }
    }
}