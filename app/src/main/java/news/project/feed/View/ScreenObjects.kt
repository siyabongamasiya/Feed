package news.project.feed.View

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Newspaper
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import news.project.feed.Model.Feed


sealed class mainScreens(){
    @Serializable
    object signupOrsignin : mainScreens()
    @Serializable
    data class signup(val defVal : String = "") : mainScreens()
    @Serializable
    data class home(val defVal : String = "") : mainScreens()
    @Serializable
    data class story(val id : Int) : mainScreens()
}


//home screens
@Serializable
sealed class homeScreens(val index : Int, val title : String){
    @Serializable
    object NewsFeed : homeScreens(0,"news feed")
    @Serializable
    data class Search(val defVal : String = "") : homeScreens(1,"search")
    @Serializable
    data class Saved(val defVal : String = "") : homeScreens(2,"saved")
    @Serializable
    data class Profile(val defVal : String = "") : homeScreens(3,"profile")
}

