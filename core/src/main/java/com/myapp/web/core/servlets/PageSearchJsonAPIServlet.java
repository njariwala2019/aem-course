package com.myapp.web.core.servlets;

import com.day.cq.search.result.Hit;
import com.day.cq.search.result.SearchResult;
import com.google.gson.Gson;
import com.myapp.web.core.services.QueryBuilderService;
import lombok.extern.slf4j.Slf4j;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletResourceTypes;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.propertytypes.ServiceDescription;

import javax.jcr.RepositoryException;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static com.day.cq.wcm.api.NameConstants.NT_PAGE;


@Component(service = {Servlet.class})
@SlingServletResourceTypes(
        resourceTypes = "sling/servlet/default",
        selectors = "query123",
        extensions = "json",
        methods = HttpConstants.METHOD_GET
)
@ServiceDescription("Search API")
@Slf4j
public class PageSearchJsonAPIServlet  extends SlingSafeMethodsServlet {

    @Reference
    private QueryBuilderService queryBuilderService;

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        Gson gson = new Gson();
        Map<String,String> map = new HashMap<>();
        map.put("path", "/content/myAppWeb/us/en");
        map.put("type", NT_PAGE);
        map.put("fulltext","Contact");
        SearchResult result = queryBuilderService.getResults(map,request.getResourceResolver());
        List<String> paths = new ArrayList<>();

        try {
            for (Hit hit : result.getHits()) {
                paths.add(hit.getPath());
            }
        }catch (RepositoryException e){
            log.error(e.getMessage());
        }

        response.getWriter().write(gson.toJson(paths));
        response.getWriter().close();


    }
}
