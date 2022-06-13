package be.intec.newreserveblog.controller;

import be.intec.newreserveblog.entity.Author;
import be.intec.newreserveblog.entity.BlogPost;
import be.intec.newreserveblog.service.AuthorService;
import be.intec.newreserveblog.service.BlogPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
@Controller
public class HomeController {

    private AuthorService authorService;
    private BlogPostService blogPostService;

    @Autowired
    public HomeController(AuthorService authorService, BlogPostService blogPostService) {
        this.authorService = authorService;
        this.blogPostService = blogPostService;
    }

    @GetMapping("/")
    public String homePage(Model model) {

        return blogPostListByPage(1,"title","asc",model);
    }


    @GetMapping("/page/{pageNumber}")
    public String blogPostListByPage(@PathVariable("pageNumber") int currentPage,
                             @Param("sortField") String sortField,
                             @Param("sortDirection") String sortDirection,
                             Model model) {

        Page<BlogPost> pagedBlogPost = blogPostService.getAllBlogPost(currentPage, sortField, sortDirection);
        List<BlogPost> blogPostFromDb = pagedBlogPost.getContent();
        long totalBlogPost = pagedBlogPost.getTotalElements();
        int totalPages = pagedBlogPost.getTotalPages();

        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalBlogPost", totalBlogPost);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("blogPosts", blogPostFromDb);
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDirection", sortDirection);
        model.addAttribute("reverseSortDir", sortDirection.equals("asc") ? "desc" : "asc");
        return "index";
    }


    //    @GetMapping("/login")
    //    public String showLoginForm(Model model) {
    //
    //        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    //        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
    //            return "login";
    //        }
    //
    //        return "redirect:/";
    //    }


}
