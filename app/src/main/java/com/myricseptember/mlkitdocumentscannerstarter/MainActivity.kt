package com.myricseptember.mlkitdocumentscannerstarter

import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.myricseptember.mlkitdocumentscannerstarter.ui.theme.MlKitDocumentScannerStarterTheme

class MainActivity : ComponentActivity() {

    //TODO Step 1: Uncomment the variables in onCreate
//    private lateinit var numberOfPages: String
//    private var pageList: List<Uri> = emptyList()
//    private lateinit var documentName: String

    //TODO Step 2: Add the Activity Result Laucher variable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MlKitDocumentScannerStarterTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    //TODO Step 4: Receive scanning results

                    //TODO Step 5: Pass the images to the UI
                    DocumentScannerScreen(emptyList())
                }
            }
        }
    }

    //TODO Step 3: Add the Scanner options
//    private fun configureDucumentScannerOptions(): GmsDocumentScannerOptions {
//
//    }

    private fun onScanPDFButtonClick() {
        //TODO Step 6: Launch the document scanner
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun DocumentScannerScreen(imageUri: List<Uri>, modifier: Modifier = Modifier) {
        var hasStartedScanning by remember {
            mutableStateOf<Boolean>(true)
        }

        var validDocumentName by remember {
            mutableStateOf<Boolean>(false)
        }

        var validNumberOfPages by remember {
            mutableStateOf<Boolean>(false)
        }

        var numberOfPages by remember {
            mutableStateOf<String>("")
        }.also {
            //TODO Step 7: Uncomment numberOfPages variable
//            numberOfPages = it.value
        }

        var documentName by remember {
            mutableStateOf<String>("")
        }.also {
            //TODO Step 8: Uncomment documentName variable
//            documentName = it.value
        }

        Column(
            Modifier
                .padding(20.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally

        ) {

            Box(
                modifier = Modifier
                    .padding(2.dp)
                    .border(
                        width = 1.dp, color = Color.Blue, shape = RoundedCornerShape(8.dp)
                    )
                    .weight(1f)
                    .padding(2.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                        .padding(2.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    if (hasStartedScanning) {
                        Text(
                            textAlign = TextAlign.Center,
                            text = "1) Enter the number of pages you would like to scan."
                        )

                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            textAlign = TextAlign.Center,
                            text = "2) Once done press the 'Scan' button to scan your PDF document."
                        )
                    }

                    imageUri.forEach { uri ->
                        AsyncImage(
                            model = uri,
                            contentDescription = null,
                            contentScale = ContentScale.FillWidth,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))

            TextField(value = numberOfPages.trim(),
                label = { Text(text = "Enter number of pages") },
                isError = validNumberOfPages,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                supportingText = {
                    if (validNumberOfPages) {
                        Text(
                            text = "Page number should be < 0",
                            color = Color.Red
                        )
                    }
                },
                onValueChange = { numberOfPages = it })
            Spacer(modifier = Modifier.height(8.dp))

            TextField(value = documentName.trim(),
                label = { Text(text = "Enter document name") },
                isError = validDocumentName,
                supportingText = {
                    if (validDocumentName) {
                        Text(
                            text = "Please enter a valid document name",
                            color = Color.Red
                        )
                    }
                },
                onValueChange = { documentName = it })

            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = {
                //TODO Step 9: Uncomment documentName variable

                showToast("Scan Button clicked!")

//                if (numberOfPages.isEmpty() || numberOfPages.toInt() == 0 || documentName.isEmpty()) {
//                    validNumberOfPages = numberOfPages.isEmpty() || numberOfPages.toInt() == 0
//                    validDocumentName = documentName.isEmpty()
//                } else {
//                    validNumberOfPages = false
//                    validDocumentName = false
//                    hasStartedScanning = false
//                    onScanPDFButtonClick()
//                }
            }) {
                Text(text = "Scan Document")
            }
        }
    }
}