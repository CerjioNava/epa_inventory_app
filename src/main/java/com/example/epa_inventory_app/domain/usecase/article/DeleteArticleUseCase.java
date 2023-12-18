package com.example.epa_inventory_app.domain.usecase.article;

import com.example.epa_inventory_app.domain.model.article.gateway.ArticleGateway;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@RequiredArgsConstructor
public class DeleteArticleUseCase implements Function<String, Mono<Void>> {

    private final ArticleGateway articleGateway;

    @Override
    public Mono<Void> apply(String id) {
        return articleGateway.deleteArticleById(id);
    }
    
}
