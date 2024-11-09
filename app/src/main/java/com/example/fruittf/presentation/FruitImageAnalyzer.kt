package com.example.fruittf.presentation

import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import com.example.fruittf.domain.Classification
import com.example.fruittf.domain.FruitClassifier

class FruitImageAnalyzer(
    private val classifier: FruitClassifier,
    private val onResults: (List<Classification>) -> Unit
): ImageAnalysis.Analyzer {
   private var frameSkipCounter = 0

    override fun analyze(image: ImageProxy) {
        if(frameSkipCounter % 60 == 0) {
            val rotationDegrees = image.imageInfo.rotationDegrees
            val bitmap = image
                .toBitmap()
                .centerCrop(160, 160)
            val results = classifier.classify(bitmap, rotationDegrees)
            onResults(results)
        }
        frameSkipCounter++
        image.close()
    }
}