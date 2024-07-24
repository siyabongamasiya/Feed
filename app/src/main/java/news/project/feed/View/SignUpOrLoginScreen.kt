package news.project.feed.View

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.HorizontalDivider
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
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import news.project.feed.R
import news.project.feed.ui.theme.FeedTheme

@Composable
fun DrawSignUpOrLoginScreen(navController: NavHostController){
    FeedTheme {

        Scaffold {paddingValues ->  
            midSectionSignUpOrLogin(paddingValues,navController)
        }
    }
}

@Composable

fun midSectionSignUpOrLogin(paddingValues: PaddingValues,navController: NavController) {
    var email = rememberSaveable {
        mutableStateOf("")
    }
    
    var password = rememberSaveable {
        mutableStateOf("")
    }

    var passwordVisible by rememberSaveable {
        mutableStateOf(false)
    }

    ConstraintLayout(modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colorScheme.background)
        .padding(10.dp)) {
        val (signinref,signupref) = createRefs()

        Column(modifier = Modifier
            .fillMaxWidth()
            .constrainAs(signinref) {
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
                    email.value = newvalue},
                placeholder = {
                Text(text = "Email")
            })
            OutlinedTextField(
                modifier = Modifier
                    .clip(RoundedCornerShape(16)),
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
                text = "login",
                painter = null) {

                navController.navigate(mainScreens.home("")){
                    popUpTo(0)
                }
            }

        }

        Column (modifier = Modifier
            .fillMaxWidth()
            .constrainAs(signupref) {
                top.linkTo(
                    signinref.bottom,
                    margin = 20.dp
                )
            },
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally){

            Row(modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically) {

                HorizontalDivider(
                    modifier = Modifier.weight(0.4f),
                    color = Color.Black
                )
                Text(modifier = Modifier.padding(5.dp), text = "Or")
                HorizontalDivider(
                    modifier = Modifier.weight(0.4f),
                    color = Color.Black
                )
            }

            Button1(modifier = Modifier.fillMaxWidth(),
                hasicon = false,
                text = "Sign Up",
                painter = null) {
                navController.navigate(mainScreens.signup(""))
            }

            Button1(modifier = Modifier.fillMaxWidth(),
                hasicon = true,
                text = "Continue with google",
                painter = painterResource(id = R.drawable.google2)) {

            }

        }
    }
    
    Box(
        contentAlignment = Alignment.Center){
        

        

        
    }
}


@Composable
fun Button1(modifier: Modifier,hasicon : Boolean,
                 text : String,
                 painter : Painter?,
                 onclick : () -> Unit){
    val color : Color
    if (!hasicon){
        color = MaterialTheme.colorScheme.tertiary
    }else{
        color = MaterialTheme.colorScheme.surface
    }

    ElevatedButton(modifier=modifier,
        colors = ButtonDefaults.buttonColors(
            containerColor =color,
        ),
        onClick = { onclick.invoke() }) {

        if(hasicon){
            Row(modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center) {
                Row(verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    Icon(
                        modifier = Modifier.size(30.dp),
                        painter = painter!!,
                        contentDescription = "googleicon",
                        tint = Color.Unspecified
                    )
                    Text(text = text,
                        color = MaterialTheme.colorScheme.onSurface)
                }
            }
        }else{
            Row(modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center) {
                Text(text = text,
                    color = MaterialTheme.colorScheme.onSurface)
            }
        }

    }
}