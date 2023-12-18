package com.example.epa_inventory_app.infrastructure.entryPoints.article;

import com.example.epa_inventory_app.domain.model.article.Article;
import com.example.epa_inventory_app.domain.usecase.article.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class ArticleRouter {

    @Bean
    public RouterFunction<ServerResponse> getAllArticles(GetAllArticlesUseCase getAllArticlesUseCase) {
        return route(
                GET("/api/articles"),
                request -> ServerResponse
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(
                                getAllArticlesUseCase.get(), Article.class))
                        .onErrorResume(throwable -> ServerResponse
                                .status(HttpStatus.NO_CONTENT)
                                .build())
        );
    }

    @Bean
    public RouterFunction<ServerResponse> getArticleById(GetArticleByIdUseCase getArticleByIdUseCase) {
        return route(
                GET("/api/articles/{id}"),
                request -> ServerResponse
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(
                                getArticleByIdUseCase.apply(request.pathVariable("id")), Article.class))
                        .onErrorResume(throwable -> ServerResponse
                                .status(HttpStatus.BAD_REQUEST)
                                .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue(throwable.getMessage()))
//                request -> getArticleByIdUseCase.apply(request.pathVariable("id"))
//                        .flatMap(article -> ServerResponse
//                                .ok()
//                                .contentType(MediaType.APPLICATION_JSON)
//                                .bodyValue(article))
//                        .onErrorResume(throwable -> ServerResponse
//                                .status(HttpStatus.BAD_REQUEST)
//                                .contentType(MediaType.APPLICATION_JSON)
//                                .bodyValue(throwable.getMessage()))
        );
    }

    @Bean
    public RouterFunction<ServerResponse> saveArticle(SaveArticleUseCase saveArticleUseCase) {
        return route(
                POST("/api/articles").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(Article.class)
                        .flatMap(article ->
                                saveArticleUseCase.apply(article)
                                .flatMap(result -> ServerResponse
                                        .status(HttpStatus.CREATED)
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .bodyValue(result))
                                .onErrorResume(throwable -> ServerResponse
                                        .status(HttpStatus.BAD_REQUEST)
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .bodyValue(throwable.getMessage())))
        );
    }

    @Bean
    public RouterFunction<ServerResponse> updateArticle(UpdateArticleUseCase updateArticleUseCase) {
        return route(
                PUT("/api/articles/{id}").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(Article.class)
                        .flatMap(article ->
                                updateArticleUseCase.apply(request.pathVariable("id"), article)
                                .flatMap(result -> ServerResponse
                                        .status(HttpStatus.OK)
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .bodyValue(result))
                                .onErrorResume(throwable -> ServerResponse
                                        .status(HttpStatus.BAD_REQUEST)
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .bodyValue(throwable.getMessage())))
        );
    }

    @Bean
    public RouterFunction<ServerResponse> deleteArticle(DeleteArticleUseCase deleteArticleUseCase) {
        return route(
                DELETE("/api/articles/{id}").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(Article.class)
                        .flatMap(article ->
                                deleteArticleUseCase.apply(request.pathVariable("id"))
                                        .flatMap(result -> ServerResponse
                                                .status(HttpStatus.OK)
                                                .contentType(MediaType.APPLICATION_JSON)
                                                .bodyValue("Successfully deleted article with id: "+request.pathVariable("id")))
                                        .onErrorResume(throwable -> ServerResponse
                                                .status(HttpStatus.BAD_REQUEST)
                                                .contentType(MediaType.APPLICATION_JSON)
                                                .bodyValue(throwable.getMessage())))
        );
    }

}
