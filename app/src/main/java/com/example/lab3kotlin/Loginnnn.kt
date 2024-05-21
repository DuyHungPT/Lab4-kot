package com.example.lab3kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.app.unusedapprestrictions.IUnusedAppRestrictionsBackportCallback.Default
import com.example.lab3kotlin.ui.theme.LoginScreen

class Loginnnn : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent{
          LoginScreenn()
        }
    }
}
@Composable
fun LoginScreenn() {
     var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") } 
    val context = LocalContext.current 
    
    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        Image(painter = painterResource(id = R.drawable.img_2), 
            contentDescription = "Logo",
            modifier = Modifier
                .height(100.dp)
                .width(100.dp))
     Spacer(modifier = Modifier.height(16.dp))
        TextField(value = username, onValueChange = {username = it},
            label = { Text("Username")},
            modifier = Modifier.fillMaxWidth())
        
        Spacer(modifier = Modifier.height(8.dp))
        TextField(value = password, onValueChange = {password = it},
            label = { Text("Password")},
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions.Default.copy(
                autoCorrect = false
            ),
            modifier = Modifier.fillMaxWidth())

        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            if (username.isNotEmpty() && password.isNotEmpty()) {
                Toast.makeText(context,"Login successful", Toast.LENGTH_SHORT).show()

            }else{
                Toast.makeText(context,"Please enter usename and password", Toast.LENGTH_SHORT).show()
            }
        },
            modifier = Modifier.fillMaxWidth()

        ) {
           Text(text = "Login")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPrevieew(){
    LoginScreenn()
}