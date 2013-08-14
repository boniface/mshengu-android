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
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import com.actionbarsherlock.app.SherlockFragment;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
import com.google.zxing.integration.android.IntentIntegrator;
import zm.hashcode.android.mshengu.R;
import zm.hashcode.android.mshengu.model.LocationResource;
import zm.hashcode.android.mshengu.model.ServiceResource;
import zm.hashcode.android.mshengu.services.rest.CommunicationService;
import zm.hashcode.android.mshengu.services.rest.Impl.CommunicationServiceImpl;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: boniface
 * Date: 2013/08/12
 * Time: 8:20 PM
 * To change this template use File | Settings | File Templates.
 */
public class ServiceFragment extends SherlockFragment {

    private EditText unitId;
    private EditText comments;

    private CheckBox pumpout;
    private CheckBox flush;
    private CheckBox chemical;


    private View view;
    private double latitude;
    private double longitude;
    private String addressString;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final CommunicationService communicationService = new CommunicationServiceImpl();
        setHasOptionsMenu(true);

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
        latitude = location.getLatitude();
        // get Current Longitude
        longitude = location.getLongitude();


        view = inflater.inflate(R.layout.service_frag, container, false);
        unitId = (EditText) view.findViewById(R.id.unitID);

        comments = (EditText) view.findViewById(R.id.comments);

        pumpout = (CheckBox) view.findViewById(R.id.pumpout);
        flush = (CheckBox) view.findViewById(R.id.flush);
        chemical = (CheckBox) view.findViewById(R.id.chemical);

        pumpout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (((CheckBox) v).isChecked()) {
                    pumpout.setChecked(true);
                } else {
                    pumpout.setChecked(false);
                }
            }
        });

        flush.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (((CheckBox) v).isChecked()) {
                    flush.setChecked(true);
                } else {
                    flush.setChecked(false);
                }
            }
        });

        chemical.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (((CheckBox) v).isChecked()) {
                    chemical.setChecked(true);
                } else {
                    chemical.setChecked(false);
                }
            }
        });


        Button scanButton = (Button) view.findViewById(R.id.scanButton);
        scanButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                IntentIntegrator integrator = new IntentIntegrator(
                        getActivity());
                integrator.initiateScan();


            }
        });

        Button tagButton = (Button) view.findViewById(R.id.tagButton);
        tagButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                final LocationResource locationResource = new LocationResource();
                locationResource.setUnitID(unitId.getText().toString());
                locationResource.setLatititude(String.valueOf(latitude));
                locationResource.setLongitude(String.valueOf(longitude));

                ServiceResource serviceResource = new ServiceResource();
                serviceResource.setUnitID(unitId.getText().toString());
                Map<String,Boolean> tasks = new HashMap<String, Boolean>();
                tasks.put("pumpout",pumpout.isChecked());
                tasks.put("flush",flush.isChecked());
                tasks.put("chemical",chemical.isChecked());
                serviceResource.setTasks(tasks);
                serviceResource.setIncident(comments.getText().toString());
                if(communicationService.checkLocality(locationResource)) {

                    communicationService.postService(serviceResource);

                } else{
                    Toast.makeText(getActivity(),"You are out of Range. Data Not submitted!",Toast.LENGTH_LONG).show();
                }

                unitId.setText("");
                comments.setText("");
                pumpout.setChecked(false);
                flush.setChecked(false);
                chemical.setChecked(false);


            }
        });

        return view;
    }


}
