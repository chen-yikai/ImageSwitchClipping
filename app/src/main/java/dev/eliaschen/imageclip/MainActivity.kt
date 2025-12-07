package dev.eliaschen.imageclip

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.CompositingStrategy
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.eliaschen.imageclip.ui.theme.ImageClipTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Main()
        }
    }
}

@Composable
fun Main() {
    var clipLevel by remember { mutableStateOf(0f) }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(modifier = Modifier.size(700.dp, 500.dp)) {
            Box {
                Image(
                    painter = painterResource(R.drawable.first),
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                )
                Text(
                    "first.jpeg",
                    modifier = Modifier
                        .padding(10.dp)
                        .clip(CircleShape)
                        .background(Color.White.copy(alpha = 0.8f))
                        .padding(5.dp)
                        .align(Alignment.TopEnd),
                    fontSize = 30.sp
                )
            }
            Box(
                modifier = Modifier
                    .graphicsLayer(compositingStrategy = CompositingStrategy.Offscreen)
                    .drawWithContent {
                        drawContent()
                        drawRect(
                            Color.Black,
                            topLeft = Offset((size.width * clipLevel), 0f),
                            size = size,
                            blendMode = BlendMode.Clear
                        )
                    }
            ) {
                Image(
                    painter = painterResource(R.drawable.second),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                )
                Text(
                    "second.jpeg",
                    modifier = Modifier
                        .padding(10.dp)
                        .clip(CircleShape)
                        .background(Color.White.copy(alpha = 0.5f))
                        .padding(5.dp)
                        .align(Alignment.TopStart),
                    fontSize = 30.sp
                )
            }
        }
        Spacer(Modifier.height(30.dp))
        Slider(
            clipLevel,
            onValueChange = { clipLevel = it },
            valueRange = 0f..1f,
            modifier = Modifier
                .width(700.dp)
        )
    }
}