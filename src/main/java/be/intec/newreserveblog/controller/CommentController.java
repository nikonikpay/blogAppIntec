package be.intec.newreserveblog.controller;


import be.intec.newreserveblog.entity.Author;
import be.intec.newreserveblog.entity.BlogPost;
import be.intec.newreserveblog.entity.Comment;
import be.intec.newreserveblog.service.AuthorService;
import be.intec.newreserveblog.service.BlogPostService;
import be.intec.newreserveblog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Controller
public class CommentController {


    private AuthorService authorService;
    private BlogPostService blogPostService;
    private CommentService commentService;

    @Autowired
    public CommentController(AuthorService authorService,
                             BlogPostService blogPostService,
                             CommentService commentService) {
        this.authorService = authorService;
        this.blogPostService = blogPostService;
        this.commentService = commentService;
    }


    @GetMapping("/commentForm/{postId}")
    public String showCreateCommentForm(@PathVariable("postId") Long postId, Model model, Comment comment) {

        BlogPost blogPostToAssign = blogPostService.getBlogPostById(postId);
        comment.setBlogPost(blogPostToAssign);

        model.addAttribute("blogPostToAssign", blogPostToAssign);
        model.addAttribute("comment", comment);

        System.out.println("This is Comment object " + comment.getId());
        System.out.println("This is Comment object " + comment.getBlogPost());
        return "comment-create-form";
    }

    //
    //
    @PostMapping(value = "/saveComment")
    public String saveComment(@Valid @ModelAttribute Comment comment, BindingResult result) {

        System.out.println("the object is "+ comment);
        if(result.hasErrors()) {

            System.out.println("the object is "+ comment);
            //            return "redirect:/postInDetail/" + postId;
            return "redirect:/";
        }

        System.out.println("The object is :"+comment);
        commentService.saveComment(comment);

        return "redirect:/";
    }




}
