package com.fidelsoft.ricknmonty.viewmodal

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.fidelsoft.ricknmonty.databinding.ItemLoadingStateBinding

class CharacterLoadingAdaptor(private val retry: () -> Unit):
LoadStateAdapter<CharacterLoadingAdaptor.LoadStateViewHolder>(){

    inner class LoadStateViewHolder(private val binding: ItemLoadingStateBinding, retry: () -> Unit):
    RecyclerView.ViewHolder(binding.root){

        init {
            binding.buRetry.setOnClickListener{
                retry.invoke()
            }
        }

        fun bindState(loadState: LoadState){
            if (loadState is LoadState.Error){
                binding.tvErrorMsg.text = loadState.error.localizedMessage
            }

            binding.progressBar.isVisible = loadState is LoadState.Loading
            binding.tvErrorMsg.isVisible = loadState !is LoadState.Loading
            binding.buRetry.isVisible = loadState !is LoadState.Loading
        }

    }

    override fun onBindViewHolder(holder: LoadStateViewHolder, loadState: LoadState) {
        holder.bindState(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadStateViewHolder {
        val binding = ItemLoadingStateBinding.inflate(LayoutInflater.from(parent.context))
        return LoadStateViewHolder(binding,retry)
    }
}
