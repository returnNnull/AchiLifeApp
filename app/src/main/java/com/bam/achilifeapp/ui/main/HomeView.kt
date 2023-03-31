package com.bam.achilifeapp.ui.main

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.bam.achilifeapp.R
import com.bam.achilifeapp.data.app.firebase.Award
import com.bam.achilifeapp.data.app.firebase.Categories


@Composable
fun HomeScreen(navController: NavController?, viewModel: IHomeScreenViewModel) {
    Column {
        ProfileView(profileUiState = viewModel.profileUiState())
        AwardProgressView(list = viewModel.awardsProgressUiState())
        CategoryProgressView(list = viewModel.categoriesProgressUiState())
    }
}

@Composable
fun CategoryProgressView(list: ListUiState<Categories>) {
    Column {
        SectionHeader(text = "Категории")
        Spacer(modifier = Modifier.height(8.dp))
        LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            items(list.items) {
                CategoriesProgressItem(it)
            }
        }
    }
}

@Composable
private fun CategoriesProgressItem(it: Categories) {
    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = it.name)
            Text(text = "${it.value} %")
        }
        LinearProgressIndicator(
            modifier = Modifier.fillMaxWidth(),
            progress = (it.value / 100).toFloat()
        )
    }
}

@Composable
fun AwardProgressView(list: ListUiState<Award>) {

    Column(Modifier.padding(8.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            SectionHeader(
                text = "В процессе"
            )
        }
        Spacer(modifier = Modifier.height(5.dp))
        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 96.dp)
        ) {
            items(list.items) {
                AwardsProgressGridCell(it)
            }
        }
    }
}

@Composable
private fun SectionHeader(text: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = text, fontSize = MaterialTheme.typography.titleMedium.fontSize)
        Icon(
            modifier = Modifier
                .height(24.dp)
                .width(24.dp),
            imageVector = Icons.Default.List,
            contentDescription = "image"
        )
    }

}

@Composable
private fun AwardsProgressGridCell(it: Award) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        AsyncImage(
            modifier = Modifier.clip(RoundedCornerShape(15.dp)),
            model = it.img,
            contentDescription = "award",
            placeholder = painterResource(
                id = R.drawable.ic_baseline_attractions_24
            )
        )
        Text(text = it.name, fontSize = MaterialTheme.typography.labelSmall.fontSize)
        Text(
            text = "${it.value / it.maxValue * 100} %",
            fontSize = MaterialTheme.typography.labelSmall.fontSize
        )
    }
}




@Composable
fun ProfileView(profileUiState: ProfileUiState) {
    Row(
        Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {

        val state by profileUiState.collect().collectAsState()

        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                modifier = Modifier
                    .widthIn(200.dp, 300.dp)
                    .heightIn(200.dp, 300.dp)
                    .clip(
                        RoundedCornerShape(15)
                    ),
                model = state.img,
                contentDescription = "avatar",
                placeholder = painterResource(
                    id = R.drawable.ic_baseline_account_box_24
                ),
                contentScale = ContentScale.Crop,

                )
            Text(text = state.name)
        }

        Column(Modifier.padding(8.dp)) {
            Text(text = "Любимая категория", fontWeight = FontWeight.Bold)
            Text(text = state.bestCategory)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Мотивация", fontWeight = FontWeight.Bold)
            Text(text = state.motivation)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Лучшие достижения", fontWeight = FontWeight.Bold)
            LazyRow(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                items(profileUiState.bestAwards) {
                    AsyncImage(
                        model = it.img,
                        contentDescription = "award",
                        placeholder = painterResource(id = R.drawable.ic_baseline_attractions_24)
                    )
                }
            }
        }
    }
}




@Preview(showBackground = true)
@Composable
fun Profile_Preview() {
    ProfileView(profileUiState = ProfileUiState())
}

@Preview(showBackground = true)
@Composable
fun AwardsProgress_Preview() {
    AwardProgressView(list = ListUiState(List(10) { Award() }))
}

@Preview(showBackground = true)
@Composable
fun CategoriesProgress_Preview() {
    CategoryProgressView(list = ListUiState(List(7) { Categories() }))
}




