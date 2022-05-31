package com.cuyer.restaurantio.presentation.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.OutlinedButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout

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
            placeholder = { Text(text = hint) },
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


@Composable
fun StandardButton(buttonText: String, modifier: Modifier = Modifier) {
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
fun ButtonWithImage(image: ImageVector, description: String, modifier: Modifier = Modifier) {
    OutlinedButton(onClick = {
    /*TODO*/
    },
        modifier = Modifier
          //  .fillMaxWidth()
            .padding(20.dp, 0.dp, 20.dp, 0.dp),

        shape = RoundedCornerShape(10.dp),

    ) {
        Image(imageVector = image, contentDescription = description )
    }

}
