package com.codeup.blog.controllers;

import com.codeup.blog.services.EmailService;
import com.codeup.blog.models.User;
import com.codeup.blog.repos.PostRepository;
import com.codeup.blog.models.Post;
import com.codeup.blog.repos.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
class PostController {

    // These two next steps are often called dependency injection, where we create a Repository instance and initialize it in the controller class constructor.
    private final PostRepository postDao;
    private final UserRepository userDao;
    private final EmailService emailService;

    public PostController(PostRepository postDao, UserRepository userDao, EmailService emailService) {
        this.postDao = postDao;
        this.userDao = userDao;
        this.emailService = emailService;
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
    public String viewCreateForm(Model viewModel) {
        viewModel.addAttribute("post", new Post());
        return "posts/create";
    }

    @PostMapping("/posts/create")
    public String createPost(@ModelAttribute Post postToBeSaved) {
        User owner = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        postToBeSaved.setOwner(owner);
        Post dbPost = postDao.save(postToBeSaved);
        emailService.prepareAndSend(dbPost,"A new post has been created!", "You can find the post at id" + dbPost.getId());
        return "redirect:/posts/"+ dbPost.getId();
    }

    @GetMapping("/posts/{id}/edit")
    public String viewEditForm(@PathVariable Long id, Model viewModel) {
        viewModel.addAttribute("post", postDao.getOne(id));
        return "/posts/edit";
    }

    @PostMapping("/posts/{id}/edit")
    public String updatePost(@PathVariable Long id, @ModelAttribute Post postToBeUpdated) {
        User owner = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        postToBeUpdated.setOwner(owner);
        postDao.save(postToBeUpdated);
        return "redirect:/posts/" + postToBeUpdated.getId();
    }

    @PostMapping("posts/{id}/delete")
    public String deletePost(@PathVariable Long id) {
        Post post = postDao.getOne(id);
        postDao.delete(post);
        return "redirect:/posts";
    }
}
