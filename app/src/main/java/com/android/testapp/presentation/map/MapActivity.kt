package com.android.testapp.presentation.map

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.android.testapp.R
import com.android.testapp.base.utils.extensions.toPx
import com.android.testapp.domain.model.CornModel
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.PolygonOptions
import org.koin.androidx.viewmodel.ext.android.viewModel


class MapActivity : AppCompatActivity(R.layout.activity_map), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    private val cornModel: CornModel? by lazy {
        intent.extras?.getParcelable<CornModel>(KEY_CORN_MODEL)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        val x1 = cornModel?.minX ?: 0.0
        val y1 = cornModel?.minY ?: 0.0
        val x2 = cornModel?.maxX ?: 0.0
        val y2 = cornModel?.maxY ?: 0.0

        mMap.uiSettings.isZoomControlsEnabled = true
        mMap.uiSettings.isCompassEnabled = true

        val bounds = LatLngBounds.Builder().apply {
            include(LatLng(x1, y1))
            include(LatLng(x2, y2))
        }.build()

        val width = resources.displayMetrics.widthPixels
        val height = resources.displayMetrics.heightPixels

        val polygon = PolygonOptions().apply {
            add(LatLng(x1, y1))
            add(LatLng(x2, y1))
            add(LatLng(x2, y2))
            add(LatLng(x1, y2))
            fillColor(Color.YELLOW)
        }

        mMap.addPolygon(polygon)
        mMap.setLatLngBoundsForCameraTarget(bounds)

        mMap.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds, width, height, 30.toPx))
    }

    companion object {
        private const val KEY_CORN_MODEL = "key_corn_model"

        fun newInstance(context: Context, cornModel: CornModel) : Intent {
            val intent = Intent(context, MapActivity::class.java)
            intent.putExtra(KEY_CORN_MODEL, cornModel)
            return intent
        }
    }
}
