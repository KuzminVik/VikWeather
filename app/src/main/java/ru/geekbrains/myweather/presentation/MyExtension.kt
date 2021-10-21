package ru.geekbrains.myweather.presentation

import android.Manifest
import android.app.Activity
import android.content.DialogInterface
import android.content.pm.PackageManager
import android.location.Location
import android.net.Uri
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

const val THEME_UNDEFINED = -1
const val THEME_LIGHT = 0
const val THEME_DARK = 1

fun Fragment.arguments(vararg arguments: Pair<String, Any>): Fragment {
    this.arguments = bundleOf(*arguments)
    return this
}

fun ImageView.setDrawableFromUri(uri: Uri){
    Glide.with(context)
        .load(uri)
        .into(this)
}

fun View.click(click: () -> Unit) = setOnClickListener { click() }

fun Activity.getMyLocation(requestCode: Int): Pair<Double, Double>{
    val fusedLocationClient: FusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
    checkAndRequestPermission(Manifest.permission.ACCESS_FINE_LOCATION, requestCode)
    var pair: Pair<Double, Double> = Pair(0.0, 0.0)
    fusedLocationClient.lastLocation
        .addOnSuccessListener { location: Location ->
            pair = Pair(location.latitude, location.longitude)
        }
    return pair
}

fun Activity.checkAndRequestPermission(
    manifestPermission: String, requestCode: Int
) {
    val permissionStatus = ContextCompat.checkSelfPermission(applicationContext, manifestPermission)
    if (permissionStatus == PackageManager.PERMISSION_DENIED) {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, manifestPermission)) {
            AlertDialog.Builder(this)
                .setTitle("Разрешения")
                .setMessage("Чтобы автоматически определять погоду для вашего местоположения, требуется разрешение для использования геолокации")
                .setPositiveButton("Разрешить",
                    DialogInterface.OnClickListener { dialogInterface, i ->
                        requestPermission(manifestPermission, requestCode)
                    })
                .setNegativeButton("Не разрешать",null)
                .show()
        } else {
            requestPermission(manifestPermission, requestCode)
        }
    } else {

    }
}

fun Activity.requestPermission(manifestPermission: String, requestCode: Int) {
    ActivityCompat.requestPermissions(this, arrayOf(manifestPermission), requestCode)
}
