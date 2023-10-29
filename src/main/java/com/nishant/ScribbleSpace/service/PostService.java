package com.nishant.ScribbleSpace.service;

import com.nishant.ScribbleSpace.model.Post;
import com.nishant.ScribbleSpace.repo.PostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PostService {
    @Autowired
    PostRepo postRepo;

    public Post createPost(Post post){
        return postRepo.save(post);
    }

    public Post getPostById(Long id){
        return postRepo.findById(id).orElseThrow();
    }

    public Post updatePost(Post post) {
        return postRepo.save(post);
    }

    public boolean isMyPost(Long PostId, Long OwnerId) {
        return postRepo.existsByOwner_IdAndId(OwnerId,PostId);
    }

    @Transactional
    public String deletePost(Long id) {
        postRepo.deleteById(id);
        return "Success";
    }

    public List<Post> getPostsByGenrePaginated(String genre, Integer pageNumber) {
        return postRepo.findByPostGenres_GenreOrderByCreationTimeStampAsc(genre, PageRequest.of(pageNumber,10));
    }

    public List<Post> getPostsForUsersPaginated(List<Long> followingIds, Integer pageNumber) {
        return postRepo.findByOwner_IdIn(followingIds,PageRequest.of(pageNumber,10));
    }
}
