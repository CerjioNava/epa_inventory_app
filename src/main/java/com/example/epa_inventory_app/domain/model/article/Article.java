package com.example.epa_inventory_app.domain.model.article;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Article {

    private String id;
    private String name;
    private String description;
    private String brand;

}
