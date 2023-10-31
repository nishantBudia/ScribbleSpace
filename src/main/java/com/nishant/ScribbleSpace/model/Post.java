package com.nishant.ScribbleSpace.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(length = 10000)
    private String title;

    @NotNull
    private String body;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private List<PostGenre> postGenres;

    @NotNull
    private LocalDateTime creationTimeStamp;

    @JsonIncludeProperties({"id"})
    @NotNull
    @ManyToOne
    private ApplicationUser owner;

    public Post(String title, String body, List<PostGenre> postGenres, ApplicationUser owner) {
        this.title = title;
        this.body = body;
        this.postGenres = postGenres;
        this.owner = owner;
        this.creationTimeStamp = LocalDateTime.now();
    }
}
