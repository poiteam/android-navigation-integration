package com.poilabs.android_navigation_integration

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.os.postDelayed
import com.poilabs.navigation.model.PoiNavigation
import com.poilabs.navigation.model.PoiSdkConfig
import com.poilabs.navigation.view.fragments.MapFragment
import com.poilabs.poilabspositioning.model.PLPStatus
import java.util.*

class MainActivity : AppCompatActivity(), PoiNavigation.OnNavigationReady {
    private val REQUEST_FINE_LOCATION = 56
    private val REQUEST_COARSE_LOCATION = 57

    companion object {
        const val TAG = "MainActivity"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        askLocalPermission()
    }

    private fun askLocalPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val hasFineLocation: Int =
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
            if (hasFineLocation != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                    REQUEST_FINE_LOCATION
                )
            } else {
                startPoiSDK()
            }
        } else {
            val hasLocalPermission: Int =
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
            if (hasLocalPermission != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION),
                    REQUEST_COARSE_LOCATION
                )
            } else {
                startPoiSDK()
            }
        }
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        Log.i(TAG, "onRequestPermissionResult")

        if (requestCode == REQUEST_FINE_LOCATION) {
            if (PackageManager.PERMISSION_GRANTED == grantResults[0]) { // Permission Granted
                startPoiSDK()
                Log.i(TAG, "Foreground or background location enabled.")
            } else {
                Log.e(TAG, " Permission was denied, but is needed for core functionality.")
            }
        } else if (requestCode == REQUEST_COARSE_LOCATION) {
            if (PackageManager.PERMISSION_GRANTED == grantResults[0]) { // Permission Granted
                startPoiSDK()
                Log.i(TAG, "Location permission was granted")
            } else {
                Log.e(TAG, " Permission was denied, but is needed for core functionality.")
            }
        }
    }

    private fun startPoiSDK() {
        PoiNavigation.getInstance().clearResources()
        var localeLanguage = "tr"
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            localeLanguage = Locale.forLanguageTag(Locale.getDefault().language).toString()
        }
        val poiSdkConfig = PoiSdkConfig(
            BuildConfig.APPID,
            BuildConfig.APPSECRET,
            getUniqueId()
        )
        PoiNavigation.getInstance(
            this@MainActivity,
            localeLanguage,
            poiSdkConfig
        ).bind(this)
    }

    private fun getUniqueId(): String {
        return "this is a unique id"
    }

    override fun onReady(p0: MapFragment) {
        supportFragmentManager.beginTransaction().replace(R.id.container, p0).commitAllowingStateLoss()
    }

    override fun onStoresReady() {
        runOnUiThread {
            PoiNavigation.getInstance().navigateToStore("")
            PoiNavigation.getInstance().showPointsOnMap(listOf<String>())
        }
    }

    override fun onError(p0: Throwable?) {
        p0?.printStackTrace()

    }

    override fun onStatusChanged(p0: PLPStatus?) {
        Toast.makeText(this, p0?.toString(), Toast.LENGTH_SHORT).show()
    }

}