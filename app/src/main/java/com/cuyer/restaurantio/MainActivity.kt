package com.cuyer.restaurantio

import android.os.Bundle
import android.widget.EditText
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.cuyer.restaurantio.ui.theme.RestaurantioTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Credentials("Name", "Enter your name")
        }
    }
}



@Composable
fun Credentials(header: String, hint: String) {
    var text by remember { mutableStateOf(TextFieldValue("")) }

    ConstraintLayout {
        val(headerText, TextField) = createRefs()

        Text(
            modifier = Modifier.constrainAs(headerText)
            {
                top.linkTo(parent.top)
                bottom.linkTo(TextField.top)
                start.linkTo(parent.start)


            },
            text = header)

        OutlinedTextField(
            value = text,
            onValueChange = { newText -> text = newText},
            placeholder = { Text(text = hint)},
            modifier = Modifier.constrainAs(TextField)
            {
                top.linkTo(headerText.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            },
            shape = RoundedCornerShape(10.dp)
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