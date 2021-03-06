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
import ru.geekbrains.myweather.R

fun Fragment.arguments(vararg arguments: Pair<String, Any>): Fragment {
    this.arguments = bundleOf(*arguments)
    return this
}

fun Fragment.showDialogWithInformation(msg: String, title: String){
    val builder = AlertDialog.Builder(requireContext())
    with(builder) {
        setTitle(title)
        setMessage(msg)
        setPositiveButton("OK", null)
        show()
    }
}

fun ImageView.setIconWithGlide(nameIcon: String){
    val uri: Uri = Uri.parse(context.resources.getString(R.string.path_icon, nameIcon))
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
                .setTitle("????????????????????")
                .setMessage("?????????? ?????????????????????????? ???????????????????? ???????????? ?????? ???????????? ????????????????????????????, ?????????????????? ???????????????????? ?????? ?????????????????????????? ????????????????????")
                .setPositiveButton("??????????????????",
                    DialogInterface.OnClickListener { _, _ ->
                        requestPermission(manifestPermission, requestCode)
                    })
                .setNegativeButton("???? ??????????????????",null)
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
