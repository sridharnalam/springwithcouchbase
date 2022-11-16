package com.playground.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Document;

@Document
@Getter
@Setter
public class User {
    @Id
    private Integer id;

    private String name;

    private String email;
}
