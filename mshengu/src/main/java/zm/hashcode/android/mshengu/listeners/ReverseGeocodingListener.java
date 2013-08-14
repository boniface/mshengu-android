package zm.hashcode.android.mshengu.listeners;

import android.location.Address;

/**
 * Created with IntelliJ IDEA.
 * User: boniface
 * Date: 2013/08/12
 * Time: 9:35 PM
 * To change this template use File | Settings | File Templates.
 */
public interface ReverseGeocodingListener {
    public void onAddressAvailable(Address address);
}
