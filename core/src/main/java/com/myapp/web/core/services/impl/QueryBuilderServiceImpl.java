package com.myapp.web.core.services.impl;


import com.day.cq.search.PredicateGroup;
import com.day.cq.search.Query;
import com.day.cq.search.QueryBuilder;
import com.day.cq.search.result.SearchResult;
import com.myapp.web.core.services.QueryBuilderService;
import org.apache.sling.api.resource.ResourceResolver;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.jcr.Session;
import java.util.Map;

@Component(service = QueryBuilderService.class, immediate=true)
public class QueryBuilderServiceImpl implements QueryBuilderService {

    @Reference
    private QueryBuilder queryBuilder;


    @Override
    public SearchResult getResults(Map<String, String> map, ResourceResolver resourceResolver) {

        Session session = resourceResolver.adaptTo(Session.class);
        Query query = queryBuilder.createQuery(PredicateGroup.create(map),session);
        query.setStart(0);
        query.setHitsPerPage(0l);

        return query.getResult();
    }
}
