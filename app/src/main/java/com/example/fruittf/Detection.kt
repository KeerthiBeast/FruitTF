package com.example.fruittf

import android.util.Log
import androidx.activity.ComponentActivity
import androidx.camera.view.CameraController
import androidx.camera.view.LifecycleCameraController
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.navigation.NavHostController
import com.example.fruittf.datapackage.TFLiteFruitClassifier
import com.example.fruittf.domain.Classification
import com.example.fruittf.presentation.CameraPreview
import com.example.fruittf.presentation.FruitImageAnalyzer

@Composable
fun Detect(applicationContext: ComponentActivity, navController: NavHostController) {
    var classifications by remember {
        mutableStateOf(emptyList<Classification>())
    }
    val analyzer = remember {
        FruitImageAnalyzer(
            classifier = TFLiteFruitClassifier(
                context = applicationContext
            ),
            onResults = {
                classifications = it
            }
        )
    }
    val controller = remember {
        LifecycleCameraController(applicationContext).apply {
            setEnabledUseCases(CameraController.IMAGE_ANALYSIS)
            setImageAnalysisAnalyzer(
                ContextCompat.getMainExecutor(applicationContext),
                analyzer
            )
        }
    }
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            CameraPreview(controller, Modifier.fillMaxSize())

            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                classifications.forEach {
                    Log.d("Classification Name", "$it.name")
                    Text(
                        text = it.name,
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(MaterialTheme.colorScheme.primaryContainer)
                            .padding(8.dp),
                        textAlign = TextAlign.Center,
                        fontSize = 20.sp,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Button(
                        onClick = {
                            navController.navigate(Fruit(it.name))
                        },
                    ) {
                        Text("Click for More Information")
                    }
                }
            }
        }
    }
}