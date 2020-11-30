package com.codeup.blog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
class PostController {

    @GetMapping("/posts")
    @ResponseBody
    public String postsIndex() {
        return "index";
    }

    @GetMapping("/posts/{id}")
    @ResponseBody
    public String individualPost(@PathVariable Long id) {
        return "view an individual post";
    }

    @GetMapping("/posts/create")
    @ResponseBody
    public String viewCreateForm() {
        return "view the form for creating a post";
    }

    @PostMapping("/posts/create")
    @ResponseBody
    public String submitPost() {
        return "create a new post";
    }
}
