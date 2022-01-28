package com.myapp.web.core.servlets;

import com.myapp.web.core.services.CustomHttpService;
import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;
import org.apache.sling.testing.mock.sling.servlet.MockSlingHttpServletRequest;
import org.apache.sling.testing.mock.sling.servlet.MockSlingHttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(AemContextExtension.class)
class RestAPIIntegrationServletTest {

    private RestAPIIntegrationServlet fixture;

    @BeforeEach
    public void setup(AemContext context) {
        CustomHttpService customHttpService = Mockito.mock(CustomHttpService.class);
        context.registerService(CustomHttpService.class,customHttpService);


        MockSlingHttpServletRequest request = context.request();
        MockSlingHttpServletResponse response = context.response();

        Map<String,String> params = new HashMap<>();
        params.put("q",request.getParameter("location"));
        params.put("APPID", "fcdf44219f963e2fdb76413c8535be35");

        Mockito.when(customHttpService.makeGetCall("https://api.openweathermap.org/data/2.5/weather",String.class,params)).thenReturn("{}");
        fixture = context.registerInjectActivateService(new RestAPIIntegrationServlet());
    }

    @Test
    void doGet(AemContext context) throws ServletException, IOException {


        MockSlingHttpServletRequest request = context.request();
        MockSlingHttpServletResponse response = context.response();
        fixture.doGet(request, response);
        assertEquals("{}", response.getOutputAsString());



    }
}