package ir.nima.balootchallenge.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import ir.nima.balootchallenge.data.api.service.Service
import ir.nima.balootchallenge.data.pagingSource.NewsPagingSource
import ir.nima.balootchallenge.utils.Constant
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainRepository @Inject constructor(private val service: Service) {
    fun getAll() =
        Pager(
            config = PagingConfig(
                pageSize = Constant.PAGE_SIZE,
                maxSize = 100,
                enablePlaceholders = false,
            ),
            pagingSourceFactory = { NewsPagingSource(service) }
        ).liveData
}