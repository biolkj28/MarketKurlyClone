package com.nineteam.marketkurlycloneproject.web.controller;

import com.nineteam.marketkurlycloneproject.security.dto.ResponseDto;
import com.nineteam.marketkurlycloneproject.web.dto.CommentRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {

    @PostMapping("/add")
    public ResponseEntity<ResponseDto> createComment(@PathVariable Long productId,
                                                     @RequestBody CommentRequestDto commentRequestDto,
                                                     @AuthenticationPrincipal UserDetails userDetails) {


        return commentService.createComment
    }

}
