package com.myapp.web.core.services.impl;

import com.google.gson.Gson;
import com.myapp.web.core.services.CustomHttpService;
import com.myapp.web.core.utils.OptionalUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.osgi.services.HttpClientBuilderFactory;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.net.URLDecoder;
import java.util.Map;

@Slf4j
@Component(immediate = true,
        service = CustomHttpService.class,
        property = {"service.vendor=UberDigiTech",
                "service.description=UberDigiTech Custom Http Service"})
public class CustomHttpServiceImpl implements CustomHttpService {


    private Gson gson = new Gson();

    @Reference
    private HttpClientBuilderFactory httpFactory;

    protected HttpClient getHttpClient() {
        return httpFactory.newBuilder().build();
    }


    public <T> T makeGetCall(String url, Class<T> t, Map<String, String> params) {
        String response = null;
        try {
            URIBuilder builder = createBuilderWithParams(url,params);
            HttpGet getRequest = new HttpGet(builder.build());
            HttpClient httpClient = getHttpClient();
            HttpResponse httpResponse = httpClient.execute(getRequest);
            response = IOUtils.toString(httpResponse.getEntity().getContent(), "utf-8");
        } catch (URISyntaxException | IOException e) {
            log.error("Exception: {}",e.getMessage());
        }
        return t.equals(String.class) ? t.cast(response) : gson.fromJson(response, t);
    }


    private URIBuilder createBuilderWithParams(String url, Map<String,String> params) throws URISyntaxException {
        URIBuilder builder = new URIBuilder(url);
        OptionalUtil.resolveNestedField(() -> params)
                .ifPresent(httpParams -> params.forEach((k, v) -> {
                    try {
                        builder.addParameter(k, URLDecoder.decode(v, "utf-8"));
                    } catch (UnsupportedEncodingException e) {
                        log.error("UnsupportedEncodingException : {} ", e.getMessage());
                    }
                }));
        return builder;
    }

    

}
