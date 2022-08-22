package com.fidelsoft.ricknmonty.utils

import androidx.recyclerview.widget.DiffUtil
import com.fidelsoft.ricknmonty.data.modal.Result

object CharacterDiffUtil: DiffUtil.ItemCallback<Result>() {
    override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
        return oldItem.id == newItem.id
    }
}
