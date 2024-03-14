package com.example.composetutorials

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.ExperimentalUnitApi
import com.example.composetutorials.ui.theme.ComposeTutorialsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeTutorialsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    Conversation(messages = SampleData.Talk)
                    //MessageCard(msg = Message("Lexi", "Hey, take a look at Jetpack Compose, it's great!"))
                }
            }
        }
    }
}

@Preview(
    uiMode = UI_MODE_NIGHT_YES,
    showBackground = true,
    name = "Dark Mode"
)
@Composable
fun previewMessageCard(){
    ComposeTutorialsTheme( darkTheme = true ) {
        Surface{
            MessageCard(msg = Message(R.drawable.gendo,"Gendo Ikari", "O maior inimigo da humanidade é o próprio homem"))
        }
    }
}

@Preview(showBackground = true )
@Composable
fun DefaultPreview() {
    ComposeTutorialsTheme( darkTheme = false ) {
        MessageCard(
            msg = Message(R.drawable.gendo,"Gendo Ikari", "O maior inimigo da humanidade é o próprio homem")
        )
    }
}


@Composable
fun Conversation(messages: List<Message>){
    LazyColumn {
        items(messages) { message ->
            MessageCard( message )
        }
    }
}

data class Message(val resId: Int, val author:String, val body:String, val importance:Int = 0, val receptor:Boolean = false)

@Composable
fun MessageCard(msg: Message) {
    Surface( modifier = Modifier.fillMaxWidth() ) {
        Row(
            modifier = Modifier.padding(all = Dp(8f)),
            horizontalArrangement = if(msg.receptor) Arrangement.Start else Arrangement.End
        ) {
            Image(
                painter = painterResource(id = msg.resId),
                contentDescription = "Image profile",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(Dp(60f))
                    .clip(CircleShape)
                    .align(Alignment.CenterVertically)
                    .border(Dp(2f), MaterialTheme.colorScheme.secondary, CircleShape)

            )
            Spacer(modifier = Modifier.width(Dp(8f)))

            var isExpanded by remember { mutableStateOf(false) }
            val surfaceColor by animateColorAsState(
                if(isExpanded) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface,
            )

            Column( Modifier.clickable { isExpanded = !isExpanded } ) {
                Text( text =  msg.author,
                    style = MaterialTheme.typography.titleMedium ,
                    color = MaterialTheme.colorScheme.secondary,
                    modifier = Modifier.padding(Dp(4f))
                )

                Spacer(modifier = Modifier.height(Dp(4f)))

                Surface(
                    shape = RoundedCornerShape(Dp(6f)),
                    shadowElevation = Dp(4f),
                    color = surfaceColor,
                    modifier = Modifier
                        .padding(Dp(4f))
                        .animateContentSize()
                ){
                    Text(
                        text = msg.body,
                        modifier = Modifier.width(Dp(168f)),
                        style = MaterialTheme.typography.bodySmall,
                        //maxLines = if(isExpanded) Int.MAX_VALUE else 1
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewConversation(){
    ComposeTutorialsTheme( darkTheme = false ) {
        Surface {
            Conversation( SampleData.Talk )
        }
    }
}
