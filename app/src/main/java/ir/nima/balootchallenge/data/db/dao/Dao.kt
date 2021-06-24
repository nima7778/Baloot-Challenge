package ir.nima.balootchallenge.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ir.nima.balootchallenge.data.db.entities.ArticleEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface Dao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(data: ArticleEntity)

    @Query("SELECT * FROM article")
    fun getAllFromDb(): Flow<List<ArticleEntity>>

}