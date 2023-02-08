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

    companion object {
        const val TAG = "MainActivity"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        startPoiSDK()
    }

    private fun startPoiSDK() {
        PoiNavigation.getInstance().clearResources()
        val localeLanguage: String = Locale.forLanguageTag(Locale.getDefault().language).toString()
        val poiSdkConfig = PoiSdkConfig(
            appId = BuildConfig.APPID,
            secret = BuildConfig.APPSECRET,
            uniqueId = getUniqueId()
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
        Log.i(TAG, "onStoresReady: ")
        runOnUiThread {
            PoiNavigation.getInstance().navigateToStore("")
            PoiNavigation.getInstance().showPointsOnMap(listOf<String>())
        }
    }

    override fun onError(p0: Throwable?) {
        p0?.printStackTrace()

    }

    override fun onStatusChanged(p0: PLPStatus?) {
        Log.i(TAG, "onStatusChanged: $p0")
    }

    override fun onLocation(latitude: Double?, longitude: Double?, floor: Int?) {
        Log.i(TAG, "onLocation: $latitude,$longitude,$floor")
    }

}