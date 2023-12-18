package com.example.epa_inventory_app.infrastructure.drivenAdapters.article;

import com.example.epa_inventory_app.infrastructure.drivenAdapters.article.data.ArticleData;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface ArticleRepository extends ReactiveMongoRepository<ArticleData, String> {

}
