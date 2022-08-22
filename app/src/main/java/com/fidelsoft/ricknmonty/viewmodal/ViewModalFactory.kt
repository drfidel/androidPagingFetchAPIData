package com.fidelsoft.ricknmonty.viewmodal

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.fidelsoft.ricknmonty.repo.CharacterRepository

@Suppress ( "UNCHECKED_CAST")
class ViewModalFactory( private val repository: CharacterRepository)
    : ViewModelProvider.Factory  {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CharacterViewModal::class.java)){
            return CharacterViewModal(repository) as T
        } else {
            throw IllegalArgumentException("Unknown ViewModel Class: ${modelClass.name}")
        }
    }

}