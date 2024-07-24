package news.project.feed.Model

import java.io.Serializable

@kotlinx.serialization.Serializable
data class Feed(val title : String,
                val description : String,
                val url : String,
                val author : String,
                val saved : Boolean) : Serializable

