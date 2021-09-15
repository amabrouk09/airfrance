package com.airfrance.userDemo;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection="user")
@Data
public class User {
    @Id
    private int id;
    private String nom;
    private String prenom;
    @NotNull
    private int age;
    @NotNull
    private String pays;
}
