package be.intec.newreserveblog.service.Impl;

import be.intec.newreserveblog.entity.Author;
import be.intec.newreserveblog.entity.BlogPost;
import be.intec.newreserveblog.repository.BlogPostRepository;
import be.intec.newreserveblog.service.BlogPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogPostServiceImpl implements BlogPostService {

    private BlogPostRepository blogPostRepository;


    @Autowired
    public BlogPostServiceImpl(BlogPostRepository blogPostRepository) {
        this.blogPostRepository = blogPostRepository;
    }

    @Override
    public void saveBlogPost(BlogPost blogPost) {
        blogPostRepository.save(blogPost);
    }

    @Override
    public void updateBlogPost(BlogPost blogPost) {

    }

    @Override
    public void deleteBlogPost(BlogPost blogPost) {

    }

    @Override
    public void deleteBlogPostById(Long blogPostId) {

    }

    @Override
    public BlogPost getBlogPostByObject(BlogPost blogPost) {
        return null;
    }

    @Override
    public BlogPost getBlogPostById(Long Id) {
        return blogPostRepository.findById(Id).get();
    }

    @Override
    public Page<BlogPost> getAllBlogPost(int pageNumber, String sortField, String sortDirection) {
        Sort sort = Sort.by("title");
        sort = sortDirection.equals("asc") ? sort.ascending() : sort.descending();
        Pageable pageable = PageRequest.of(pageNumber - 1, 2, sort);
        return blogPostRepository.findAll(pageable);
    }

    @Override
    public List<BlogPost> getAllBlogPostNoPagination() {
        return blogPostRepository.findAll();
    }
}
