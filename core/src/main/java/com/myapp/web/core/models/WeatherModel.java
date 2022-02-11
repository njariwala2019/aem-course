package com.myapp.web.core.models;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.myapp.web.core.services.CustomHttpService;
import lombok.Data;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.InjectionStrategy;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

import static org.apache.sling.api.resource.ResourceResolver.PROPERTY_RESOURCE_TYPE;

@Model(adaptables = Resource.class)
@Data
public class WeatherModel {

    @ValueMapValue(name=PROPERTY_RESOURCE_TYPE, injectionStrategy= InjectionStrategy.OPTIONAL)
    @Default(values="No resourceType")
    protected String resourceType;

    @ValueMapValue(name="location", injectionStrategy= InjectionStrategy.OPTIONAL)
    @Default(values="")
    protected String location;

    private String weatherInfo;

    private String weatherDescription;

    @OSGiService
    private CustomHttpService customHttpService;

    @PostConstruct
    public void init(){
        Map<String,String> params = new HashMap<>();
        params.put("q",location);
        params.put("APPID", "fcdf44219f963e2fdb76413c8535be35");

        weatherInfo = customHttpService.makeGetCall("https://api.openweathermap.org/data/2.5/weather",String.class,params);

        JsonObject jsonObject = new Gson().fromJson(weatherInfo, JsonObject.class);
        JsonObject weatherDescriptionObj = jsonObject.getAsJsonArray("weather").get(0).getAsJsonObject();

        weatherDescription = weatherDescriptionObj.get("description").getAsString();
    }
}
