package zm.hashcode.android.mshengu.view;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.actionbarsherlock.app.SherlockFragment;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import zm.hashcode.android.mshengu.R;

/**
 * Created with IntelliJ IDEA.
 * User: boniface
 * Date: 2013/08/12
 * Time: 8:22 PM
 * To change this template use File | Settings | File Templates.
 */
public class MapFragment  extends SherlockFragment{
    private MapView mapView;
    private GoogleMap googleMap;
    private Bundle bundle;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        View inflatedView = inflater.inflate(R.layout.map_frag, container, false);

        try {
            MapsInitializer.initialize(getActivity());
        } catch (GooglePlayServicesNotAvailableException e) {
            // TODO handle this situation

        }

        mapView = (MapView) inflatedView.findViewById(R.id.map);
        mapView.onCreate(bundle);
        setUpMapIfNeeded(inflatedView);

        return inflatedView;
    }
    private void setUpMapIfNeeded(View inflatedView) {
        if (googleMap == null) {
            googleMap = ((MapView) inflatedView.findViewById(R.id.map)).getMap();
            if (googleMap != null) {
                setUpMap();
            }
        }

    }

    private void setUpMap() {
        // Enable Location
        googleMap.setMyLocationEnabled(true);

        // get Location manager
        LocationManager locationManager = (LocationManager)getActivity().getSystemService(Context.LOCATION_SERVICE);

        // Create Criteria Object to Retrieve Provider
        Criteria criteria = new Criteria();

        // Get the name of the Best Provider
        String provider = locationManager.getBestProvider(criteria, true);

        //Current Location

        Location location = locationManager.getLastKnownLocation(provider);

        //setMapType
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        //Get current Latitude
        double latitude = location.getLatitude();
        //get Current Longitude
        double longitude = location.getLongitude();

        //Latln Object

        LatLng latLng = new LatLng(latitude, longitude);

        // Show the Current Location in Google Map

        googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));

        // Zoom In
        googleMap.animateCamera(CameraUpdateFactory.zoomTo(15));
        googleMap.addMarker(new MarkerOptions().position(new LatLng(latitude, longitude)).title("You are Here"));
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bundle = savedInstanceState;
    }

    @Override
    public void onResume() {
        super.onResume();
        setUpMap();
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onDestroy() {
        mapView.onDestroy();
        super.onDestroy();
    }




}
