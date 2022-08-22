package com.fidelsoft.ricknmonty.repo

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import com.fidelsoft.ricknmonty.data.modal.Result

interface CharacterRepository {
    fun getCharaters(): Flow<PagingData<Result>>
}