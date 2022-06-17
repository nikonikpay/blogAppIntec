package be.intec.newreserveblog.controller;


import be.intec.newreserveblog.entity.Author;
import be.intec.newreserveblog.entity.BlogPost;
import be.intec.newreserveblog.entity.LikePost;
import be.intec.newreserveblog.service.AuthorService;
import be.intec.newreserveblog.service.BlogPostService;
import be.intec.newreserveblog.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class LikeController {


    private AuthorService authorService;
    private BlogPostService blogPostService;
    private LikeService likeService;

    @Autowired
    public LikeController(AuthorService authorService, BlogPostService blogPostService, LikeService likeService) {
        this.authorService = authorService;
        this.blogPostService = blogPostService;
        this.likeService = likeService;
    }

    @GetMapping("/create/post/{postId}")
    public String showBlogPostLikeCreateForm(@PathVariable("postId") Long postId, LikePost likePost, Model model) {
        BlogPost blogpost = blogPostService.getBlogPostById(postId);

        String currentUserName = SecurityContextHolder.getContext()
                                                      .getAuthentication()
                                                      .getName();
        Author currentUser = authorService.getAuthorByUsername(currentUserName);
        BlogPost currentPost = blogPostService.getBlogPostById(postId);
        model.addAttribute("currentUser", currentUser);
        model.addAttribute("blogpost", blogpost);
        likePost.setAuthor(currentUser);
        likePost.setBlogPost(currentPost);

        //check if suer liked the post already
        for(LikePost postLikePost : likeService.getBlogPostLikes()) {

            if(postLikePost.getBlogPost()
                           .getId()
                           .equals(currentPost.getId()) && postLikePost.getAuthor()
                                                                       .getId() == (currentUser.getId())) {
                return "redirect:/";
            }
        }
        likeService.saveBlogPostLike(likePost);
        return "redirect:/";
    }

    @GetMapping("/delete/post/{postId}")
    public String deleteBlogPostLike(@PathVariable("postId") Long postId) {
        for(LikePost like : likeService.getBlogPostLikes()) {
            if(like.getAuthor()
                   .getUserName()
                   .equals(SecurityContextHolder.getContext()
                                                .getAuthentication()
                                                .getName()) && like.getBlogPost()
                                                                   .getId()
                                                                   .equals(postId)) {
                likeService.deleteBlogPostLike(like.getId());
            }


            //        for(LikePost like : likeService.getBlogPostLikes()) {
            //            if(like.getAuthor()
            //                   .getUserName()
            //                   .equals(SecurityContextHolder.getContext()
            //                                                .getAuthentication()
            //                                                .getName()) && like.getBlogPost()
            //                                                                   .getId()
            //                                                                   .equals(postId)) {
            //                LikePost likee = likeService.getBlogPostLikeById(like.getId());
            //               likeService.deleteBlogPostLike(likee);
            //            }
            //        }

        }
        return "redirect:/";

    }
}
