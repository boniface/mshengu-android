package zm.hashcode.android.mshengu.services.rest;

import zm.hashcode.android.mshengu.model.SiteReource;
import zm.hashcode.android.mshengu.model.UnitDeliveryResource;
import zm.hashcode.android.mshengu.model.UnitServiceResource;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: boniface
 * Date: 2013/08/14
 * Time: 10:01 AM
 * To change this template use File | Settings | File Templates.
 */
public interface CommunicationService {
    public String postDeployment(UnitDeliveryResource unitDeliveryResource);

    public String postService(UnitServiceResource unitServiceResource);

    public boolean checkLocality(UnitDeliveryResource unitDeliveryResource);

    public List<SiteReource> getSites();
}
