package be.intec.newreserveblog.service;

import be.intec.newreserveblog.entity.LikePost;

import java.util.List;



public interface LikeService {


     LikePost saveBlogPostLike(LikePost likePost);
     List<LikePost> getBlogPostLikes();
    void deleteBlogPostLike(LikePost likePost);
    void deleteBlogPostLike(Long likeId);
    LikePost getBlogPostLikeById(long id);


}
