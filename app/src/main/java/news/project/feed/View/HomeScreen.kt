package news.project.feed.View

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Newspaper
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import news.project.feed.ui.theme.FeedTheme

@Composable
fun DrawHomeScreen(navControllerMain : NavHostController){
    FeedTheme {
        midSectionHome(mainNavController = navControllerMain)
    }
}

@Composable
fun midSectionHome(mainNavController: NavHostController){
    val navControllerSub = rememberNavController()

    Scaffold (bottomBar = {
        BottomSection(navControllerSub)
    }){paddingValues ->

        NavHost(modifier = Modifier
            .fillMaxSize()
            .padding(
                top = paddingValues.calculateTopPadding() + 10.dp,
                bottom = paddingValues.calculateBottomPadding() + 10.dp
            ),
            navController = navControllerSub,
            startDestination =homeScreens.NewsFeed){
            composable<homeScreens.NewsFeed> {
                DrawNewsFeedScreen(mainNavController)
            }

            composable<homeScreens.Search> {
                DrawSearchScreen(mainNavController)
            }

            composable<homeScreens.Saved> {
                DrawSavedScreen(mainNavController)
            }

            composable<homeScreens.Profile> {
                DrawProfileScreen()
            }
        }
    }
}

@Composable
fun BottomSection(navController: NavHostController) {
    val selecteditem = rememberSaveable {
        mutableStateOf(0)
    }

    NavigationBar {


        for (n in 0..3){
            var homescreen : homeScreens
            val icon : ImageVector
            val color : Color

            when(n){
                0 -> {
                    homescreen = homeScreens.NewsFeed
                    icon = Icons.Default.Newspaper
                }
                1 -> {
                    homescreen = homeScreens.Search()
                    icon = Icons.Default.Search
                }
                2 -> {
                    homescreen = homeScreens.Saved()
                    icon = Icons.Default.Bookmark
                }
                else -> {
                    homescreen = homeScreens.Profile()
                    icon = Icons.Default.AccountCircle
                }
            }
            NavigationBarItem(
                selected = selecteditem.value == n,
                colors = NavigationBarItemColors(
                    selectedIconColor = MaterialTheme.colorScheme.tertiary,
                    selectedIndicatorColor = Color.LightGray,
                    selectedTextColor = MaterialTheme.colorScheme.tertiary,
                    unselectedIconColor = MaterialTheme.colorScheme.onSurface,
                    unselectedTextColor = MaterialTheme.colorScheme.onSurface,
                    disabledIconColor = MaterialTheme.colorScheme.onSurface,
                    disabledTextColor = MaterialTheme.colorScheme.onSurface
                ),
                onClick = {
                    selecteditem.value = n
                    navController.navigate(homescreen){
                        popUpTo(0)
                    }},
                icon = { Icon(
                    modifier = Modifier.size(30.dp),
                    imageVector = icon,
                    contentDescription = homescreen.title)},
                label = {
                    Text(text = homescreen.title,
                        style = MaterialTheme.typography.titleSmall,
                        color = if (selecteditem.value == n) MaterialTheme.colorScheme.tertiary
                        else MaterialTheme.colorScheme.onSurface)
                })
        }

    }
}
