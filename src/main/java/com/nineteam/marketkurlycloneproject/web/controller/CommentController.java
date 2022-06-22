package com.nineteam.marketkurlycloneproject.web.controller;

import com.nineteam.marketkurlycloneproject.domain.model.Comment;
import com.nineteam.marketkurlycloneproject.web.dto.CommentRequestDto;
import com.nineteam.marketkurlycloneproject.web.dto.CommentResponseDto;
import com.nineteam.marketkurlycloneproject.web.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @GetMapping("/comments/{productsId}")
    public ResponseEntity<List<CommentResponseDto>> getComment(@PathVariable Long productsId) {

        return new ResponseEntity<>(commentService.getComment(productsId), HttpStatus.OK);
    }

    @PostMapping(value = "/comments/add", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<Comment> createComment(@RequestPart CommentRequestDto commentRequestDto, @RequestPart MultipartFile multipartFile,
                                                 @AuthenticationPrincipal UserDetails userDetails) {

        return new ResponseEntity<>(commentService.creatComment(commentRequestDto, multipartFile, userDetails), HttpStatus.OK);
    }

    @PutMapping("/comment/{commentId}")
    public ResponseEntity<Long> updateComment(@PathVariable Long commentId, @RequestBody CommentRequestDto commentRequestDto,
                                              @AuthenticationPrincipal UserDetails userDetails) {
        commentService.updateComment(commentId, commentRequestDto, userDetails);
        return new ResponseEntity<>(commentRequestDto.getCommentId(), HttpStatus.OK);
    }

    @DeleteMapping("/comment/{commentId}")
    public ResponseEntity<Long> deleteComment(@PathVariable Long commentId, @AuthenticationPrincipal UserDetails userDetails) {
        commentService.deleteComment(commentId, userDetails);
        return new ResponseEntity<>(commentId, HttpStatus.OK);
    }
}
