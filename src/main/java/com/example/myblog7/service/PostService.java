package com.example.myblog7.service;

import com.example.myblog7.payload.PostDto;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto postDto);

    List getAllPosts();

    PostDto updatePostById(long id, PostDto postDto);

    PostDto getPostById(long id);

    void deletePostById(long id);
}
