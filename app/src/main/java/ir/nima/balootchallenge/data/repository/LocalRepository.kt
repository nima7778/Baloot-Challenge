package ir.nima.balootchallenge.data.repository

import ir.nima.balootchallenge.data.db.entities.ArticleEntity
import ir.nima.balootchallenge.data.db.dao.Dao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalRepository @Inject constructor(private val dao: Dao) {
    val getDao: Dao =dao
    fun insertData(pagingArticle: ArticleEntity) {
        CoroutineScope(SupervisorJob()).launch {
            dao.insert(pagingArticle)
        }
    }

}