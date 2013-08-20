package zm.hashcode.android.mshengu.view;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.actionbarsherlock.app.SherlockFragment;
import com.google.zxing.integration.android.IntentIntegrator;
import zm.hashcode.android.mshengu.R;
import zm.hashcode.android.mshengu.model.Settings;
import zm.hashcode.android.mshengu.model.UnitDeliveryResource;
import zm.hashcode.android.mshengu.model.UnitServiceResource;
import zm.hashcode.android.mshengu.repository.DatasourceDAO;
import zm.hashcode.android.mshengu.repository.Impl.DatasourceDAOImpl;
import zm.hashcode.android.mshengu.services.rest.CommunicationService;
import zm.hashcode.android.mshengu.services.rest.Impl.CommunicationServiceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: boniface
 * Date: 2013/08/12
 * Time: 8:20 PM
 * To change this template use File | Settings | File Templates.
 */
public class ServiceFragment extends SherlockFragment {

    private String selectedCountry = null;
    private String selectedAnimal = null;

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


        final DatasourceDAO dao = new DatasourceDAOImpl(getActivity());
        final CommunicationService communicationService = new CommunicationServiceImpl(dao);
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
                final UnitDeliveryResource unitDeliveryResource = new UnitDeliveryResource();
                unitDeliveryResource.setUnitId(unitId.getText().toString());
                unitDeliveryResource.setLatitude(String.valueOf(latitude));
                unitDeliveryResource.setLongitude(String.valueOf(longitude));

                UnitServiceResource unitServiceResource = new UnitServiceResource();
                unitServiceResource.setUnitId(unitId.getText().toString());
                Map<String, Boolean> tasks = new HashMap<String, Boolean>();
                tasks.put("pumpout", pumpout.isChecked());
                tasks.put("flush", flush.isChecked());
                tasks.put("chemical", chemical.isChecked());
//                unitServiceResource.setTasks(tasks);
                unitServiceResource.setIncident(comments.getText().toString());
//                if(communicationService.checkLocality(unitDeliveryResource)) {
//
//                    communicationService.postService(unitServiceResource);
//
//                } else{
//                    Toast.makeText(getActivity(),"You are out of Range. Data Not submitted!",Toast.LENGTH_LONG).show();
//                }


                List<Settings> sets = dao.getSetiingsList();

                for (Settings set : sets) {
                    System.out.println("The Name us " + set.getUrl());
                }

                System.out.println("The ");

                unitId.setText("");
                comments.setText("");
                pumpout.setChecked(false);
                flush.setChecked(false);
                chemical.setChecked(false);


            }
        });

        //get reference to the spinner from the XML layout
        Spinner spinner = (Spinner) view.findViewById(R.id.spinner);
        //attach the listener to the spinner
        spinner.setOnItemSelectedListener(new MyOnItemSelectedListener());
        //Dynamically generate a spinner data
        createSpinnerDropDown();


        return view;
    }

    public class MyOnItemSelectedListener implements AdapterView.OnItemSelectedListener {

        public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {

            String selectedItem = parent.getItemAtPosition(pos).toString();

            //check which spinner triggered the listener
            switch (parent.getId()) {
                //country spinner
                case R.id.spinner:
                    //make sure the country was already selected during the onCreate
                    if (selectedCountry != null) {
                        Toast.makeText(parent.getContext(), "Country you selected is " + selectedItem,
                                Toast.LENGTH_LONG).show();
                    }
                    selectedCountry = selectedItem;
                    break;
                //animal spinner
                case R.id.spinner1:
                    //make sure the animal was already selected during the onCreate
                    if (selectedAnimal != null) {
                        Toast.makeText(parent.getContext(), "Animal selected is " + selectedItem,
                                Toast.LENGTH_LONG).show();
                    }
                    selectedAnimal = selectedItem;
                    break;
            }


        }

        public void onNothingSelected(AdapterView<?> parent) {
            // Do nothing.
        }
    }

    //Add animals into spinner dynamically
    private void createSpinnerDropDown() {

        //get reference to the spinner from the XML layout
        Spinner spinner = (Spinner) view.findViewById(R.id.spinner1);

        //Array list of animals to display in the spinner
        List<String> list = new ArrayList<String>();
        list.add("Bear");
        list.add("Camel");
        list.add("Cat");
        list.add("Cat");
        list.add("Deer");
        list.add("Dog");
        list.add("Goat");
        list.add("Horse");
        //create an ArrayAdaptar from the String Array
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, list);
        //set the view for the Drop down list
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //set the ArrayAdapter to the spinner
        spinner.setAdapter(dataAdapter);
        //attach the listener to the spinner
        spinner.setOnItemSelectedListener(new MyOnItemSelectedListener());

    }


}
