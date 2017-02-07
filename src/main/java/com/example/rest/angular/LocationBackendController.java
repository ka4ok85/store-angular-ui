package com.example.rest.angular;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.dto.location.Location;
import com.example.dto.location.LocationPageContainer;
import com.example.dto.location.LocationProductlocationWrapper;
import com.example.dto.page.PageImplementation;
import com.example.dto.productlocation.Productlocation;
import com.example.helper.RestAuthTokenHelper;



@RestController
public class LocationBackendController {

	@Value("${rest.url}")
    private String restUrl;
	
	@RequestMapping(value="/locations", method=RequestMethod.GET)
	public ResponseEntity<LocationPageContainer<LocationProductlocationWrapper>> getLocations(@RequestParam(value = "available", required = false) Boolean isAvailable, @RequestParam(value = "shelf", required = false) Long shelf, Pageable pageable, ServletRequest request) {
		String authToken = ((HttpServletRequest) request).getHeader("Authorization");
        HttpEntity<?> httpEntity = RestAuthTokenHelper.getHttpEntity(authToken);  
		
        RestTemplate restTemplate = new RestTemplate();
        List<Location> locationsList = new ArrayList<Location>();
        String baseUrl = restUrl + "/v1/storelocations?page=" + (pageable.getPageNumber() - 1) + "&=size=" + pageable.getPageSize();
        
        ParameterizedTypeReference<PageImplementation<Location>> responseType = new ParameterizedTypeReference<PageImplementation<Location>>() { };
        ResponseEntity<PageImplementation<Location>> result = restTemplate.exchange(baseUrl, HttpMethod.GET, httpEntity, responseType);
        locationsList.addAll(result.getBody().getContent());

        String productsUrl = restUrl + "/v1/productlocations?storelocationid=";
        StringBuffer productsUrlStringBuffer = new StringBuffer();
        productsUrlStringBuffer.append(productsUrl);
        int index = 1;
        for (Location location : locationsList) {
        	productsUrlStringBuffer.append(location.getId());
        	if (locationsList.size() > index) {
        		productsUrlStringBuffer.append(",");
        	}

        	index++;
		}
        
        productsUrl = productsUrlStringBuffer.toString();
        ResponseEntity<Productlocation[]> body  = restTemplate.exchange(productsUrl, HttpMethod.GET, httpEntity, Productlocation[].class);
        Productlocation[] productlocations = body.getBody();
        Map<Long, Productlocation> productMap = new HashMap<Long, Productlocation>();
        for (Productlocation productlocation : productlocations) {
        	productMap.put(productlocation.getStorelocation().getId(), productlocation);
		}

        List<LocationProductlocationWrapper> locationProductlocationWrapperList = new ArrayList<LocationProductlocationWrapper>();
        LocationProductlocationWrapper locationProductlocationWrapper;
        for (Location location : locationsList) {
        	locationProductlocationWrapper = new LocationProductlocationWrapper();
        	locationProductlocationWrapper.setStorelocation(location);
        	if (productMap.containsKey(location.getId())) {
        		locationProductlocationWrapper.setProductlocation(productMap.get(location.getId()));
        	}
        	
        	locationProductlocationWrapperList.add(locationProductlocationWrapper);
        }

        LocationPageContainer<LocationProductlocationWrapper> resultResponse = new LocationPageContainer<LocationProductlocationWrapper>();
        
        resultResponse.setSize(result.getBody().getSize());
        resultResponse.setContent(locationProductlocationWrapperList);
        resultResponse.setTotalElements((int) result.getBody().getTotalElements());
        
        return new ResponseEntity<>(resultResponse, HttpStatus.OK);
	}
	
	
	@RequestMapping(value="/location", method=RequestMethod.GET)
	public String getLocation(@RequestParam(value = "available", required = false) Boolean isAvailable, @RequestParam(value = "shelf", required = false) Long shelf, Pageable pageable, ServletRequest request) {
		return "ZZZ";
	
	}

}
