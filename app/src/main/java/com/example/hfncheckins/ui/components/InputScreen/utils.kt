package com.example.hfncheckins.ui.components.InputScreen

import com.google.mlkit.vision.codescanner.GmsBarcodeScanner

fun handleInputValueChange(onValidStart: (String) -> Unit): (String) -> Unit =
    {
        val isValid = validateInputValue(String)
        onValidStart(it)
    }

fun handleClickScan(
    scanner: GmsBarcodeScanner,
    onValidScan: (String) -> Unit
): () -> Unit =
    {
        scanner.startScan()
            .addOnSuccessListener { barcode ->
                handleScanSuccess(barcode.rawValue.toString(), barcode.format, onValidScan)
            }
            .addOnCanceledListener {}
            .addOnFailureListener { e ->
                // Task failed with an exception
            }
    }

private fun handleScanSuccess(
    barcodeValue: String,
    barcodeFormat: Int,
    onValidScan: (String) -> Unit
) {
    val isValid: Boolean = validateBarcode(barcodeValue)
    if (isValid) {
        onValidScan(barcodeValue)
    } else {
        handleInvalidScan(barcodeFormat)
    }
}

private fun handleInvalidScan(codeType: Int) {
    TODO("Not yet implemented")
}

private fun validateInputValue(inputValue: String.Companion): Boolean {
    TODO("Not yet implemented")
}

private fun validateBarcode(barcode: String): Boolean {
//    TODO("Not yet implemented")
    return true
}
