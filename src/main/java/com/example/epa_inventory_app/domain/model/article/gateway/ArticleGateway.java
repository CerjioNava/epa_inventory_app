package com.example.epa_inventory_app.domain.model.article.gateway;

import com.example.epa_inventory_app.domain.model.article.Article;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ArticleGateway {

    Flux<Article> getAllArticles();
    Mono<Article> getArticleById(String id);
    Mono<Article> saveArticle(Article article);
    Mono<Article> updateArticle(String id, Article article);
    Mono<Void> deleteArticleById(String id);

}
