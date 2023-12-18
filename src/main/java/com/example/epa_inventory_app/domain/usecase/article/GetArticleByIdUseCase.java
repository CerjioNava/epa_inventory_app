package com.example.epa_inventory_app.domain.usecase.article;

import com.example.epa_inventory_app.domain.model.article.Article;
import com.example.epa_inventory_app.domain.model.article.gateway.ArticleGateway;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@RequiredArgsConstructor
public class GetArticleByIdUseCase implements Function<String, Mono<Article>> {

    private final ArticleGateway articleGateway;

    @Override
    public Mono<Article> apply(String id) {
        System.out.println("INFO: Request to get an Article by id: "+id);
        return articleGateway.getArticleById(id);
    }

}
