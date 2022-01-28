package com.myapp.web.core.servlets;

import com.day.cq.commons.Externalizer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletResourceTypes;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.propertytypes.ServiceDescription;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component(service = {Servlet.class})
@SlingServletResourceTypes(
        resourceTypes = "sling/servlet/default",
        selectors = "myselector",
        extensions = "json",
        methods = HttpConstants.METHOD_GET
)
@ServiceDescription("SAMPLE JSON API SERVLET")
public class JsonAPIServlet extends SlingSafeMethodsServlet {

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {

        Externalizer externalizer = request.getResourceResolver().adaptTo(Externalizer.class);
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        Gson gson = new GsonBuilder().create();
        Map<String,String> map = new HashMap<>();
        map.put("name","Nikunj Jariwala");
        map.put("domain", "https://www.donotstoplearning.com");


        map.put("local", externalizer.externalLink(request.getResourceResolver(), Externalizer.LOCAL, "/content/myappweb/us/en")+".html");
        map.put("author", externalizer.authorLink(request.getResourceResolver(),"/content/myappweb/us/en")+".html");
        map.put("publish", externalizer.publishLink(request.getResourceResolver(),"/content/myappweb/us/en")+".html");

        response.getWriter().write(gson.toJson(map));
        response.getWriter().close();
    }
}
