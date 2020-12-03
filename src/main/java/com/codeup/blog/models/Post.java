package com.codeup.blog.models;
import javax.persistence.*;

@Entity
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String body;

    @ManyToOne
    @JoinColumn (name = "user_id")
    private User owner;

/* ------------------------------------------------------------------------------------ */

//CONSTRUCTORS
    public Post() {}

    // CREATE
    public Post(String title, String body, User owner) {
        this.title = title;
        this.body = body;
        this.owner = owner;
    }

    //READ
    public Post(Long id, String title, String body, User owner) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.owner = owner;
    }

/* ------------------------------------------------------------------------------------ */

//GETTERS AND SETTERS
    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public User getOwner() { return owner; }

    public void setOwner(User owner) { this.owner = owner; }

}
