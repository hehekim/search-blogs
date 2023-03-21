package com.dev.moduleclient.client;

import com.dev.moduleclient.dto.request.BlogRequest;
import com.dev.moduleclient.dto.response.BlogResponse;
import com.dev.moduleclient.dto.response.ClientResponse;
import org.springframework.http.HttpEntity;

import java.net.URI;

public interface SearchClient<T extends BlogRequest, M extends BlogResponse> {
     boolean isAvailableType(SearchClientType type);
     ClientResponse<M> call(T request);
     HttpEntity<String> setHttpEntity();
     URI createURI(T request);
     Integer setRequestPage(Integer page);
     Integer setRequestSize(Integer size);
}
