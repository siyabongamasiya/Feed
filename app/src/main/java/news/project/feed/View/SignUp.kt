package news.project.feed.View

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import news.project.feed.ui.theme.FeedTheme


@Composable
fun DrawSignUpScreen(mainNavHostController: NavHostController){
    FeedTheme {
        Scaffold(topBar = {
            topSection(mainNavHostController,"Sign Up",true)
        }) {paddingValues ->
            midSectionSignUp(mainNavHostController,paddingValues)
        }
    }
}

@Composable
fun midSectionSignUp(navController: NavHostController,paddingValues: PaddingValues) {
    var email = rememberSaveable {
        mutableStateOf("")
    }

    var password = rememberSaveable {
        mutableStateOf("")
    }

    var passwordVisible by rememberSaveable {
        mutableStateOf(false)
    }

    ConstraintLayout (modifier = Modifier
        .fillMaxSize()
        .padding(10.dp)){
        val (signupref) = createRefs()

        Column(modifier = Modifier
            .fillMaxWidth()
            .constrainAs(signupref) {
                centerTo(parent)
            },
            verticalArrangement = Arrangement.spacedBy(30.dp),
            horizontalAlignment = Alignment.CenterHorizontally) {

            OutlinedTextField(
                modifier = Modifier.clip(RoundedCornerShape(16)),
                colors = TextFieldDefaults.colors(
                    focusedTextColor = Color.Black,
                    cursorColor = Color.Black,
                    focusedIndicatorColor = Color.Black,
                    unfocusedTextColor = Color.Black,
                    unfocusedIndicatorColor = Color.Black
                ),
                value = email.value,
                onValueChange = {newvalue ->
                    email.value = newvalue
                },
                placeholder = {
                    Text(text = "Email")
                })
            OutlinedTextField(
                modifier = Modifier.clip(RoundedCornerShape(16)),
                colors = TextFieldDefaults.colors(
                    focusedTextColor = Color.Black,
                    cursorColor = Color.Black,
                    focusedIndicatorColor = Color.Black,
                    unfocusedTextColor = Color.Black,
                    unfocusedIndicatorColor = Color.Black
                ),
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    if (!passwordVisible) {
                        Icon(
                            modifier = Modifier.clickable {
                                passwordVisible = !passwordVisible
                            },
                            imageVector = Icons.Default.VisibilityOff,
                            contentDescription = "visibility off"
                        )
                    }else{
                        Icon(
                            modifier = Modifier.clickable {
                                passwordVisible = !passwordVisible
                            },
                            imageVector = Icons.Default.Visibility,
                            contentDescription = "visibility on"
                        )
                    }
                },
                value = password.value,
                onValueChange = {newvalue ->
                    password.value = newvalue
                },
                placeholder = {
                    Text(text = "Password")
                })
            Button1(modifier = Modifier.fillMaxWidth(),
                hasicon = false,
                text = "Sign Up",
                painter = null) {



            }

        }
    }
}

@Composable
fun topSection(navController: NavHostController,title : String?,Back : Boolean) {
    ConstraintLayout(modifier = Modifier
        .fillMaxWidth()
        .background(MaterialTheme.colorScheme.surface)
        .padding(10.dp)) {
        val (iconref,titleref) = createRefs()

        if(Back) {
            Icon(
                modifier = Modifier.constrainAs(iconref) {
                    start.linkTo(parent.start)
                }.clickable {
                    navController.navigateUp()
                },
                imageVector = Icons.Default.ArrowBackIosNew,
                tint = MaterialTheme.colorScheme.onSurface,
                contentDescription = "back arrow"
            )
        }

        if (title != null){
            Text(modifier = Modifier.constrainAs(titleref){
                centerTo(parent)
            }, text = title,
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.titleLarge)
        }
    }
}
