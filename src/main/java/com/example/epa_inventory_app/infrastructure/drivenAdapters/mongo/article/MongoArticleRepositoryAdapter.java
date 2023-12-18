package com.example.epa_inventory_app.infrastructure.drivenAdapters.mongo.article;

import com.example.epa_inventory_app.domain.model.article.Article;
import com.example.epa_inventory_app.domain.model.article.gateway.ArticleGateway;
import lombok.RequiredArgsConstructor;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
public class MongoArticleRepositoryAdapter implements ArticleGateway {

    private final MongoArticleDBRepository repository;
    private final ObjectMapper mapper;

    @Override
    public Flux<Article> getAllArticles() {
        return repository
                .findAll()
                .switchIfEmpty(Flux.empty())
                .map(articleData -> mapper.map(articleData, Article.class));
    }

    @Override
    public Mono<Article> getArticleById(String id) {
        return null;
    }

    @Override
    public Mono<Article> saveArticle(Article article) {
        return null;
    }

    @Override
    public Mono<Article> updateArticle(Article article) {
        return null;
    }

    @Override
    public Mono<Article> deleteArticleById(String id) {
        return null;
    }
}
