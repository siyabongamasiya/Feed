package news.project.feed.View

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import news.project.feed.Model.Feed
import news.project.feed.ui.theme.FeedTheme

@Composable
fun DrawSearchScreen(mainNavigator : NavHostController){
    FeedTheme {
        Scaffold(topBar = {
            topSection(navController = mainNavigator, title = "Search", Back = false)
        }) {paddingValues ->
            midSectionSearch(mainNavigator,paddingValues)
        }
    }
}

@Composable
fun midSectionSearch(navController: NavHostController, paddingValues: PaddingValues) {
    var search = rememberSaveable {
        mutableStateOf("")
    }

    var Feeds = rememberSaveable {
        mutableStateOf(listOf(
            Feed("Barca wins",
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
                false)
        ))
    }

    Column(modifier = Modifier.fillMaxSize()
        .padding(start = 10.dp, end = 10.dp, top = paddingValues.calculateTopPadding() + 10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally) {

        OutlinedTextField(
            modifier = Modifier
                .clip(RoundedCornerShape(16)),
            colors = TextFieldDefaults.colors(
                focusedTextColor = Color.Black,
                cursorColor = Color.Black
            ),
            value = search.value,
            onValueChange = {newvalue ->
                search.value = newvalue
            },
            placeholder = {
                Text(text = "Search")
            })

        FeedList(feedlist = Feeds.value, navController = navController, paddingValues = 10.dp)

    }
}
