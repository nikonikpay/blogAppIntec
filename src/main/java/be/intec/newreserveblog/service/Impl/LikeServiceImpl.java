package be.intec.newreserveblog.service.Impl;

import be.intec.newreserveblog.entity.LikePost;
import be.intec.newreserveblog.repository.LikeRepository;
import be.intec.newreserveblog.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class LikeServiceImpl implements LikeService {

    private LikeRepository likeRepository;

    @Autowired
    public LikeServiceImpl(LikeRepository likeRepository) {
        this.likeRepository = likeRepository;
    }

    @Override
    public LikePost saveBlogPostLike(LikePost likePost) {
        return likeRepository.save(likePost);
    }

    @Override
    public List<LikePost> getBlogPostLikes() {
        return likeRepository.findAll();
    }

    @Override
    public void deleteBlogPostLike(LikePost likePost) {
        likeRepository.delete(likePost);
    }

    public void deleteBlogPostLike(Long likeId) {
        likeRepository.deleteById(likeId);
    }


    @Override
    public LikePost getBlogPostLikeById(long id) {
        return null;
    }
}
