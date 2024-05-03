package me.nasiri.coinmaster.domain.model


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import me.nasiri.coinmaster.util.Constans.TableNews

data class NewsData(
    @SerializedName("Data")
    val `data`: List<Data>,
    @SerializedName("HasWarning")
    val hasWarning: Boolean,
    @SerializedName("Message")
    val message: String,
    @SerializedName("Promoted")
    val promoted: List<Any>,
    @SerializedName("RateLimit")
    val rateLimit: RateLimit,
    @SerializedName("Type")
    val type: Int,
) {
    data class Data(
        @SerializedName("body")
        val body: String,
        @SerializedName("categories")
        val categories: String,
        @SerializedName("downvotes")
        val downvotes: String,
        @SerializedName("guid")
        val guid: String,
        @SerializedName("id")
        val id: String,
        @SerializedName("imageurl")
        val imageurl: String,
        @SerializedName("lang")
        val lang: String,
        @SerializedName("published_on")
        val publishedOn: Int,
        @SerializedName("source")
        val source: String,
        @SerializedName("source_info")
        val sourceInfo: SourceInfo,
        @SerializedName("tags")
        val tags: String,
        @SerializedName("title")
        val title: String,
        @SerializedName("upvotes")
        val upvotes: String,
        @SerializedName("url")
        val url: String,
    ) {
        data class SourceInfo(
            @SerializedName("img")
            val img: String,
            @SerializedName("lang")
            val lang: String,
            @SerializedName("name")
            val name: String,
        )
    }

    class RateLimit
}

fun NewsData.toList(): List<CusNews> =
    this.data.map { CusNews(title = it.title, url = it.url, id = it.id.toLong()) }

@Entity(tableName = TableNews)
data class CusNews(
    @PrimaryKey(autoGenerate = true)
    val id: Long?,
    val title: String,
    val url: String,
)