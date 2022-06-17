package be.intec.newreserveblog.controller;


import be.intec.newreserveblog.entity.Author;
import be.intec.newreserveblog.entity.BlogPost;
import be.intec.newreserveblog.entity.Comment;
import be.intec.newreserveblog.entity.LikePost;
import be.intec.newreserveblog.service.AuthorService;
import be.intec.newreserveblog.service.BlogPostService;
import be.intec.newreserveblog.service.CommentService;
import be.intec.newreserveblog.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class BlogPostController {

    private AuthorService authorService;
    private BlogPostService blogPostService;
    private LikeService likeService;
    private CommentService commentService;

    @Autowired
    public BlogPostController(AuthorService authorService, BlogPostService blogPostService,LikeService likeService,CommentService commentService) {
        this.authorService = authorService;
        this.blogPostService = blogPostService;
        this.likeService=likeService;
        this.commentService =commentService;
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

        findingUser(model, authorService, likeService);

        BlogPost blogPost = blogPostService.getBlogPostById(postId);
       List<Comment> comments = commentService.getAllComments(blogPost);

        model.addAttribute("allComments",comments);
        model.addAttribute("oneBlogPost",blogPost);
        return "blogPost-in-detail";
    }

    static void findingUser(Model model, AuthorService authorService, LikeService likeService) {
        String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();
        Author currentUser = authorService.getAuthorByUsername(currentUserName);


        List<Long> postIdsLikedByCurrentUser = new ArrayList<>();

        for (LikePost likePost : likeService.getBlogPostLikes()){
            if(likePost.getAuthor().getUserName().equals(currentUserName)){
                postIdsLikedByCurrentUser.add(likePost.getBlogPost().getId());
            }
        }

        model.addAttribute("likedpostids", postIdsLikedByCurrentUser);
        model.addAttribute("currentUser", currentUser);
    }


}
