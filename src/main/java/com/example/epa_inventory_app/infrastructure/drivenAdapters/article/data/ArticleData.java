package com.example.epa_inventory_app.infrastructure.drivenAdapters.article.data;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@Document(collection = "Article")
public class ArticleData {

    @Id
    private String id;
    private String name;
    private String description;
    private String brand;

}
