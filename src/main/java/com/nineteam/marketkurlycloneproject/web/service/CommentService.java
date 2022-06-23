package com.nineteam.marketkurlycloneproject.web.service;

import com.nineteam.marketkurlycloneproject.domain.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;



}
