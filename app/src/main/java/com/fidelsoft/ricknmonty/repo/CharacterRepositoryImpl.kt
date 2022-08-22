package com.fidelsoft.ricknmonty.repo

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.fidelsoft.ricknmonty.data.RickMortyDataSource
import com.fidelsoft.ricknmonty.data.modal.Result
import com.fidelsoft.ricknmonty.network.RickMortyApiService
import com.fidelsoft.ricknmonty.utils.NETWORK_PAGE_SIZE
import kotlinx.coroutines.flow.Flow

class CharacterRepositoryImpl(
    private val apiService: RickMortyApiService
) : CharacterRepository {

    private val pagingConfig = PagingConfig(
        pageSize = NETWORK_PAGE_SIZE,
        enablePlaceholders = false
    )

    override fun getCharaters(): Flow<PagingData<Result>> {

        return Pager(
            config = pagingConfig,
            pagingSourceFactory = { RickMortyDataSource(apiService) }
        ).flow
    }
}