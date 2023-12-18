package com.example.epa_inventory_app.domain.usecase.article;

import com.example.epa_inventory_app.domain.model.article.Article;
import com.example.epa_inventory_app.domain.model.article.gateway.ArticleGateway;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

import java.util.function.Supplier;

@RequiredArgsConstructor
public class GetAllArticlesUseCase implements Supplier<Flux<Article>> {

    private final ArticleGateway articleGateway;

    @Override
    public Flux<Article> get() {
        System.out.println("INFO: Request to get all articles");
        return articleGateway.getAllArticles();
    }

}
