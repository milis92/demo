package io.milis.sixt.ext

import androidx.annotation.DrawableRes
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

@DslMarker()
annotation class MarkerDsl

@MarkerDsl
private interface Builder<T> {
    fun build(): T
}

class MarkerBuilder : Builder<MarkerOptions> {

    private val markerOptions = MarkerOptions()

    fun position(init: LocationBuilder.() -> Unit) {
        markerOptions.position(location(init))
    }

    fun name(init: () -> String) {
        markerOptions.title(init.invoke())
    }

    fun snippet(init: () -> String) {
        markerOptions.snippet(init.invoke())
    }

    fun drawable(init: DrawableBuilder.() -> Unit) {
        markerOptions.icon(DrawableBuilder().apply(init).build())
    }

    override fun build(): MarkerOptions {
        return markerOptions
    }
}

class LocationBuilder : Builder<LatLng> {

    private var latitude: Double = 0.000000
    private var longitude: Double = 0.000000

    fun latitude(init: () -> Double) {
        latitude = init.invoke()
    }

    fun longitude(init: () -> Double) {
        latitude = init.invoke()
    }

    override fun build(): LatLng {
        return LatLng(latitude, longitude)
    }
}

class DrawableBuilder : Builder<BitmapDescriptor> {

    private var descriptor =
            BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)

    fun resource(@DrawableRes resource: Int) {
        descriptor = BitmapDescriptorFactory.fromResource(resource)
    }

    override fun build(): BitmapDescriptor {
        return descriptor
    }

}

inline fun location(init: LocationBuilder.() -> Unit): LatLng {
    return LocationBuilder().apply(init).build()
}

inline fun marker(init: MarkerBuilder.() -> Unit): MarkerOptions {
    return MarkerBuilder().apply(init).build()
}