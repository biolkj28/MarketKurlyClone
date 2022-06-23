package com.nineteam.marketkurlycloneproject.web.service;

import com.nineteam.marketkurlycloneproject.domain.model.Comment;
import com.nineteam.marketkurlycloneproject.domain.model.Products;
import com.nineteam.marketkurlycloneproject.domain.repository.CommentQueryRepository;
import com.nineteam.marketkurlycloneproject.domain.repository.CommentRepository;
import com.nineteam.marketkurlycloneproject.domain.repository.ProductRepository;
import com.nineteam.marketkurlycloneproject.security.dto.ResponseDto;
import com.nineteam.marketkurlycloneproject.security.model.User;
import com.nineteam.marketkurlycloneproject.security.repository.UserRepository;
import com.nineteam.marketkurlycloneproject.web.dto.CommentListResponseDto;
import com.nineteam.marketkurlycloneproject.web.dto.CommentRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final CommentQueryRepository queryRepository;

    @Transactional
    public ResponseEntity<ResponseDto> createComment (Long productId, CommentRequestDto commentRequestDto, String loginId){
        String title = commentRequestDto.getTitle();
        String content = commentRequestDto.getComment();
        String comment_image = commentRequestDto.getComment_image();
        User user = userRepository.findOneByLoginId(loginId).orElse(null);
        Products products = productRepository.findById(productId).orElse(null);
        Comment comment = new Comment(title,content,comment_image,user,products);

        if (products == null) {
            return checkIdAction("등록");
        }

        commentRepository.save(comment);
        return successAction("등록");
    }

    @Transactional
    public ResponseEntity<ResponseDto> updateComment (Long commentId, CommentRequestDto commentRequestDto, String loginId) {
        Comment comment = commentRepository.findById(commentId).orElse(null);
        User user = userRepository.findOneByLoginId(loginId).orElse(null);
        if (comment == null) {
            return checkIdAction("수정");
        }

        assert user != null;
        if (!user.getId().equals(comment.getUser().getId())) {
            return checkIdAction("수정");
        }

        comment.update(commentRequestDto.getTitle(),commentRequestDto.getComment(),commentRequestDto.getComment_image());
        commentRepository.save(comment);
        return successAction("수정");
    }

    @Transactional
    public ResponseEntity<ResponseDto> deleteComment (Long commentId, String loginId) {
        Comment comment = commentRepository.findById(commentId).orElse(null);
        User user = userRepository.findOneByLoginId(loginId).orElse(null);
        if (comment == null) {
            return checkIdAction("삭제");
        }

        assert user != null;
        if (!user.getId().equals(comment.getUser().getId())) {
            return checkIdAction("삭제");
        }
        commentRepository.delete(comment);
        return successAction("삭제");
    }

    @Transactional
    public List<CommentListResponseDto>getComments(Long id){
        return queryRepository.getComments(id);
    }


    public ResponseEntity<ResponseDto> checkIdAction(String action) {
        return new ResponseEntity<>(new ResponseDto(false, action + " 실패"), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<ResponseDto> successAction(String action) {
        return new ResponseEntity<>(new ResponseDto(true, action + " 성공"), HttpStatus.OK);
    }
}
