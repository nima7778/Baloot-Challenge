package ir.nima.balootchallenge.data.db.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import ir.nima.balootchallenge.data.db.entities.ArticleEntity

@Database(entities = [ArticleEntity::class] , version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun dao(): Dao
}