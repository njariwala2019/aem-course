package com.myapp.web.core.services;

import com.day.cq.search.result.SearchResult;
import org.apache.sling.api.resource.ResourceResolver;

import java.util.Map;

public interface QueryBuilderService {
    SearchResult getResults(Map<String, String> map, ResourceResolver resourceResolver);
}
