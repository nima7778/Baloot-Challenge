package ir.nima.balootchallenge.data.pagingSource

import androidx.paging.PagingSource
import ir.nima.balootchallenge.data.api.model.Article
import ir.nima.balootchallenge.data.api.model.ErrorModel
import ir.nima.balootchallenge.data.api.service.Service
import ir.nima.balootchallenge.utils.Constant
import ir.nima.balootchallenge.utils.PreferenceUtils
import java.net.SocketTimeoutException

private const val STARTING_PAGE_INDEX = 1

class NewsPagingSource(private val service: Service) : PagingSource<Int, Article>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        val position = params.key ?: STARTING_PAGE_INDEX
        return try {
            val response = service.getNews(
                apiKey = Constant.API_KEY,
                page = position,
                pageSize = Constant.PAGE_SIZE
            )

            if (response.code() != 200) {
                val error = PreferenceUtils.parseJsonError<ErrorModel>(
                    response.errorBody()?.source()!!.inputStream()
                )
                throw Exception(error.message)
            }

            LoadResult.Page(
                data = response.body()?.articles!!,
                prevKey = if (position == STARTING_PAGE_INDEX) null else position - 1,
                nextKey = if (response.body()!!.articles.isEmpty()) null else position + 1
            )
        } catch (socketTimeOutException: SocketTimeoutException) {
            LoadResult.Error(socketTimeOutException)
        }catch (exception: Exception){
            LoadResult.Error(exception)
        }


    }
}
