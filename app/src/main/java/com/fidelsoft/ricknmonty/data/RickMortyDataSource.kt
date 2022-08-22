package com.fidelsoft.ricknmonty.data

import android.net.Uri
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.fidelsoft.ricknmonty.data.modal.Info
import com.fidelsoft.ricknmonty.data.modal.Result
import com.fidelsoft.ricknmonty.data.modal.RickMortyResponse
import com.fidelsoft.ricknmonty.network.RickMortyApiService
import com.fidelsoft.ricknmonty.utils.STARTING_PAGE

class RickMortyDataSource (
    private val apiService: RickMortyApiService
    ): PagingSource<Int, Result>() {

    override val keyReuseSupported: Boolean = true

    override fun getRefreshKey(state: PagingState<Int, Result>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Result> {

        val position = if (params.key == null) STARTING_PAGE else params.key

        try {

            val response: RickMortyResponse = apiService.getAllCharacters()
            val info: Info = response.info
            val next = Uri.parse(info.next).getQueryParameter("page")

            val characters: List<Result> = response.results


            return LoadResult.Page(
                    data = characters,
                    prevKey = if(position == STARTING_PAGE) null else position!! - 1,
                    nextKey = next?.toInt()
                )

        } catch (e: Exception){
            return LoadResult.Error(e)
        }
    }

}