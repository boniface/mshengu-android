package zm.hashcode.android.mshengu.model;

import java.io.Serializable;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: boniface
 * Date: 2013/08/14
 * Time: 10:04 AM
 * To change this template use File | Settings | File Templates.
 */
public class UnitServiceResource implements Serializable {
    private static final long serialVersionUID = 1L;
    private String unitId;
    private Map<String, Boolean> services;
    private String incident;

    public String getUnitId() {
        return unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }

    public Map<String, Boolean> getServices() {
        return services;
    }

    public void setServices(Map<String, Boolean> services) {
        this.services = services;
    }

    public String getIncident() {
        return incident;
    }

    public void setIncident(String incident) {
        this.incident = incident;
    }
}
