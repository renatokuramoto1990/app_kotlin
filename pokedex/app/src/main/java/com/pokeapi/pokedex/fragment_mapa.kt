package com.pokeapi.pokedex

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class MapaFragment : Fragment(), OnMapReadyCallback {
    private var map: GoogleMap? = null

    @SuppressLint("MissingPermission")
    override fun onMapReady(googleMap: GoogleMap?) {
        this.map = googleMap

        val ok = PermissionUtils.validate(activity!!, 1,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION)
        if (ok) map?.isMyLocationEnabled = true

        val location =LatLng(-23.550227, -46.633911)

        val update = CameraUpdateFactory.newLatLngZoom(location, 18f)
        map?.moveCamera(update)

        map?.addMarker(MarkerOptions()
            .title("FIT")
            .snippet("Faculdade Impacta de Tecnologia")
            .position(location)
        )
        map?.mapType=GoogleMap.MAP_TYPE_NORMAL
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.fragment_mapa, container, false)
        val mapFragment = childFragmentManager.findFragmentById(R.id.mapFragment) as SupportMapFragment
        mapFragment.getMapAsync(this)
        return view
    }

    @SuppressLint("MissingPermission")
    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<out String>,
                                            grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        for (result in grantResults) {
            if (result == PackageManager.PERMISSION_GRANTED) {
                map?.isMyLocationEnabled = true
                return
            }
        }
    }

}

