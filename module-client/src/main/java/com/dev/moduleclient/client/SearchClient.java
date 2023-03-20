package com.dev.moduleclient.client;

import com.dev.moduleclient.dto.request.BlogRequest;
import com.dev.moduleclient.dto.response.BlogResponse;
import org.springframework.http.HttpEntity;

import java.net.URI;

public interface SearchClient<T extends BlogRequest, M extends BlogResponse> {
     boolean isAvailableType(SearchClientType type);
     M call(T request);
     HttpEntity<String> setHttpEntity();
     URI createURI(T request);
}
