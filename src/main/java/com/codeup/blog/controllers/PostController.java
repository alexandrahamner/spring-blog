package com.codeup.blog.controllers;

import com.codeup.blog.PostRepository;
import com.codeup.blog.models.Post;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
class PostController {

    // These two next steps are often called dependency injection, where we create a Repository instance and initialize it in the controller class constructor.
    private final PostRepository postDao;

    public PostController(PostRepository postDao) {
        this.postDao = postDao;
    }

    @GetMapping("/posts")
    public String postsIndex(Model viewModel) {
        viewModel.addAttribute("posts", postDao.findAll());
        return "posts/index";
    }

    @GetMapping("/posts/{id}")
    public String individualPost(@PathVariable Long id, Model viewModel) {
        viewModel.addAttribute("post", postDao.getOne(id));
        return "posts/show";
    }

    @GetMapping("/posts/create")
    @ResponseBody
    public String viewCreateForm() {
        return "view the form for creating a post";
    }

    @PostMapping("/posts/create")
    @ResponseBody
    public String submitPost() {
        Post postTest = new Post("CSS Grid How-to", "lorem ipsum stuff");
        Post dbPost = postDao.save(postTest);
        return "create a new post with the id: " + dbPost.getId();
    }
}
