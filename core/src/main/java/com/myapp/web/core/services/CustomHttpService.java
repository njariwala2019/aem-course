package com.myapp.web.core.services;

import java.util.Map;

public interface CustomHttpService {
    <T> T makeGetCall(String url, Class<T> t, Map<String, String> headers, Map<String, String> params);
}
