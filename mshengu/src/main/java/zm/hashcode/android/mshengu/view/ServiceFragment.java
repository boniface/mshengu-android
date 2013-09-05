package zm.hashcode.android.mshengu.view;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import com.actionbarsherlock.app.SherlockFragment;
import com.google.zxing.integration.android.IntentIntegrator;
import zm.hashcode.android.mshengu.R;
import zm.hashcode.android.mshengu.model.UnitServiceResource;
import zm.hashcode.android.mshengu.repository.DatasourceDAO;
import zm.hashcode.android.mshengu.repository.Impl.DatasourceDAOImpl;
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

    private CheckBox pumpOut;
    private CheckBox washBucket;
    private CheckBox suctionOut;
    private CheckBox scrubFloor;
    private CheckBox rechargeBacket;
    private CheckBox cleanPerimeter;


    private View view;
    private double latitude;
    private double longitude;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        final DatasourceDAO dao = new DatasourceDAOImpl(getActivity());
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

        pumpOut = (CheckBox) view.findViewById(R.id.pumpOut);
        washBucket = (CheckBox) view.findViewById(R.id.washBucket);
        suctionOut = (CheckBox) view.findViewById(R.id.suctionOut);

        scrubFloor = (CheckBox) view.findViewById(R.id.scrubFloor);
        rechargeBacket = (CheckBox) view.findViewById(R.id.rechargeBacket);
        cleanPerimeter = (CheckBox) view.findViewById(R.id.cleanPerimeter);

        pumpOut.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (((CheckBox) v).isChecked()) {
                    pumpOut.setChecked(true);
                } else {
                    pumpOut.setChecked(false);
                }
            }
        });

        washBucket.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (((CheckBox) v).isChecked()) {
                    washBucket.setChecked(true);
                } else {
                    washBucket.setChecked(false);
                }
            }
        });

        suctionOut.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (((CheckBox) v).isChecked()) {
                    suctionOut.setChecked(true);
                } else {
                    suctionOut.setChecked(false);
                }
            }
        });

        scrubFloor.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (((CheckBox) v).isChecked()) {
                    scrubFloor.setChecked(true);
                } else {
                    scrubFloor.setChecked(false);
                }
            }
        });

        rechargeBacket.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (((CheckBox) v).isChecked()) {
                    rechargeBacket.setChecked(true);
                } else {
                    rechargeBacket.setChecked(false);
                }
            }
        });

        cleanPerimeter.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (((CheckBox) v).isChecked()) {
                    cleanPerimeter.setChecked(true);
                } else {
                    cleanPerimeter.setChecked(false);
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

        Button submitServiceButton = (Button) view.findViewById(R.id.submit_Service_button);
        submitServiceButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                final UnitServiceResource unitServiceResource = new UnitServiceResource();
                unitServiceResource.setLatitude(String.valueOf(latitude));
                unitServiceResource.setLongitude(String.valueOf(longitude));
                unitServiceResource.setUnitId(unitId.getText().toString());
                Map<String, Boolean> tasks = new HashMap<String, Boolean>();
                tasks.put("pumpOut", pumpOut.isChecked());
                tasks.put("washBucket", washBucket.isChecked());
                tasks.put("suctionOut", suctionOut.isChecked());
                tasks.put("scrubFloor", scrubFloor.isChecked());
                tasks.put("rechargeBacket", rechargeBacket.isChecked());
                tasks.put("cleanPerimeter", cleanPerimeter.isChecked());

                unitServiceResource.setServices(tasks);
                unitServiceResource.setIncident(comments.getText().toString());

                final AsyncCallPostService post = new AsyncCallPostService(unitServiceResource);
                post.execute();

                unitId.setText("");
                comments.setText("");
                pumpOut.setChecked(false);
                washBucket.setChecked(false);
                suctionOut.setChecked(false);
                scrubFloor.setChecked(false);
                rechargeBacket.setChecked(false);
                cleanPerimeter.setChecked(false);


            }
        });


        return view;
    }


    private class AsyncCallPostService extends AsyncTask<Void, Void, Void> {
        final UnitServiceResource unitServiceResource;

        private AsyncCallPostService(UnitServiceResource unitDeliveryResource) {
            this.unitServiceResource = unitDeliveryResource;
        }

        @Override
        protected Void doInBackground(Void... params) {

            postService(unitServiceResource);
            return null;
        }
    }

    public void postService(UnitServiceResource unitDeliveryResource) {
        DatasourceDAO dao = new DatasourceDAOImpl(getActivity());
        final CommunicationService communicationService = new CommunicationServiceImpl(dao);
        communicationService.postService(unitDeliveryResource);

    }


}
