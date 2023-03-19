package com.dev.moduleapi.event;

import com.dev.moduleapi.service.BlogPopularKeywordService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

@Slf4j
@Component
@RequiredArgsConstructor
public class BlogPopularKeywordHandler {
    private final BlogPopularKeywordService popularKeywordService;

    @TransactionalEventListener
    @Async
    public void saveBlogPopularKeyword(BlogPopularKeywordEvent event){
        popularKeywordService.saveBlogPopularKeyword(event.getKeyword());
    }
}
