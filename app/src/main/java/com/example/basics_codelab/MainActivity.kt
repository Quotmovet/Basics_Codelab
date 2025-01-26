package com.example.basics_codelab

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Icon
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.basics_codelab.root.ui.BasicsCodelabTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BasicsCodelabTheme {
                MySootheAppPortrait()
            }
        }
    }
}

@Composable
fun HomeScreen(modifier: Modifier = Modifier){
    Column(modifier) {
        Spacer(Modifier.height(16.dp))
        SearchBar(Modifier.padding(16.dp))
        HomeSection(
            title = R.string.hs1_align_your_body,
            modifier = Modifier.padding(top = 16.dp)) {
            BodyElementRow()
        }
        Spacer(Modifier.height(16.dp))
        HomeSection(
            title = R.string.hs2_favorite,
            modifier = Modifier.padding(top = 16.dp)) {
            CollectionCardRow()
        }
        Spacer(Modifier.height(16.dp))
    }
}

@Composable
fun HomeSection(
    @StringRes title: Int,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
){
    Column(modifier = modifier) {
        Text(
            text = stringResource(title),
            style = MaterialTheme.typography.titleMedium,
            modifier = modifier
                .paddingFromBaseline(top = 40.dp, bottom = 16.dp)
                .padding(horizontal = 16.dp)
        )
        content()
    }
}

@Composable
private fun SearchBar(modifier: Modifier = Modifier){
    TextField(
        value = "",
        onValueChange = {},
        placeholder = { Text(stringResource(R.string.ab1_search)) },
        maxLines = 1,
        singleLine = true,
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null,
            )
        },
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = MaterialTheme.colorScheme.surface,
            focusedContainerColor = MaterialTheme.colorScheme.surface
        ),
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp)
    )
}

@Composable
fun BodyElementRow(modifier: Modifier = Modifier){

    val bodyData: List<BodyElementData> = List(10){
        BodyElementData(R.drawable.ic_launcher_background, R.string.ab1_inversions)
    }

    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
        modifier = modifier) {
        items(items = bodyData) { item ->
            BodyElement(bodyElementData = item)
        }
    }
}

@Composable
fun BodyElement(
    bodyElementData: BodyElementData,
    modifier: Modifier = Modifier){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .padding(8.dp)
            .background(MaterialTheme.colorScheme.surface)) {
        Image(
            painter = painterResource(bodyElementData.drawable),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(88.dp)
                .clip(CircleShape)
        )
        Text(
            text = stringResource(bodyElementData.text),
            modifier = Modifier.paddingFromBaseline(top = 24.dp, bottom = 8.dp),
            style = MaterialTheme.typography.bodyMedium

        )
    }
}

@Composable
fun CollectionCard(
    bodyElementData: BodyElementData,
    modifier: Modifier = Modifier){
    Surface (
        shape = MaterialTheme.shapes.medium,
        modifier = modifier
    ) {
        Row(
            modifier = modifier
                .width(255.dp)
                .height(80.dp)
                .clip(MaterialTheme.shapes.medium)
                .background(MaterialTheme.colorScheme.surface),
            verticalAlignment = Alignment.CenterVertically
        ){
            Image(
                painter = painterResource(bodyElementData.drawable),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(80.dp)
            )
            Text(
                text = stringResource(bodyElementData.text),
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface,
                fontSize = 12.sp,
                fontFamily = MaterialTheme.typography.bodySmall.fontFamily,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
            )
        }
    }
}

@Composable
fun CollectionCardRow(modifier: Modifier = Modifier){

    val bodyData: List<BodyElementData> = List(14){
        BodyElementData(R.drawable.ic_launcher_background, R.string.fc2_nature_meditations)
    }

    LazyHorizontalGrid(
        rows = GridCells.Fixed(2),
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier.height(168.dp)
    ) {
        items(items = bodyData) { item ->
            CollectionCard(bodyElementData = item)
        }
    }
}

@Composable
fun SootheBottomNavigation(modifier: Modifier = Modifier){

    NavigationBar(
        containerColor = MaterialTheme.colorScheme.surfaceVariant,
        modifier = modifier) {

        NavigationBarItem(
            icon = { Icon(Icons.Filled.Home, contentDescription = null) },
            label = { Text(stringResource(R.string.ab1_inversions)) },
            selected = true,
            onClick = {}
        )

        NavigationBarItem(
            icon = { Icon(Icons.Filled.Search, contentDescription = null) },
            label = { Text(stringResource(R.string.ab1_search)) },
            selected = true,
            onClick = {}
        )
    }
}

@Composable
fun MySootheAppPortrait() {
    BasicsCodelabTheme {
        Scaffold(
            bottomBar = { SootheBottomNavigation() }
        ) { padding ->
            HomeScreen(Modifier.padding(padding))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BodyElementPreview(){
    BasicsCodelabTheme {
        BodyElementRow()
    }
}

@Preview(showBackground = true)
@Composable
fun CollectionCardRowPreview(){
    BasicsCodelabTheme {
        CollectionCardRow()
    }
}
