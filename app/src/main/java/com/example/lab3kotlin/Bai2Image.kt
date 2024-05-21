package com.example.lab3kotlin

import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.BiasAlignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class Image : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
        ImageListScreene()
        }
    }
}

@Composable
fun ImageListScreene(){
    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ){
        Icon(
            imageVector = Icons.Default.Star,
            contentDescription = "Icon",
            modifier = Modifier.size(48.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        HorizontalImageList(images = listOf(
            R.drawable.img_1,
            R.drawable.img,
            R.drawable.img_2,
            R.drawable.img_4
        ))
          Spacer(modifier = Modifier.height(16.dp))
        VerticalImageList(images = listOf(
            R.drawable.img_3,
            R.drawable.img_1,
            R.drawable.img,
            R.drawable.img_2
        ))
    }
}
@Composable
fun HorizontalImageListt(images: List<Int>) {
    Row(
        modifier = Modifier
            .horizontalScroll(rememberScrollState())
    ) {
        images.forEach { imageRes ->
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = null,
                modifier = Modifier
                    .size(200.dp)
                    .padding(end = 8.dp)
                    .background(
                        color = Color.Gray,
                        shape = RoundedCornerShape(12.dp)
                    )
            )
        }
    }
}
@Composable
fun VerticalImageListt(images: List<Int>){
    Column (
        modifier = Modifier
            .verticalScroll(rememberScrollState())
    ){
        images.forEach {imageRes ->
            Image(painter = painterResource(id = imageRes),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(16/9f)
                    .padding(bottom = 8.dp)
                    .background(
                        color = Color.Cyan,
                        shape = RoundedCornerShape(12.dp)
                    ))
        }
    }


}

@Preview(showBackground = true)
@Composable
fun DefaulttPreview() {
    ImageListScreene()
}