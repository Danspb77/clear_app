package com.diplom.clear_app.util

import android.Manifest
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.shouldShowRationale

@OptIn(ExperimentalPermissionsApi::class)
object CameraPermissionHelper {
    fun hasPermission(permissionState: PermissionState): Boolean {
        return permissionState.status.isGranted
    }

    fun shouldShowRationale(permissionState: PermissionState): Boolean {
        return permissionState.status.shouldShowRationale
    }

    fun requestPermission(permissionState: PermissionState) {
        permissionState.launchPermissionRequest()
    }
}
