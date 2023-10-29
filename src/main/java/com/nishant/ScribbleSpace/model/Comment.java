package com.nishant.ScribbleSpace.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String body;

    @NotNull
    private LocalDateTime creationTime;

    @JsonIncludeProperties({"id"})
    @NotNull
    @ManyToOne(optional = false)
    private ApplicationUser commenter;


    @JsonIncludeProperties({"id"})
    @NotNull
    @ManyToOne(optional = false)
    private Post post;

    @JsonIncludeProperties({"id"})
    @ManyToOne()
    private Comment replyTo;

    public Comment(String body, ApplicationUser commenter, Post post, Comment replyTo) {
        this.body = body;
        this.commenter = commenter;
        this.post = post;
        this.replyTo = replyTo;
        this.creationTime = LocalDateTime.now();
    }
}
