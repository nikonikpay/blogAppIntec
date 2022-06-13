package be.intec.newreserveblog.controller;


import be.intec.newreserveblog.entity.Author;
import be.intec.newreserveblog.entity.BlogPost;
import be.intec.newreserveblog.entity.Comment;
import be.intec.newreserveblog.service.AuthorService;
import be.intec.newreserveblog.service.BlogPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class BlogPostController {

    private AuthorService authorService;
    private BlogPostService blogPostService;

    @Autowired
    public BlogPostController(AuthorService authorService, BlogPostService blogPostService) {
        this.authorService = authorService;
        this.blogPostService = blogPostService;
    }


    @GetMapping("/showPostForm")
    public String showCreatePostForm(Model model, BlogPost blogpost) {


        String currentUserName = SecurityContextHolder.getContext()
                                                      .getAuthentication()
                                                      .getName();
        Author currentAuthor = authorService.getAuthorByUsername(currentUserName);

        model.addAttribute("currentUser", currentAuthor);
        model.addAttribute("blogPost", blogpost);
        System.out.println("In Controller beginning" + blogpost);
        System.out.println("the current author is" + currentAuthor);
        return "blogPost-create-form";
    }





    @PostMapping("/create")
    public String saveBlogPost(@Valid @ModelAttribute("blogPost") BlogPost blogPost,
                               BindingResult result,
                               Model model) {

        String currentUserName = SecurityContextHolder.getContext()
                                                      .getAuthentication()
                                                      .getName();

        Author currentAuthor = authorService.getAuthorByUsername(currentUserName);

        if(result.hasErrors()) {
            model.addAttribute("blogPost", blogPost);
            return "blogPost-create-form";
        }

        blogPost.setAuthor(currentAuthor);
        blogPostService.saveBlogPost(blogPost);
        return "redirect:/";
    }


    @GetMapping("/postInDetail/{postId}")
    public String getFullPost(@PathVariable("postId") Long postId, Model model) {
        BlogPost blogPost = blogPostService.getBlogPostById(postId);

        model.addAttribute("oneBlogPost",blogPost);
        return "blogPost-in-detail";
    }


}
