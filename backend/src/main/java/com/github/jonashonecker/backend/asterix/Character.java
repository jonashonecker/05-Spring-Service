package com.github.jonashonecker.backend.asterix;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("characters")
public record Character(
        @Id
        String id,
        String name,
        int age,
        String profession
) {
}
