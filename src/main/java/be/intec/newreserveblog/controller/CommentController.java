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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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

    @PostMapping(value = "/saveComment/{postId}")
    public String saveComment(@Valid @ModelAttribute("comment") Comment comment,@PathVariable("postId") Long postId,
                              BindingResult result ) {

//        String currentUserName = SecurityContextHolder.getContext()
//                                                      .getAuthentication()
//                                                      .getName();
//        Author currentAuthor = authorService.getAuthorByUsername(currentUserName);

        BlogPost blogPostToAssign= blogPostService.getBlogPostById(postId);
        System.out.println("This is the post "+blogPostToAssign);




        if(result.hasErrors()) {

            System.out.println("Something happened "+ blogPostToAssign);
//            return "redirect:/postInDetail/" + postId;
            return "redirect:/";
        }

        comment.setBlogPost(blogPostToAssign);
        commentService.saveComment(comment);

        return "redirect:/postInDetail/" + postId;
    }


}
