package com.nineteam.marketkurlycloneproject.web.controller;

import com.nineteam.marketkurlycloneproject.security.dto.ResponseDto;
import com.nineteam.marketkurlycloneproject.web.dto.CommentListResponseDto;
import com.nineteam.marketkurlycloneproject.web.dto.CommentRequestDto;
import com.nineteam.marketkurlycloneproject.web.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {

    private final CommentService commentService;

    @GetMapping("/list/{productId}")
    public ResponseEntity<List<CommentListResponseDto>> getComments(@PathVariable Long productId){
        return new ResponseEntity<>(commentService.getComments(productId), HttpStatus.OK);
    }

    @PostMapping("/{productId}/add")
    public ResponseEntity<ResponseDto> createComment(@PathVariable Long productId,
                                                     @RequestBody CommentRequestDto commentRequestDto,
                                                     @AuthenticationPrincipal UserDetails userDetails) {
        String loginId = userDetails.getUsername();
        return commentService.createComment(productId, commentRequestDto, loginId);
    }

    @PutMapping("/{commentId}")
    public ResponseEntity<ResponseDto> updateComment(@PathVariable Long commentId,
                                                     @RequestBody CommentRequestDto commentRequestDto,
                                                     @AuthenticationPrincipal UserDetails userDetails) {
        String loginId = userDetails.getUsername();
        return commentService.updateComment(commentId, commentRequestDto,loginId);
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<ResponseDto> deleteComment(@PathVariable Long commentId, @AuthenticationPrincipal UserDetails userDetails) {
        String loginId = userDetails.getUsername();
        return commentService.deleteComment(commentId,loginId);
    }

}
