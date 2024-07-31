package com.example.myblog7.service.impl;

import com.example.myblog7.entity.Post;
import com.example.myblog7.exception.ResourceNotFoundException;
import com.example.myblog7.payload.PostDto;
import com.example.myblog7.repository.PostRepository;
import com.example.myblog7.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;
    @Override
    public PostDto createPost(PostDto postDto) {

        Post post = mapToEntity(postDto);


        Post savedPost = postRepository.save(post);


        return mapToDto(savedPost);
    }

    @Override
    public List getAllPosts() {

        postRepository.findAll();
        return postRepository.findAll();
    }

    @Override
    public PostDto updatePostById(long id, PostDto postDto) {

        Post post = postRepository.findById(id).orElseThrow
                (() -> new ResourceNotFoundException("Post", "id", id));

        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());
        Post saved = postRepository.save(post);
        return mapToDto(saved);
    }

    @Override
    public PostDto getPostById(long id) {

        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));


        return mapToDto(post);
    }

    @Override
    public void deletePostById(long id) {
        Post post = postRepository.findById(id).orElseThrow
                (() -> new ResourceNotFoundException("Post", "id", id));
        postRepository.delete(post);

    }

    //dto to entity
    private Post mapToEntity(PostDto postDto) {
        Post post = new Post();

        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());

        return post;

    }

    // entity to dto

    private PostDto mapToDto(Post post) {

        PostDto postDto = new PostDto();
        postDto.setId(post.getId());
        postDto.setTitle(post.getTitle());
        postDto.setDescription(post.getDescription());
        postDto.setContent(post.getContent());

        return postDto;


    }
}
