package com.example.epa_inventory_app.domain.model.article;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Article {

    private String id;
    private String name;
    private String description;
    private String brand;

}
