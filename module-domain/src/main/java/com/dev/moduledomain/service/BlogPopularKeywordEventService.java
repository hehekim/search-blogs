package com.dev.moduledomain.service;

import com.dev.moduledomain.event.BlogPopularKeywordEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class BlogPopularKeywordEventService implements PopularKeywordEventService {
    private final ApplicationEventPublisher publisher;

    @Override
    public void saveBlogPopularKeyword(String keyword) {
        publisher.publishEvent(BlogPopularKeywordEvent.from(keyword));
    }
}
