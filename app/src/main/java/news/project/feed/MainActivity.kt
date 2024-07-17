package news.project.feed

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import news.project.feed.View.DiscusionAreaScreen
import news.project.feed.View.HomeScreen
import news.project.feed.View.SignUpOrLoginScreen
import news.project.feed.View.StoryScreen
import news.project.feed.View.discussionarea
import news.project.feed.View.home
import news.project.feed.View.signupOrsignin
import news.project.feed.View.story
import news.project.feed.ui.theme.FeedTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination ="signuporsignin"){
                composable<signupOrsignin> {
                    SignUpOrLoginScreen()
                }

                composable<home> {
                    HomeScreen()
                }

                composable<discussionarea> {
                    DiscusionAreaScreen()
                }

                composable<story> {
                    StoryScreen()
                }
            }
        }
    }
}
