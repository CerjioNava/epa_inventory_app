package com.example.epa_inventory_app.infrastructure.drivenAdapters.mongo.article;

import com.example.epa_inventory_app.infrastructure.drivenAdapters.mongo.article.data.ArticleData;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface MongoArticleDBRepository extends ReactiveMongoRepository<ArticleData, String> {

}
