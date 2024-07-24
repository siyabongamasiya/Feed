package news.project.feed.View

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import googlefonts.fontFamily
import news.project.feed.Model.Feed
import news.project.feed.R
import news.project.feed.ui.theme.FeedTheme


@Composable
fun DrawStoryScreen(id: Int,navController: NavHostController){
    val database = listOf(Feed("Barca wins",
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
            false))

    val feed = database.get(id)

    FeedTheme {
        Scaffold(topBar = {
            topSection(navController = navController,
                title = "Story",
                Back = true)
        }) {paddingValues ->
            midSectionStory(navController,paddingValues,feed)
        }
    }
}

@Composable
fun midSectionStory(navController: NavHostController, paddingValues: PaddingValues,feed: Feed) {

    ConstraintLayout(
        Modifier
            .fillMaxSize()
            .padding(
                start = 10.dp,
                end = 10.dp,
                top = paddingValues.calculateTopPadding() + 10.dp
            )) {
        val (storyref,buttonref) = createRefs()

        Column(modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surface, shape = RoundedCornerShape(16))
            .constrainAs(storyref) {
                top.linkTo(
                    parent.top,
                    margin = paddingValues.calculateTopPadding() + 10.dp
                )
                bottom.linkTo(
                    buttonref.top,
                    margin = 10.dp
                )
            }
            .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally) {

            Image(
                modifier = Modifier.size(200.dp),
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = "story image")
            Text(modifier = Modifier.fillMaxWidth(), text = buildAnnotatedString {
                withStyle(
                    SpanStyle(fontFamily = fontFamily,
                        fontWeight = FontWeight.Bold,
                        fontSize = 22.sp,
                        color = MaterialTheme.colorScheme.onSurface)
                ){
                    append("Title \n")
                }

                withStyle(
                    SpanStyle(fontFamily = fontFamily,
                        fontWeight = FontWeight.Normal,
                        fontSize = 22.sp,
                        color = MaterialTheme.colorScheme.onSurface)
                ){
                    append(feed.title)
                }
            })
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .scrollable(rememberScrollState(),Orientation.Vertical),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(modifier = Modifier.fillMaxWidth(),text = buildAnnotatedString {
                    withStyle(
                        SpanStyle(fontFamily = fontFamily,
                            fontWeight = FontWeight.Bold,
                            fontSize = 22.sp,
                            color = MaterialTheme.colorScheme.onSurface)
                    ){
                        append("Description \n")
                    }

                    withStyle(
                        SpanStyle(fontFamily = fontFamily,
                            fontWeight = FontWeight.Normal,
                            fontSize = 16.sp,
                            color = MaterialTheme.colorScheme.onSurface)
                    ){
                        append(feed.description)
                    }
                })
            }


        }

        val icon = if(feed.saved){
            Icons.Default.Bookmark
        }else{
            Icons.Default.BookmarkBorder
        }
        Button2(modifier = Modifier
            .fillMaxWidth()
            .constrainAs(buttonref) {
                bottom.linkTo(
                    parent.bottom,
                    margin = 60.dp
                )
            }, icon = icon) {

        }
    }
}
