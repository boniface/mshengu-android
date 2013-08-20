package zm.hashcode.android.mshengu.model;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: boniface
 * Date: 2013/08/14
 * Time: 9:25 AM
 * To change this template use File | Settings | File Templates.
 */
public class UnitDeliveryResource implements Serializable {

    private static final long serialVersionUID = 1L;
    private String unitId;
    private String latitude;
    private String longitude;
    private String siteId;

    public String getUnitId() {
        return unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getSiteId() {
        return siteId;
    }

    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }
}
