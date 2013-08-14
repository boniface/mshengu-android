package zm.hashcode.android.mshengu.model;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: boniface
 * Date: 2013/08/14
 * Time: 9:25 AM
 * To change this template use File | Settings | File Templates.
 */
public class LocationResource implements Serializable{
    private String unitID;
    private String longitude;
    private String latititude;

    public String getUnitID() {
        return unitID;
    }

    public void setUnitID(String unitID) {
        this.unitID = unitID;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatititude() {
        return latititude;
    }

    public void setLatititude(String latititude) {
        this.latititude = latititude;
    }
}
