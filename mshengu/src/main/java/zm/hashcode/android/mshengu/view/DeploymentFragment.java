package zm.hashcode.android.mshengu.view;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.actionbarsherlock.app.SherlockFragment;
import com.google.zxing.integration.android.IntentIntegrator;
import zm.hashcode.android.mshengu.R;
import zm.hashcode.android.mshengu.model.SiteReource;
import zm.hashcode.android.mshengu.model.UnitDeliveryResource;
import zm.hashcode.android.mshengu.repository.DatasourceDAO;
import zm.hashcode.android.mshengu.repository.Impl.DatasourceDAOImpl;
import zm.hashcode.android.mshengu.services.rest.CommunicationService;
import zm.hashcode.android.mshengu.services.rest.Impl.CommunicationServiceImpl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: boniface
 * Date: 2013/08/12
 * Time: 8:21 PM
 * To change this template use File | Settings | File Templates.
 */
public class DeploymentFragment extends SherlockFragment {
    private String selectedSite = null;

    private EditText unitId;
    private EditText latitude;
    private EditText longitude;
    private Spinner siteInput;
    private List<String> list = new ArrayList<String>();
    ;

    private View view;
    private double lat;
    private double longi;
    private String TAG = "Mshengu";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        AsyncCallREST task = new AsyncCallREST();
        task.execute();


        LocationManager locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        String provider = locationManager.getBestProvider(criteria, true);
        Location location = locationManager.getLastKnownLocation(provider);
        lat = location.getLatitude();
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
                final UnitDeliveryResource unitDeliveryResource = new UnitDeliveryResource();
                unitDeliveryResource.setUnitId(unitId.getText().toString());
                unitDeliveryResource.setLatitude(latitude.getText().toString());
                unitDeliveryResource.setLongitude(longitude.getText().toString());
                unitDeliveryResource.setSiteId(selectedSite);

                final AsyncCallPost postService = new AsyncCallPost(unitDeliveryResource);
                postService.execute();
                unitId.setText("");
                latitude.setText("");
                longitude.setText("");


            }
        });


        createSpinnerSiteDropDown();

        return view;
    }

    //Add animals into spinner dynamically
    private void createSpinnerSiteDropDown() {

        //get reference to the spinner from the XML layout
        siteInput = (Spinner) view.findViewById(R.id.sites_drop_down);
        //create an ArrayAdaptar from the String Array
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, list);
        //set the view for the Drop down list
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //set the ArrayAdapter to the spinner
        siteInput.setAdapter(dataAdapter);
        //attach the listener to the spinner
        siteInput.setOnItemSelectedListener(new MyOnItemSelectedListener());

    }

    public class MyOnItemSelectedListener implements AdapterView.OnItemSelectedListener {

        public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {

            String selectedItem = parent.getItemAtPosition(pos).toString();

            //check which spinner triggered the listener
            switch (parent.getId()) {
                //country spinner
                case R.id.sites_drop_down:
                    //make sure the country was already selected during the onCreate
                    if (selectedSite != null) {
                        Toast.makeText(parent.getContext(), "Site selected is " + selectedItem,
                                Toast.LENGTH_LONG).show();
                    }
                    selectedSite = selectedItem;
                    break;
            }

        }

        public void onNothingSelected(AdapterView<?> parent) {
            // Do nothing.
        }
    }


    private class AsyncCallREST extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {
            Log.i(TAG, "doInBackground");
            getSites();
            return null;
        }
    }

    private class AsyncCallPost extends AsyncTask<Void, Void, Void> {
        final UnitDeliveryResource unitDeliveryResource;

        private AsyncCallPost(UnitDeliveryResource unitDeliveryResource) {
            this.unitDeliveryResource = unitDeliveryResource;
        }

        @Override
        protected Void doInBackground(Void... params) {
            Log.i(TAG, "doInBackground");
            postDeployment(unitDeliveryResource);
            return null;
        }
    }

    public void getSites() {
        DatasourceDAO dao = new DatasourceDAOImpl(getActivity());
        final CommunicationService communicationService = new CommunicationServiceImpl(dao);
        List<SiteReource> sites = communicationService.getSites();
        Set<String> uniqueSites = new HashSet<String>();
        for (SiteReource siteReource : sites) {

            uniqueSites.add(siteReource.getName());
        }
        list.clear();
        list.addAll(uniqueSites);

    }

    public void postDeployment(UnitDeliveryResource unitDeliveryResource) {
        DatasourceDAO dao = new DatasourceDAOImpl(getActivity());
        final CommunicationService communicationService = new CommunicationServiceImpl(dao);
        communicationService.postDeployment(unitDeliveryResource);

    }


}
