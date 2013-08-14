package zm.hashcode.android.mshengu.services.rest.Impl;

import org.springframework.http.*;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import zm.hashcode.android.mshengu.model.LocationResource;
import zm.hashcode.android.mshengu.model.ServiceResource;
import zm.hashcode.android.mshengu.services.rest.CommunicationService;

/**
 * Created with IntelliJ IDEA.
 * User: boniface
 * Date: 2013/08/14
 * Time: 10:08 AM
 * To change this template use File | Settings | File Templates.
 */
public class CommunicationServiceImpl implements CommunicationService{


    @Override
    public String postDeployment(LocationResource locationResource) {
        String url = "localhost" ;

        HttpEntity<LocationResource> requestEntity = new HttpEntity<LocationResource>(locationResource, getContentType());
        // Make the HTTP POST request, marshaling the request to JSON, and the response to a String
//        ResponseEntity<String> responseEntity = getConnection().exchange(url, HttpMethod.POST, requestEntity, String.class);
        System.out.println("LATITITUBE !!!!!!!!! "+locationResource.getLatititude());
        System.out.println("LONGITUTED !!!!!!!! "+locationResource.getLongitude());
        System.out.println("ID IS !!!!!!!!!!!!!!!!!"+locationResource.getUnitID());
       return null; // responseEntity.getBody();
    }

    @Override
    public String postService(ServiceResource serviceResource) {
        String url = "localhost"  ;

        HttpEntity<ServiceResource> requestEntity = new HttpEntity<ServiceResource>(serviceResource, getContentType());
        // Make the HTTP POST request, marshaling the request to JSON, and the response to a String
//        ResponseEntity<String> responseEntity = getConnection().exchange(url, HttpMethod.POST, requestEntity, String.class);

        System.out.println("PUMP_OUT !!!!!!!! "+serviceResource.getTasks().get("pumpout"));
        System.out.println("FLUSH !!!!!!!! "+serviceResource.getTasks().get("flush"));
        System.out.println("CHEMICAL !!!!!!!! "+serviceResource.getTasks().get("chemical"));
        System.out.println("ID IS !!!!!!!!!!!!!!!!!"+serviceResource.getUnitID());
        System.out.println("Comments IS !!!!!!!!!!!!!!!!!"+serviceResource.getIncident());
        return null; //responseEntity.getBody();
    }

    @Override
    public boolean checkLocality(LocationResource locationResource) {
        String url = "localhost" ;

        HttpEntity<LocationResource> requestEntity = new HttpEntity<LocationResource>(locationResource, getContentType());
        // Make the HTTP POST request, marshaling the request to JSON, and the response to a String
//        ResponseEntity<String> responseEntity = getConnection().exchange(url, HttpMethod.POST, requestEntity, String.class);

        System.out.println("LATITITUBE !!!!!!!!! "+locationResource.getLatititude());
        System.out.println("LONGITUTED !!!!!!!! "+locationResource.getLongitude());
        System.out.println("ID IS !!!!!!!!!!!!!!!!!"+locationResource.getUnitID());
        return true;
    }

    private RestTemplate getConnection(){
        final RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
        return restTemplate;
    }

    private HttpHeaders getContentType() {
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(new MediaType("application","json"));
        return requestHeaders;

    }
}
