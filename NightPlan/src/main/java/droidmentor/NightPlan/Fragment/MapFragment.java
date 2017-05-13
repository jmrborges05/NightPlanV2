package droidmentor.NightPlan.Fragment;

import android.content.Context;
import android.graphics.BitmapRegionDecoder;
import android.graphics.Color;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;


import java.util.ArrayList;
import java.util.List;

import droidmentor.bnv_with_viewpager.R;

import static android.R.id.list;

/**
 * Created by joaoborges on 13/05/2017.
 */

public class MapFragment extends Fragment implements GoogleMap.OnMarkerClickListener,
        OnMapReadyCallback {


    MapView mMapView;
    private GoogleMap googleMap;

    private static final LatLng PERTH = new LatLng(38.704848676178585, -9.166386646489494);
    private static final LatLng SYDNEY = new LatLng(38.72214852559799, -9.12914514541626);
    private static final LatLng BRISBANE = new LatLng(38.689405,  -9.337181);

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.location_fragment, container, false);

        mMapView = (MapView) rootView.findViewById(R.id.mapView);
        mMapView.onCreate(savedInstanceState);

        mMapView.onResume(); // needed to get the map to display immediately

        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }

        mMapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap mMap) {
                googleMap = mMap;

                // For showing a move to my location button
                googleMap.setMyLocationEnabled(true);

                // For dropping a marker at a point on the Map
                LatLng sydney = new LatLng(38.7437396,-9.2302445);
                googleMap.addMarker(new MarkerOptions().position(sydney).title("Marker Title").snippet("Marker Description"));

                googleMap.addMarker(new MarkerOptions()
                        .position(PERTH)
                        .title("Come Prima"));

                googleMap.addMarker(new MarkerOptions()
                        .position(SYDNEY)
                        .title("Italian Restaurant"));

                googleMap.addMarker(new MarkerOptions()
                        .position(BRISBANE)
                        .title("Davito")
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.logo)));



                //MARK Linha entre pois
                PolylineOptions options = new PolylineOptions().width(5).color(Color.BLUE).geodesic(true);

                options.add(PERTH);
                options.add(sydney);
                options.add(SYDNEY);
                options.add(BRISBANE);


                googleMap.addPolyline(options);



                // For zooming automatically to the location of the marker
                CameraPosition cameraPosition = new CameraPosition.Builder().target(sydney).zoom(12).build();
                googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
            }
        });

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }

    public void onMapReady(GoogleMap map) {
    }

    public boolean onMarkerClick(final Marker marker) {

        // Retrieve the data from the marker.


        // Return false to indicate that we have not consumed the event and that we wish
        // for the default behavior to occur (which is for the camera to move such that the
        // marker is centered and for the marker's info window to open, if it has one).
        return false;
    }
}

