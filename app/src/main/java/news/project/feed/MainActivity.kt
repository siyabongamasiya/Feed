package news.project.feed

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import news.project.feed.View.DrawHomeScreen
import news.project.feed.View.DrawSignUpOrLoginScreen
import news.project.feed.View.DrawSignUpScreen
import news.project.feed.View.DrawStoryScreen
import news.project.feed.View.mainScreens

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DrawMainScreen()
        }
    }
}

@Preview
@Composable
fun DrawMainScreen(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination ="signinorsignup"){
        composable("signinorsignup") {
            DrawSignUpOrLoginScreen(navController)
        }

        composable<mainScreens.signup> {
            DrawSignUpScreen(navController)
        }

        composable<mainScreens.home> {
            DrawHomeScreen(navController)
        }

        composable<mainScreens.story> {
            val route = it.toRoute<mainScreens.story>()
            DrawStoryScreen(route.id,navController)
        }
    }
}
