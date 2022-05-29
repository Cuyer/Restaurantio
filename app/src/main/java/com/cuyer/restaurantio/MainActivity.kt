package com.cuyer.restaurantio

import android.os.Bundle
import android.widget.EditText
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.cuyer.restaurantio.ui.theme.RestaurantioTheme
import com.cuyer.restaurantio.viewmodels.MainActivityViewModel

class MainActivity : ComponentActivity() {

    private val viewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().apply {
            setKeepOnScreenCondition {
                viewModel.isLoading.value
            }
        }
        setContent {
            RestaurantioTheme {
                Surface(Modifier.fillMaxSize()) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()) {
                        Credentials(header = "Name", hint = "Name")
                }
               }
            }
        }
    }
}


@Composable
fun StandardButton(buttonText: String) {
    Button(onClick = {
        /*TODO*/
    },
    modifier = Modifier
        .fillMaxWidth()
        .padding(20.dp, 0.dp, 20.dp, 0.dp),
        shape = RoundedCornerShape(10.dp)) {
        Text(text = buttonText)
    }
}

@Composable
fun Credentials(header: String, hint: String) {
    var text by remember { mutableStateOf(TextFieldValue("")) }

    ConstraintLayout(Modifier.fillMaxWidth()) {
        val(headerText, TextField) = createRefs()

        Text(
            modifier = Modifier
                .constrainAs(headerText)
                {
                    bottom.linkTo(TextField.top)
                    start.linkTo(parent.start)

                }
                .fillMaxWidth()
                .padding(25.dp, 0.dp, 20.dp, 5.dp),
            text = header)

        OutlinedTextField(
            value = text,
            onValueChange = { newText -> text = newText},
            placeholder = { Text(text = hint)},
            modifier = Modifier
                .constrainAs(TextField)
                {
                    top.linkTo(headerText.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .fillMaxWidth()
                .padding(20.dp, 0.dp, 20.dp, 0.dp),
            shape = RoundedCornerShape(10.dp),
        )

    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    RestaurantioTheme {
        Credentials("Name", "Enter your name")
    }
}