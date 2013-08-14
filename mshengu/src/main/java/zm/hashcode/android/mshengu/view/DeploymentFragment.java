package zm.hashcode.android.mshengu.view;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import com.actionbarsherlock.app.SherlockFragment;
import com.google.zxing.integration.android.IntentIntegrator;
import zm.hashcode.android.mshengu.R;
import zm.hashcode.android.mshengu.model.LocationResource;
import zm.hashcode.android.mshengu.services.rest.CommunicationService;
import zm.hashcode.android.mshengu.services.rest.Impl.CommunicationServiceImpl;

/**
 * Created with IntelliJ IDEA.
 * User: boniface
 * Date: 2013/08/12
 * Time: 8:21 PM
 * To change this template use File | Settings | File Templates.
 */
public class DeploymentFragment extends SherlockFragment {

    private EditText unitId;
    private EditText latitude;
    private EditText longitude;

    private View view;
    private double lat;
    private double longi;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
      final CommunicationService communicationService = new CommunicationServiceImpl();


        LocationManager locationManager = (LocationManager) getActivity()
                .getSystemService(Context.LOCATION_SERVICE);
        // Create Criteria Object to Retrieve Provider
        Criteria criteria = new Criteria();

        // Get the name of the Best Provider
        String provider = locationManager.getBestProvider(criteria, true);

        // Current Location

        Location location = locationManager.getLastKnownLocation(provider);

        // setMapType

        // Get current Latitude
        lat = location.getLatitude();
        // get Current Longitude
        longi = location.getLongitude();

        view = inflater.inflate(R.layout.deployment_frag, container, false);
        unitId = (EditText) view.findViewById(R.id.unitID);
        latitude = (EditText) view.findViewById(R.id.latitude);
        longitude = (EditText) view.findViewById(R.id.longitude);
        Button scanButton = (Button) view.findViewById(R.id.scanButton);
        scanButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                IntentIntegrator integrator = new IntentIntegrator(
                        getActivity());
                integrator.initiateScan();
                latitude.setText(String.valueOf(lat));
                longitude.setText(String.valueOf(longi));
            }
        });

        Button tagButton = (Button) view.findViewById(R.id.tagButton);
        tagButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                final LocationResource locationResource = new LocationResource();
                locationResource.setUnitID(unitId.getText().toString());
                locationResource.setLatititude(latitude.getText().toString());
                locationResource.setLongitude(longitude.getText().toString());
                communicationService.postDeployment(locationResource);
                unitId.setText("");
                latitude.setText("");
                longitude.setText("");
            }
        });
        return view;
    }

}
