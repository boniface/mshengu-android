package zm.hashcode.android.mshengu.services.rest;

import zm.hashcode.android.mshengu.model.LocationResource;
import zm.hashcode.android.mshengu.model.ServiceResource;

/**
 * Created with IntelliJ IDEA.
 * User: boniface
 * Date: 2013/08/14
 * Time: 10:01 AM
 * To change this template use File | Settings | File Templates.
 */
public interface CommunicationService {
    public String postDeployment(LocationResource locationResource);
    public String postService(ServiceResource serviceResource);
    public boolean checkLocality(LocationResource locationResource);
}
