package zm.hashcode.android.mshengu.listeners;

import android.location.Location;

/**
 * Created with IntelliJ IDEA.
 * User: boniface
 * Date: 2013/08/12
 * Time: 9:18 PM
 * To change this template use File | Settings | File Templates.
 */
public interface LocationResultListener {
    public void onLocationResultAvailable(Location location);
}