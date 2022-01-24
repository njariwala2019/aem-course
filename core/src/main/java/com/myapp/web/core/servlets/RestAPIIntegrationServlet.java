package com.myapp.web.core.servlets;

import org.apache.sling.api.servlets.SlingSafeMethodsServlet;

import com.google.gson.Gson;
import com.myapp.web.core.services.CustomHttpService;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletResourceTypes;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.propertytypes.ServiceDescription;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component(service = {Servlet.class})
@SlingServletResourceTypes(
        resourceTypes = "sling/servlet/default",
        selectors = "weather",
        extensions = "json",
        methods = HttpConstants.METHOD_GET
)
@ServiceDescription("SAMPLE WEATHER JSON API SERVLET")
public class RestAPIIntegrationServlet extends SlingSafeMethodsServlet {

    @Reference
    private CustomHttpService customHttpService;

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        Map<String,String> params = new HashMap<>();
        params.put("q",request.getParameter("location"));
        params.put("APPID", "fcdf44219f963e2fdb76413c8535be35");

        response.getWriter().write(customHttpService.makeGetCall("https://api.openweathermap.org/data/2.5/weather",String.class,params));
        response.getWriter().close();
    }

}
