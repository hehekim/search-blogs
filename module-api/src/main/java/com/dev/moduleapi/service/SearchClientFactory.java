package com.dev.moduleapi.service;

import com.dev.moduleapi.exception.ErrorCode;
import com.dev.moduleapi.exception.SearchApplicationException;
import com.dev.moduleclient.client.SearchClient;
import com.dev.moduleclient.client.SearchClientType;
import com.dev.moduleclient.dto.request.BlogRequest;
import com.dev.moduleclient.dto.response.BlogResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class SearchClientFactory {

    private final List<SearchClient<BlogRequest, BlogResponse>> searchClients;

    public SearchClient<BlogRequest, BlogResponse> getImplementationByType(SearchClientType action) {
        return searchClients.stream()
                .filter(e -> e.isAvailableType(action))
                .findFirst()
                .orElseThrow(() -> new SearchApplicationException(ErrorCode.SEARCH_TYPE_NOT_FOUND));
    }
}
