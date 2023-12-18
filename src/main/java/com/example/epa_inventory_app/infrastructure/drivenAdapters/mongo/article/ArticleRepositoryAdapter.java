package com.example.epa_inventory_app.infrastructure.drivenAdapters.mongo.article;

import com.example.epa_inventory_app.domain.model.article.Article;
import com.example.epa_inventory_app.domain.model.article.gateway.ArticleGateway;
import com.example.epa_inventory_app.infrastructure.drivenAdapters.mongo.article.data.ArticleData;
import lombok.RequiredArgsConstructor;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
public class ArticleRepositoryAdapter implements ArticleGateway {

    private final ArticleRepository repository;
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
        return repository
                .findById(id)
                .switchIfEmpty(Mono.error(new RuntimeException("Unable to find article with id: " + id)))
                .map(articleData -> mapper.map(articleData, Article.class));
    }

    @Override
    public Mono<Article> saveArticle(Article article) {
        return repository
                .save(mapper.map(article, ArticleData.class))
                .map(articleData -> mapper.map(articleData, Article.class));
    }

    @Override
    public Mono<Article> updateArticle(String id, Article article) {
        return repository
                .findById(id)
                .switchIfEmpty(Mono.error(new RuntimeException("Unable to find and update article with id: " + id)))
                .flatMap(articleData -> {
                    article.setId(articleData.getId());
                    return repository.save(mapper.map(article, ArticleData.class));
                })
                .map(articleData -> mapper.map(articleData, Article.class));
    }

    @Override
    public Mono<Void> deleteArticleById(String id) {
        return repository
                .findById(id)
                .switchIfEmpty(Mono.error(new RuntimeException("Unable to find and delete article with id: " + id)))
                .flatMap(articleData -> repository.deleteById(articleData.getId()));
    }
}
