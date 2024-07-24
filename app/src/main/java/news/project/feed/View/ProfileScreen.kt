package news.project.feed.View

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import news.project.feed.Model.Profile
import news.project.feed.R
import news.project.feed.ui.theme.FeedTheme

@Preview
@Composable
fun DrawProfileScreen(){
    FeedTheme {
        val navController = rememberNavController()
        Scaffold(topBar = {
            topSection(navController = navController, title = "Profile", Back = false)
        }) {paddingValues ->
            midSectionProfile(navController,paddingValues)
        }
    }
}

@Composable
fun midSectionProfile(navController: NavHostController, paddingValues: PaddingValues) {
    var profile by remember {
        mutableStateOf(Profile("Masiya",
            "SiyabongaKhanyile76@gmail.com",
            "12345"))
    }

    ConstraintLayout(modifier = Modifier.fillMaxSize()
        .background(MaterialTheme.colorScheme.background)
        .padding(start = 10.dp, end = 10.dp, top = paddingValues.calculateTopPadding() + 10.dp)) {
        val (profileref) = createRefs()

        Column(modifier = Modifier.fillMaxWidth(0.5f)
            .background(MaterialTheme.colorScheme.surface,
                shape = RoundedCornerShape(16))
            .constrainAs(profileref){
            centerTo(parent)
        }.padding(10.dp),
            verticalArrangement = Arrangement.spacedBy(50.dp),
            horizontalAlignment = Alignment.CenterHorizontally) {

            Column(modifier = Modifier,
                verticalArrangement = Arrangement.spacedBy(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally) {

                Image(
                    modifier = Modifier.size(150.dp),
                    painter = painterResource(id = R.drawable.ic_launcher_foreground),
                    contentDescription = "profile pictue")

                Text(text = profile.email,
                    color = MaterialTheme.colorScheme.onSurface)

            }

        }
    }
}
