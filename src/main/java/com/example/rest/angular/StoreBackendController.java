package com.example.rest.angular;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.dto.page.PageImplementation;
import com.example.dto.store.Store;

@RestController
public class StoreBackendController {

	@Value("${rest.url}")
    private String restUrl;
	
	@RequestMapping(value="/stores", method=RequestMethod.GET)
    public Store[] storeList() {
        RestTemplate restTemplate = new RestTemplate();

        List<Store> storeList = new ArrayList<Store>();
        String baseUrl = restUrl + "/v1/stores";
        String url = "";

        ParameterizedTypeReference<PageImplementation<Store>> responseType = new ParameterizedTypeReference<PageImplementation<Store>>() { };
        ResponseEntity<PageImplementation<Store>> result = restTemplate.exchange(baseUrl, HttpMethod.GET, null, responseType);
        storeList.addAll(result.getBody().getContent());
        
        int page = 1;
        while (result.getBody().isLast() == false) {
        	url = baseUrl + "?page=" + page;
        	System.out.println(url);
        	result = restTemplate.exchange(url, HttpMethod.GET, null, responseType);
        	storeList.addAll(result.getBody().getContent());
        	page++;
        }

        // convert ArrayList into array
        Store stores[] = new Store[storeList.size()];
        stores = storeList.toArray(stores);        
 
        return stores;
    }
}
