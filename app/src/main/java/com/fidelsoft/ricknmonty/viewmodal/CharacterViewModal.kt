package com.fidelsoft.ricknmonty.viewmodal

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.fidelsoft.ricknmonty.repo.CharacterRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import com.fidelsoft.ricknmonty.data.modal.Result
import kotlinx.coroutines.flow.asStateFlow

class CharacterViewModal (
    private val repository: CharacterRepository
        ) : ViewModel(){

    private val mutableCharacterData: MutableStateFlow<PagingData<Result>?> = MutableStateFlow((null))

    val characterData = mutableCharacterData.asStateFlow()

            fun getCharacters(){
                viewModelScope.launch {
                    repository.getCharaters()
                        .cachedIn(viewModelScope)
                        .collect { data ->
                            mutableCharacterData.value = data

                        }
                }
            }
}