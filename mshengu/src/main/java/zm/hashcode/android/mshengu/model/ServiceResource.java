package zm.hashcode.android.mshengu.model;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: boniface
 * Date: 2013/08/14
 * Time: 10:04 AM
 * To change this template use File | Settings | File Templates.
 */
public class ServiceResource implements Serializable{
    private String unitID;
    private Map<String,Boolean> tasks;
    private String incident;

    public String getUnitID() {
        return unitID;
    }

    public void setUnitID(String unitID) {
        this.unitID = unitID;
    }

    public String getIncident() {
        return incident;
    }

    public void setIncident(String incident) {
        this.incident = incident;
    }

    public Map<String, Boolean> getTasks() {
        return tasks;
    }

    public void setTasks(Map<String, Boolean> tasks) {
        this.tasks = tasks;
    }
}
