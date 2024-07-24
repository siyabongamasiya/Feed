package news.project.feed.View

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import news.project.feed.Model.Feed
import news.project.feed.R
import news.project.feed.ui.theme.FeedTheme

@Composable
fun DrawNewsFeedScreen(mainNavigator : NavHostController){
    FeedTheme {
        Scaffold(topBar = {
            topSection(navController = mainNavigator, title = "News Feed",false)
        }) {paddingValues ->
            midSectionNewsFeed(mainNavigator,paddingValues)
        }
    }
}

@Composable
fun midSectionNewsFeed(navController: NavHostController, paddingValues: PaddingValues) {

    var Feeds = rememberSaveable {
        mutableStateOf(listOf(Feed("Barca wins",
            "Barca wins copa dal rey",
            "https://www.google.com",
            "fabrizio",
            true),
            Feed("She did it again",
                "Lizzy wins another medal",
                "https://www.google.com",
                "daily magazine",
                true),
            Feed("Window opens",
                "Transfer window opens",
                "https://www.google.com",
                "The sun",
                true),
            Feed("Mbappe scores another",
                "Mbappe scores another hattrick",
                "https://www.google.com",
                "soccer zone",
                false)))
    }

    FeedList(feedlist = Feeds.value, navController = navController, paddingValues = paddingValues.calculateTopPadding() + 10.dp)

}

@Composable
fun FeedList(feedlist : List<Feed>,navController: NavHostController,paddingValues: Dp){
    LazyColumn(modifier = Modifier
        .fillMaxSize()
        .padding(
            start = 10.dp, end = 10.dp,
            top = paddingValues
        ),
        verticalArrangement = Arrangement.spacedBy(60.dp),
        horizontalAlignment = Alignment.CenterHorizontally){


        itemsIndexed(feedlist){index,feed ->
            FeedItem(modifier = Modifier
                .fillMaxWidth()
                .clickable { navController.navigate(mainScreens.story(index)) },
                feed){

            }
        }


    }
}

@Composable
fun FeedItem(modifier: Modifier,feed : Feed,onSave: () -> Unit) {
    val icon = if(feed.saved){
        Icons.Default.Bookmark
    }else{
        Icons.Default.BookmarkBorder
    }

    ElevatedCard(modifier = modifier,
        elevation = CardDefaults.elevatedCardElevation(
            defaultElevation = 10.dp,
        )) {

        Column(
            modifier= Modifier.padding(10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally) {

            Text(modifier = Modifier.fillMaxWidth(),
                text = feed.title,
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.titleLarge
            )
            Text(modifier = Modifier.fillMaxWidth(),
                text = feed.description,
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.bodyLarge)
            Image(modifier = Modifier.size(150.dp),
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = "feed image")
            Text(modifier = Modifier.fillMaxWidth(),
                text = feed.author,
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.titleSmall)
            Button2(Modifier,icon){

            }

        }
    }



}

@Composable
fun Button2(modifier: Modifier,icon : ImageVector,onclick : () -> Unit){
    ElevatedButton(modifier=modifier,
        colors = ButtonDefaults.buttonColors(
            containerColor =MaterialTheme.colorScheme.tertiary,
        ),
        onClick = { onclick.invoke() }) {

        Row(modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center) {
            Row(verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                Icon(
                    modifier = Modifier.size(30.dp),
                    tint =MaterialTheme.colorScheme.onSurface,
                    imageVector = icon,
                    contentDescription = "button 2 icon"
                )
            }
        }

    }
}
