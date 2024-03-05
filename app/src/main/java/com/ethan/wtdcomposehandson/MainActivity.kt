package com.ethan.wtdcomposehandson

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Column {
                WtdScreen()
            }
        }
    }
}

@Composable
fun WtdScreen() {
    val defaultList = (1..10).toList()
    var numbers by remember { mutableStateOf(defaultList) }

    val onAddClicked = {
        numbers = numbers.toMutableList().apply {
            add(Random.nextInt())
        }
    }
    val onRemoveClicked = {
        numbers = numbers.toMutableList().apply {
            if (isNotEmpty()) {
                removeLast()
            }
        }
    }
    val onResetClicked = {
        numbers = defaultList
    }

    Column {
        TopTitleBar()
        ActionsRow(
            onAddClicked,
            onRemoveClicked,
            onResetClicked
        )
        ItemList(numbers)
    }
}

@Composable
fun TopTitleBar() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .height(58.dp)
            .fillMaxWidth()
            .background(Color(0xFF997EE8))
    ) {
        Text(
            text = "Wanted Challenge",
            fontSize = 28.sp
        )
    }

}

@Composable
fun ActionsRow(
    onAddClicked: () -> Unit,
    onRemoveClicked: () -> Unit,
    onResetClicked: () -> Unit
) {
    Row {
        ActionButton(text = "추가", Color(0xFFD9D9D9), onAddClicked)
        ActionButton(text = "삭제", Color(0xFF944B4B), onRemoveClicked)
        ActionButton(text = "초기화", Color(0xFF84D59F), onResetClicked)
    }
}

@Composable
fun ActionButton(text: String, backgroundColor: Color, onClicked: () -> Unit) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .size(100.dp)
            .background(backgroundColor)
            .clickable { onClicked() }
    ) {
        Text(text = text, fontSize = 24.sp)
    }
}

@Composable
fun ItemList(numbers: List<Int>) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(numbers) { NumberItem(it) }
    }
}

@Composable
fun NumberItem(number: Int) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
    ) {
        Text(
            text = "Item #$number",
            fontSize = 28.sp
        )
    }
}
