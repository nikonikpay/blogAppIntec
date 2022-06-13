package be.intec.newreserveblog.service;

import be.intec.newreserveblog.entity.Author;
import be.intec.newreserveblog.entity.BlogPost;
import org.springframework.data.domain.Page;

import java.util.List;
public interface BlogPostService {

    void saveBlogPost(BlogPost blogPost) ;
    void updateBlogPost(BlogPost blogPost);
    void deleteBlogPost(BlogPost blogPost);
    public void deleteBlogPostById(Long blogPostId);
    BlogPost getBlogPostByObject(BlogPost blogPost);
    BlogPost getBlogPostById(Long Id);
    Page<BlogPost> getAllBlogPost(int pageNumber, String sortField, String sortDirection);

    List<BlogPost> getAllBlogPostNoPagination();



}
