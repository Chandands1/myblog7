package com.example.myblog7.controller;

import com.example.myblog7.payload.PostDto;
import com.example.myblog7.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto) {

        return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);

    }
    @GetMapping
    public List<PostDto> getAllPost(){
    return  postService.getAllPosts();

    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable(name = "id") long id) {
       return new ResponseEntity<>( postService.getPostById(id),HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updatePostById(@PathVariable (name = "id")long id, @RequestBody PostDto postDto) {

        PostDto postDto1 = postService.updatePostById(id, postDto);

        return new ResponseEntity<>(postDto1, HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public String deletePostById(@PathVariable(name = "id") long id) {

        postService.deletePostById(id);
        return "Post is deleted "+id;

    }

}
