package com.productservice.model;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.*;

@Document(value = "product")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Product {

}
