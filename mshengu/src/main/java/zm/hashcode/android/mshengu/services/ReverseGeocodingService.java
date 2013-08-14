package zm.hashcode.android.mshengu.services;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.AsyncTask;
import zm.hashcode.android.mshengu.listeners.AddressResultListener;
import zm.hashcode.android.mshengu.listeners.ReverseGeocodingListener;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

/**
 * Created with IntelliJ IDEA.
 * User: boniface
 * Date: 2013/08/12
 * Time: 9:37 PM
 * To change this template use File | Settings | File Templates.
 */
public class ReverseGeocodingService extends AsyncTask<Location, Void, Void> {

    private final ReverseGeocodingListener mListener;
    private final Context mContext;
    private Address mAddress;

    public ReverseGeocodingService(Context context, ReverseGeocodingListener listener) {
        mContext = context;
        mListener = listener;
    }

    @Override
    protected Void doInBackground(Location... locations) {
        // create an instance of a Geocoder
        Geocoder geocoder = new Geocoder(mContext, Locale.getDefault());

        // get the first location
        Location loc = locations[0];
        // a location might be associated with multiple addresses; so we need a list
        List<Address> addresses = null;

        try {
            // ask the Geocoder to give a list of address for the given latitude and longitude
            // 1 means max result - we need only 1
            addresses = geocoder.getFromLocation(loc.getLatitude(), loc.getLongitude(), 1);
        } catch (IOException e) {
            mAddress = null;
        }

        // get the first address
        if (addresses != null && addresses.size() > 0) {
            mAddress = addresses.get(0);
        }

        return null;
    }
}