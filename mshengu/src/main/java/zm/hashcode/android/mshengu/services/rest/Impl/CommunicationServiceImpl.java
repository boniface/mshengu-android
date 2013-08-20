package zm.hashcode.android.mshengu.services.rest.Impl;

import org.springframework.http.*;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import zm.hashcode.android.mshengu.model.SiteReource;
import zm.hashcode.android.mshengu.model.UnitDeliveryResource;
import zm.hashcode.android.mshengu.model.UnitServiceResource;
import zm.hashcode.android.mshengu.repository.DatasourceDAO;
import zm.hashcode.android.mshengu.services.rest.CommunicationService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: boniface
 * Date: 2013/08/14
 * Time: 10:08 AM
 * To change this template use File | Settings | File Templates.
 */
public class CommunicationServiceImpl implements CommunicationService {
    private DatasourceDAO dao;

    public CommunicationServiceImpl(DatasourceDAO datasourceDAO) {
        this.dao = datasourceDAO;
    }


    @Override
    public String postDeployment(UnitDeliveryResource unitDeliveryResource) {
        final String url = dao.getSettings().getUrl() + "tagunit";


        HttpEntity<UnitDeliveryResource> requestEntity = new HttpEntity<UnitDeliveryResource>(unitDeliveryResource, getContentType());
//        Make the HTTP POST request, marshaling the request to JSON, and the response to a String
        ResponseEntity<String> responseEntity = getConnection().exchange(url, HttpMethod.POST, requestEntity, String.class);

        return responseEntity.getBody();
    }

    @Override
    public String postService(UnitServiceResource unitServiceResource) {
        final String url = dao.getSettings().getUrl() + "serviceunit";

        HttpEntity<UnitServiceResource> requestEntity = new HttpEntity<UnitServiceResource>(unitServiceResource, getContentType());
        // Make the HTTP POST request, marshaling the request to JSON, and the response to a String
        ResponseEntity<String> responseEntity = getConnection().exchange(url, HttpMethod.POST, requestEntity, String.class);

        return responseEntity.getBody();
    }

    @Override
    public boolean checkLocality(UnitDeliveryResource unitDeliveryResource) {
        String url = "localhost";

        HttpEntity<UnitDeliveryResource> requestEntity = new HttpEntity<UnitDeliveryResource>(unitDeliveryResource, getContentType());
        // Make the HTTP POST request, marshaling the request to JSON, and the response to a String
//        ResponseEntity<String> responseEntity = getConnection().exchange(url, HttpMethod.POST, requestEntity, String.class);


        return true;
    }

    @Override
    public List<SiteReource> getSites() {
        final String url = dao.getSettings().getUrl() + "sites";
        List<SiteReource> sites = new ArrayList<SiteReource>();
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setAccept(Collections.singletonList(new MediaType("application", "json")));
        HttpEntity<?> requestEntity = new HttpEntity<Object>(requestHeaders);

        // Create a new RestTemplate instance
        RestTemplate restTemplate = new RestTemplate();

        // Add the Gson message converter
        restTemplate.getMessageConverters().add(new GsonHttpMessageConverter());
        System.out.println("The URL IS !!!!!" + url);

        ResponseEntity<SiteReource[]> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, SiteReource[].class);
        SiteReource[] events = responseEntity.getBody();

        for (SiteReource event : events) {
            sites.add(event);
        }
        return sites;
    }


    private RestTemplate getConnection() {
        final RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
        return restTemplate;
    }

    private HttpHeaders getContentType() {
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(new MediaType("application", "json"));
        return requestHeaders;

    }
}
