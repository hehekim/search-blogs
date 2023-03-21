package com.dev.moduledomain.event;

import com.dev.moduledomain.service.PopularKeywordService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class BlogPopularKeywordHandler {
    private final PopularKeywordService popularKeywordService;

    @EventListener
    @Transactional
    @Async
    public void saveBlogPopularKeyword(BlogPopularKeywordEvent event) {
        popularKeywordService.addPopularKeywordToOneCount(event.getKeyword());
    }
}
