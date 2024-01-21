package com.springboot.blog.service.impl;

import com.springboot.blog.entity.Comment;
import com.springboot.blog.entity.Post;
import com.springboot.blog.exception.BlogAPIException;
import com.springboot.blog.exception.ResourceNotFoundException;
import com.springboot.blog.payload.CommentDto;
import com.springboot.blog.repository.CommentRepository;
import com.springboot.blog.repository.PostRepository;
import com.springboot.blog.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    private CommentRepository commentRepository;

    private PostRepository postRepository;

    public CommentServiceImpl(CommentRepository commentRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }

    private CommentDto mapToDto(Comment comment){
        CommentDto commentDto = new CommentDto();
        commentDto.setBody(comment.getBody());
        commentDto.setId(comment.getId());
        commentDto.setEmail(comment.getEmail());
        commentDto.setName(comment.getName());
        return  commentDto;
    }

    private Comment mapToComment(CommentDto commentDto){
        Comment comment = new Comment();
        comment.setBody(commentDto.getBody());
        comment.setEmail(commentDto.getEmail());
        comment.setId(commentDto.getId());
        comment.setName(commentDto.getName());

        return comment;
    }
    @Override
    public CommentDto createComment(long postId, CommentDto commentDto) {

        Comment comment = mapToComment(commentDto);

        //retrieve post entity by id;
        Post post = postRepository.findById(postId).orElseThrow(
                ()-> new ResourceNotFoundException("Post","id",postId));

        //set post to comment entity
        comment.setPost(post);

        //save comment entity to db
        Comment newComment = commentRepository.save(comment);

        return mapToDto(newComment);





    }

    @Override
    public List<CommentDto> getCommentsByPostId(long postId) {
        //retrieve comments by post id

        List<Comment> comments = commentRepository.findByPostId(postId);

        //converting list of comments to commentDto
        return comments.stream().map(comment -> mapToDto(comment)).collect(Collectors.toList());
    }

    @Override
    public CommentDto getCommentById(long postId, long commentId) {

        //retrieve post by id
        Post post = postRepository.findById(postId).orElseThrow(
                ()->new ResourceNotFoundException("Post","id",postId));

        //retrieve comment by id
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                ()->new ResourceNotFoundException("Comment","id",commentId));

        if(!comment.getPost().getId().equals(post.getId())){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST,"Comment does not belong to a post");
        }
        return mapToDto(comment);

    }

    @Override
    public CommentDto updateComment(long postId, long commentId, CommentDto commentDto) {
        //retrieve post by id
        Post post = postRepository.findById(postId).orElseThrow(
                ()->new ResourceNotFoundException("Post","id",postId));

        //retrieve comment by id
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                ()->new ResourceNotFoundException("Comment","id",commentId));

        if(!comment.getPost().getId().equals(post.getId())){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST,"Comment does not belongs to post");
        }

        comment.setName(commentDto.getName());
        comment.setBody(commentDto.getBody());
        comment.setEmail(commentDto.getEmail());

        Comment updatedComment = commentRepository.save(comment);

        return mapToDto(updatedComment);

    }

    @Override
    public void deleteComment(long postId, long commentId) {
        //retrieve post by id
        Post post = postRepository.findById(postId).orElseThrow(
                ()->new ResourceNotFoundException("Post","id",postId));

        //retrieve comment by id
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                ()->new ResourceNotFoundException("Comment","id",commentId));

        if(!comment.getPost().getId().equals(post.getId())){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST,"Comment does not belongs to post");
        }

        commentRepository.delete(comment);

    }
}
