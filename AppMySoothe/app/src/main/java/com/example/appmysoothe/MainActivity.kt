package com.example.appmysoothe

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Spa
import androidx.compose.material3.*
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.appmysoothe.ui.theme.AppMySootheTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppMySootheTheme {
                val windowSize = calculateWindowSizeClass(activity = this)
                App(windowSize)
            }
        }
    }
}

@Composable
fun App(windowSize: WindowSizeClass) {

    /**
     *  In example below we have a situation where our app
     *  recognize when the window is Portrait or Landscape and show
     *  a specific layout for every case.
     *
     *  For this we need windowSize. We can get the window size with the
     *  calculateWindowSizeClass function by passing the activity reference in args
     *
     * **/
    when(windowSize.widthSizeClass) {
        WindowWidthSizeClass.Compact -> {
            AppPortrait()
        }
        WindowWidthSizeClass.Expanded,
        WindowWidthSizeClass.Medium -> {
            AppLandscape()
        }
    }
}

@Composable
fun AppPortrait() {
    Scaffold( bottomBar = { SootheBottomNavigation() } ) {
        HomeScreen()
    }
}

@Composable
fun AppLandscape() {
    Row {
       SootheNavigationRail()
       HomeScreen()
    }
}


@Composable
fun SootheBottomNavigation( modifier: Modifier = Modifier ){
    /**
     *   NavigationBar is a simple component from the Material package that
     *   allows us to work with navigation in our application
     **/
    NavigationBar(modifier = modifier, containerColor = MaterialTheme.colorScheme.surfaceVariant) {
        NavigationBarItem (
            icon = {
                Icon(imageVector = Icons.Default.Spa, contentDescription = null)
            },
            label = {
                Text(text = "Home")
            },
            selected = true,
            onClick = {}
        )
        NavigationBarItem (
            icon = {
                Icon(imageVector = Icons.Default.AccountCircle, contentDescription = null)
            },
            label = {
                Text(text = "Profile")
            },
            selected = true,
            onClick = {}
        )
    }
}

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {

    /**
     * For we set our component with a scroll, we have set the field
     * (vertical/horizontal)Scroll and put a ScrollState as arg
     * **/
    Column( modifier.verticalScroll(rememberScrollState()) ) {
        Spacer(modifier = modifier.height(49.dp))
        SearchBar(modifier.padding(horizontal = 16.dp))
        HomeSection(title = "Align Your Body") {
            AlignYourBodyRow()
        }
        HomeSection(title = "Favorite Collections") {
            FavoriteCollectionsGrid()
        }
        Spacer(Modifier.height(16.dp))
    }
}

@Composable
fun HomeSection(
    title:String,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
){
    Column(modifier) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge,
            modifier = modifier
                .paddingFromBaseline(top = 40.dp, bottom = 32.dp)
                .padding(horizontal = 16.dp)
        )
        content()
    }
}

/**
 *  The concept of Arrangement is basically the way to we works
 *  with Gap in our lists,grids e etc...
 *
 * */
@Composable
fun FavoriteCollectionsGrid( modifier: Modifier = Modifier ){
    LazyHorizontalGrid(
        rows = GridCells.Fixed(2),
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement =   Arrangement.spacedBy(16.dp),
        modifier = modifier.height(165.dp)) {
        items(favoriteCollectionsData) {
            item ->
            FavoriteCollectionCard(modifier.height(80.dp), item.imageRes, item.title)
        }
    }
}

@Composable
fun FavoriteCollectionCard( modifier: Modifier = Modifier, imageRes:Int, title:String ){
    Surface(
        shape = MaterialTheme.shapes.medium,
        color = MaterialTheme.colorScheme.surfaceVariant,
        modifier = modifier
    ) {
        Row(
            modifier.width(255.dp),
            verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = modifier.size(80.dp)
            )
            Text(text = title, style = MaterialTheme.typography.bodyMedium, modifier = Modifier.padding(horizontal = 16.dp))
        }
    }
}

@Composable
fun AlignYourBodyRow( modifier: Modifier = Modifier ) {
    LazyRow(modifier, horizontalArrangement = Arrangement.spacedBy(8.dp), contentPadding = PaddingValues(horizontal = 8.dp)) {
        items(alignYourBodyData) {
            item ->
            AlignYourBodyElement(modifier,item.imageRes, item.title)
        }
    }
}

@Composable
fun AlignYourBodyElement( modifier: Modifier = Modifier, imageRes:Int, title:String ) {
    Column(modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = modifier
                .clip(CircleShape)
                .size(88.dp)
        )
        Text(text = title,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.paddingFromBaseline(top = 24.dp, bottom = 8.dp),)
    }
}

@Composable
fun SearchBar(modifier: Modifier = Modifier) {
    TextField(
        value = "",
        onValueChange = {},
        placeholder = { Text(text = "Search") },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null
            )
        },
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = MaterialTheme.colorScheme.surface,
            focusedContainerColor = MaterialTheme.colorScheme.surface
        ),
        modifier = modifier
            .heightIn(min = 56.dp)
            .fillMaxWidth(),


    )
}

@Composable
private fun SootheNavigationRail(modifier: Modifier = Modifier) {
    NavigationRail(
        modifier = modifier.padding(start = 8.dp, end = 8.dp),
        containerColor = MaterialTheme.colorScheme.background,
    ) {
        Column(
            modifier = modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            NavigationRailItem(
                icon = {
                    Icon(
                        imageVector = Icons.Default.Spa,
                        contentDescription = null
                    )
                },
                label = {
                    Text("Home")
                },
                selected = true,
                onClick = {}
            )
            Spacer(modifier = Modifier.height(8.dp))
            NavigationRailItem(
                icon = {
                    Icon(
                        imageVector = Icons.Default.AccountCircle,
                        contentDescription = null
                    )
                },
                label = {
                    Text("Profile")
                },
                selected = false,
                onClick = {}
            )
        }
    }
}


data class AlignYourBody(val imageRes: Int, val title:String)
data class FavoriteCollectionCard(val imageRes: Int, val title:String)

val favoriteCollectionsData = listOf(
    FavoriteCollectionCard(R.drawable.fc1_short_mantras, "Short Mantras"),
    FavoriteCollectionCard(R.drawable.fc2_nature_meditations, "Nature Meditations"),
    FavoriteCollectionCard(R.drawable.fc3_stress_and_anxiety, "Stress and Anxiety"),
    FavoriteCollectionCard(R.drawable.fc4_self_massage, "Self Massage"),
    FavoriteCollectionCard(R.drawable.fc5_overwhelmed, "Overwhelmed"),
    FavoriteCollectionCard(R.drawable.fc6_nightly_wind_down, "Nightly Wind Down")
)

val alignYourBodyData = listOf(
    AlignYourBody(R.drawable.ab1_inversions, "Inversions"),
    AlignYourBody(R.drawable.ab2_quick_yoga, "Quick Yoga"),
    AlignYourBody(R.drawable.ab3_stretching, "Stretching"),
    AlignYourBody(R.drawable.ab4_tabata, "Tabata"),
    AlignYourBody(R.drawable.ab5_hiit, "Hiit"),
    AlignYourBody(R.drawable.ab6_pre_natal_yoga, "Pre Natal Yoga"),
)
