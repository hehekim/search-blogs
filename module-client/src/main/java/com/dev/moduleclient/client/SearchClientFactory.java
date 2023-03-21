package com.dev.moduleclient.client;

import com.dev.moduleclient.dto.request.BlogRequest;
import com.dev.moduleclient.dto.response.BlogResponse;
import com.dev.moduleclient.dto.response.ClientResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class SearchClientFactory {
    private final List<SearchClient<BlogRequest, BlogResponse>> searchClients;

    public ClientResponse<SearchClient<BlogRequest, BlogResponse>> getImplementationByType(SearchClientType action) {
        for (SearchClient<BlogRequest, BlogResponse> searchClient : searchClients) {
            if (searchClient.isAvailableType(action)) {
                return ClientResponse.success(searchClient);
            }
        }
        return ClientResponse.failed(new Exception());
    }
}
