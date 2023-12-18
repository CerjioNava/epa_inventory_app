package com.example.epa_inventory_app.domain.usecase.article;

import com.example.epa_inventory_app.domain.model.article.Article;
import com.example.epa_inventory_app.domain.model.article.gateway.ArticleGateway;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.function.BiFunction;

@RequiredArgsConstructor
public class UpdateArticleUseCase implements BiFunction<String, Article, Mono<Article>> {

    private final ArticleGateway articleGateway;

    @Override
    public Mono<Article> apply(String id, Article article) {
        System.out.println("INFO: Request to update an Article by id: "+id);
        return articleGateway.updateArticle(id, article);
    }

}
