package com.example.epa_inventory_app.application.config;

import com.example.epa_inventory_app.domain.model.article.gateway.ArticleGateway;
import com.example.epa_inventory_app.domain.usecase.article.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ArticleUseCaseConfig {

    @Bean
    public GetAllArticlesUseCase getAllArticlesUseCase(ArticleGateway articleGateway) {
        return new GetAllArticlesUseCase(articleGateway);
    }

    @Bean
    public GetArticleByIdUseCase getArticleByIdUseCase(ArticleGateway articleGateway) {
        return new GetArticleByIdUseCase(articleGateway);
    }

    @Bean
    public SaveArticleUseCase saveArticleUseCase(ArticleGateway articleGateway) {
        return new SaveArticleUseCase(articleGateway);
    }

    @Bean
    public UpdateArticleUseCase updateArticleUseCase(ArticleGateway articleGateway) {
        return new UpdateArticleUseCase(articleGateway);
    }

    @Bean
    public DeleteArticleUseCase deleteArticleUseCase(ArticleGateway articleGateway) {
        return new DeleteArticleUseCase(articleGateway);
    }

}
