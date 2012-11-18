package net.sareweb.android.barazkide;

import org.springframework.http.converter.json.GsonHttpMessageConverter;

import com.googlecode.androidannotations.annotations.rest.Rest;

@Rest(rootUrl = "http://192.168.0.14:9080/Barazkide-portlet/api/secure/jsonws/garden", converters = { GsonHttpMessageConverter.class })
public interface GardenRESTClient {

}
