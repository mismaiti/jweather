package com.jweatherinfo.core.util

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.location.Location
import android.os.Looper
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.jweatherinfo.MainActivity
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import java.util.concurrent.TimeUnit

object LocationUtil {

    @SuppressLint("MissingPermission")
    suspend fun FusedLocationProviderClient.locationFlow(): Flow<Result<Location>> = callbackFlow {
        val locationRequest = LocationRequest.create().apply {
            interval = TimeUnit.SECONDS.toMillis(2)
            fastestInterval = TimeUnit.SECONDS.toMillis(1)
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        }
        val callBack = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult) {
                super.onLocationResult(locationResult)
                val location = locationResult.lastLocation
                if (location != null) {
                    trySend(Result.success(location))
                    channel.close()
                } else {
                    trySend(Result.failure(Throwable("Last location null")))
                    channel.close()
                }
                removeLocationUpdates(this)

            }
        }
        requestLocationUpdates(
            locationRequest,
            callBack,
            Looper.getMainLooper()
        ).addOnFailureListener { e ->
            close(e)
        }
        awaitClose {
            removeLocationUpdates(callBack)
        }
    }

    fun permissionRequest(
        context: Context,
        activity: Activity,
        permission: String,
        permissionAction: (Boolean) -> Unit
    ) {

        val permissionGranted =
            Common.checkIfPermissionGranted(
                context,
                permission
            )

        if (permissionGranted) {
            permissionAction(true)
            return
        }


        val launcher = (activity as MainActivity).registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
                // Permission Accepted
                permissionAction(true)
            } else {
                // Permission Denied
                permissionAction(false)
            }
        }

        launcher.launch(permission)

    }
}