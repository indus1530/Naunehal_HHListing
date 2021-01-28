package edu.aku.hassannaqvi.naunehal_hhlisting_app.activities.map;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;

import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.GeoDataClient;
import com.google.android.gms.location.places.PlaceDetectionClient;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.maps.android.PolyUtil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import edu.aku.hassannaqvi.naunehal_hhlisting_app.R;
import edu.aku.hassannaqvi.naunehal_hhlisting_app.contracts.VerticesContract;
import edu.aku.hassannaqvi.naunehal_hhlisting_app.core.DatabaseHelper;
import edu.aku.hassannaqvi.naunehal_hhlisting_app.core.MainApp;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private static final float DEFAULT_ZOOM = 17;
    private static final String TAG = "MAY";
    private static final long MINIMUM_DISTANCE_CHANGE_FOR_UPDATES = 1; // in Meters
    private static final long MINIMUM_TIME_BETWEEN_UPDATES = 1000; // in Milliseconds
    private static final int TWO_MINUTES = 1000 * 60 * 2;
    protected LocationManager locationManager;
    //    Login Members Array
    Location location;
    Location mLastKnownLocation;
    DatabaseHelper db;
    private GoogleMap mMap;
    private LatLng mDefaultLocation;
    private ArrayList<LatLng> clusterPoints;
    private ArrayList<LatLng> newClusterPoints;
    private ArrayList<LatLng> ucPoints;
    private PolygonOptions polygon102;
    private LatLng clusterStart;
    private String clusterName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        db = new DatabaseHelper(this);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);

        mapFragment.getMapAsync(this);

        clusterPoints = new ArrayList<LatLng>();
        newClusterPoints = new ArrayList<LatLng>();
        ucPoints = new ArrayList<LatLng>();

        Collection<VerticesContract> vc = db.getVerticesByCluster(MainApp.hh02txt);

        for (VerticesContract v : vc) {


            clusterName = v.getCluster_code();

            clusterPoints.add(new LatLng(v.getPoly_lat(), v.getPoly_lng()));
        }
        clusterStart = (clusterPoints.get(0));
       /* Collection<VerticesUCContract> vcuc = db.getVerticesByUC(MainApp.hh01txt);
        for (VerticesUCContract v : vcuc) {
            ucPoints.add(new LatLng(v.getPoly_lat(), v.getPoly_lng()));
        }*/
    }

    protected boolean isBetterLocation(Location location, Location currentBestLocation) {
        if (currentBestLocation == null) {
            // A new location is always better than no location
            return true;
        }

        // Check whether the new location fix is newer or older
        long timeDelta = location.getTime() - currentBestLocation.getTime();
        boolean isSignificantlyNewer = timeDelta > TWO_MINUTES;
        boolean isSignificantlyOlder = timeDelta < -TWO_MINUTES;
        boolean isNewer = timeDelta > 0;

        // If it's been more than two minutes since the current location, use the new location
        // because the user has likely moved
        if (isSignificantlyNewer) {
            return true;
            // If the new location is more than two minutes older, it must be worse
        } else if (isSignificantlyOlder) {
            return false;
        }

        // Check whether the new location fix is more or less accurate
        int accuracyDelta = (int) (location.getAccuracy() - currentBestLocation.getAccuracy());
        boolean isLessAccurate = accuracyDelta > 0;
        boolean isMoreAccurate = accuracyDelta < 0;
        boolean isSignificantlyLessAccurate = accuracyDelta > 200;

        // Check if the old and new location are from the same provider
        boolean isFromSameProvider = isSameProvider(location.getProvider(),
                currentBestLocation.getProvider());

        // Determine location quality using a combination of timeliness and accuracy
        if (isMoreAccurate) {
            return true;
        } else if (isNewer && !isLessAccurate) {
            return true;
        } else return isNewer && !isSignificantlyLessAccurate && isFromSameProvider;
    }

    /**
     * Checks whether two providers are the same
     */
    private boolean isSameProvider(String provider1, String provider2) {
        if (provider1 == null) {
            return provider2 == null;
        }
        return provider1.equals(provider2);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Get the current location of the device and set the position of the map.
        // Add a marker in Sydney and move the camera
        Marker clusterMarker = mMap.addMarker(new MarkerOptions()
                .position(clusterStart)
                .title(clusterName)
                .anchor(0.5f, 1)
        );

        clusterMarker.showInfoWindow();
        // Instantiates a new Polyline object and adds clusterPoints to define a rectangle
        PolygonOptions rectCluster = new PolygonOptions()
                .fillColor(getResources().getColor(R.color.colorAccentAlpha))
                .strokeColor(Color.RED)
                .zIndex(2.0f);
        rectCluster.addAll(clusterPoints);
/*
        PolygonOptions rectUC = new PolygonOptions()
                .fillColor(getResources().getColor(R.color.dullBlueOverlay))
                .strokeColor(R.color.dullBlack)
                .zIndex(1.0f);

        rectUC.addAll(ucPoints);*/


// Get back the mutable Polyline
        // Cluster Poly
        Polygon polyCluster = mMap.addPolygon(rectCluster);
        polyCluster.setGeodesic(true);
        // UC Poly
       /* Polygon polyUC = mMap.addPolygon(rectUC);
        polyUC.setGeodesic(true);*/


//        mMap.setMyLocationEnabled(true);
        mMap.getUiSettings().setMyLocationButtonEnabled(true);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(clusterPoints.get(0), DEFAULT_ZOOM));

        //DRAW CLUSTER
       /* mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            public void onMapClick(LatLng point) {
                newClusterPoints.add(new LatLng(point.latitude, point.latitude));

                Toast.makeText(getApplicationContext(), "(" + newClusterPoints.size() + ") " +
                                point.latitude + ", " + point.longitude,
                        Toast.LENGTH_SHORT).show();

                if (newClusterPoints.size() > 3) {
                    PolygonOptions rectCluster = new PolygonOptions()
                            .fillColor(getResources().getColor(R.color.colorAccentAlpha))
                            .strokeColor(Color.RED);
                    rectCluster.addAll(newClusterPoints);
                    mMap.addPolygon(rectCluster);

                }
            }
        });*/
    }

}
