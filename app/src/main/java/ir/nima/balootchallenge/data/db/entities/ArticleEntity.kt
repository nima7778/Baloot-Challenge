package ir.nima.balootchallenge.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "article",indices = [Index(value = ["title", "urlToImage"], unique = true)])
data class ArticleEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    @ColumnInfo(name = "author")
    val author: String= "",
    @ColumnInfo(name = "content")
    val content: String = "",
    @ColumnInfo(name = "description")
    val description: String,
    @ColumnInfo(name = "published_at")
    val publishedAt: String="",
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "url")
    val url: String = "",
    @ColumnInfo(name = "urlToImage")
    val urlToImage: String
)