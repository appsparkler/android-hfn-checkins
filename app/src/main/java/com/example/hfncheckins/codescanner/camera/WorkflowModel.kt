package com.example.codescanner.camera

import android.app.Application
import androidx.annotation.MainThread
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import java.util.HashSet
import com.google.mlkit.vision.barcode.common.Barcode


class WorkflowModel(application: Application) : AndroidViewModel(application) {
    enum class WorkflowState {
        NOT_STARTED,
        DETECTING,
        DETECTED,
        CONFIRMING,
        CONFIRMED,
        SEARCHING,
        SEARCHED
    }

    val detectedBarcode = MutableLiveData<Barcode>()
    val workflowState = MutableLiveData<WorkflowState>()
//    private var confirmedObject: DetectedObjectInfo? = null

    private val objectIdsToSearch = HashSet<Int>()

    var isCameraLive = false
        private set

    fun markCameraFrozen() {
        isCameraLive = false
    }

    fun markCameraLive() {
        isCameraLive = true
        objectIdsToSearch.clear()
    }

    @MainThread
    fun setWorkflowState(workflowState: WorkflowState) {
//        if (workflowState != WorkflowState.CONFIRMED &&
//            workflowState != WorkflowState.SEARCHING &&
//            workflowState != WorkflowState.SEARCHED
//        ) {
//            confirmedObject = null
//        }
        this.workflowState.value = workflowState
    }

}