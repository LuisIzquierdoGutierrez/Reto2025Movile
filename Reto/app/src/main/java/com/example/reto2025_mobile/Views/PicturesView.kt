package com.example.reto2025_mobile.Views

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage



@Composable
fun PicturesView(navController: NavHostController) {
// Use remember with mutableStateListOf to track the list of URIs
    val selectedImageUris = remember { mutableStateListOf<Uri?>() }

// Launcher for picking a single photo
    val singlePhotoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri ->
            uri?.let { selectedImageUris.add(it) }
        }
    )

// Launcher for picking multiple photos
    val multiplePhotoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickMultipleVisualMedia(),
        onResult = { uris ->
            uris.forEach { uri ->
                uri?.let { selectedImageUris.add(it) }
            }
        }
    )

    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Button(onClick = {
                    singlePhotoPickerLauncher.launch(
                        PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                    )
                }) {
                    Text(text = "Pick one photo")
                }
                Button(onClick = {
                    multiplePhotoPickerLauncher.launch(
                        PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                    )
                }) {
                    Text(text = "Pick multiple photos")
                }
            }
        }

        items(selectedImageUris) { uri ->
            uri?.let {
                ImageWithButton(uri = it, onClick = {
                    selectedImageUris.remove(uri)
                })
            }
        }
    }
}


@Composable
fun ImageWithButton(uri: Uri, onClick: () -> Unit) {
    // Box to overlay Button on Image
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(600.dp) // Adjust the height as needed
    ) {
        // Image in the background
        AsyncImage(
            model = uri,
            contentDescription = null,
            modifier = Modifier.fillMaxWidth(),
            contentScale = ContentScale.Crop
        )

        // Button on top of the Image, positioned in the top end
        Button(
            onClick = onClick,
            modifier = Modifier
                .align(Alignment.TopEnd) // Align button at top end
                .padding(16.dp) // Optional padding for the button
        ) {
            Text(text = "Remove")
        }
    }
}
