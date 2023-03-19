package com.dev.moduleapi.event;

import com.dev.moduleapi.service.BlogPopularKeywordService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class BlogPopularKeywordHandler {
    private final BlogPopularKeywordService popularKeywordService;

    @EventListener
    @Transactional
    @Async
    public void saveBlogPopularKeyword(BlogPopularKeywordEvent event) {
        popularKeywordService.saveBlogPopularKeyword(event.getKeyword());
    }
}
