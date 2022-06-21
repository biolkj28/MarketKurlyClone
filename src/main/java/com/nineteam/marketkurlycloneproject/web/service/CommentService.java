package com.nineteam.marketkurlycloneproject.web.service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.nineteam.marketkurlycloneproject.domain.model.Comment;
import com.nineteam.marketkurlycloneproject.domain.model.Products;
import com.nineteam.marketkurlycloneproject.domain.repository.CommentRepository;
import com.nineteam.marketkurlycloneproject.domain.repository.ProductsRepository;
import com.nineteam.marketkurlycloneproject.security.model.User;
import com.nineteam.marketkurlycloneproject.security.repository.UserRepository;
import com.nineteam.marketkurlycloneproject.web.dto.CommentRequestDto;
import com.nineteam.marketkurlycloneproject.web.dto.CommentResponseDto;
import com.nineteam.marketkurlycloneproject.web.dto.FileDataDto;
import lombok.RequiredArgsConstructor;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;



@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final ProductsRepository productsRepository;

    @Value("${S3Bucket}")
    private String S3Bucket;

    @Autowired
    AmazonS3Client amazonS3Client;

    public List<CommentResponseDto> getComment(Long productsId) {

        List<CommentResponseDto> commentResponseDtoList = new ArrayList<>();

        Products products = productsRepository.findById(productsId).orElseThrow(
                () -> new IllegalArgumentException("유효하지 않는 상품입니다."));

        List<Comment> commentList = commentRepository.findAllByProductsId(productsId);

        for(Comment comments : commentList){

            CommentResponseDto commentResponseDto = new CommentResponseDto(comments);

            commentResponseDtoList.add(commentResponseDto);
        }

        return commentResponseDtoList;
    }

    @Transactional
    public Comment creatComment(CommentRequestDto commentRequestDto, MultipartFile multipartFile, UserDetails userDetails) {
        User user = userRepository.findOneByLoginId(userDetails.getUsername()).orElseThrow(
                ()-> new IllegalArgumentException("유효하지 않은 아이디입니다."));

        Long productsId = commentRequestDto.getProducts().getId();

        Products products = productsRepository.findById(productsId).orElseThrow(
                ()-> new IllegalArgumentException("유효하지 않은 상품입니다."));

        FileDataDto fileDataDto = saveImage(multipartFile);
        commentRequestDto.setCommentImg(fileDataDto.getImagePath());
        commentRequestDto.setFileName(fileDataDto.getImageName());


        Comment comment = new Comment(commentRequestDto, products, user);
        commentRepository.save(comment);

        return comment;
    }

    @Transactional
    public void updateComment(Long commentId, CommentRequestDto commentRequestDto) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new IllegalArgumentException("유효하지 않는 댓글입니다."));

        commentRequestDto.setCommentImg(comment.getCommentImg());
        commentRequestDto.setFileName(comment.getFileName());
        comment.update(commentRequestDto);
    }

    @Transactional
    public void deleteComment(Long commentId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 댓글입니다."));

        amazonS3Client.deleteObject(S3Bucket,comment.getFileName());

        commentRepository.deleteById(commentId);
    }

    @Transactional
    public FileDataDto saveImage(MultipartFile multipartFile) {
        // 파일 이름
        String originalName = DateTime.now().toString().replaceAll("[+:]",".")+multipartFile.getOriginalFilename();
        // 파일 크기
        long size = multipartFile.getSize();

        ObjectMetadata objectMetaData = new ObjectMetadata();
        objectMetaData.setContentType(multipartFile.getContentType());
        objectMetaData.setContentLength(size);


        try {
            amazonS3Client.putObject(
                    new PutObjectRequest(S3Bucket, originalName, multipartFile.getInputStream(), objectMetaData)
                            .withCannedAcl(CannedAccessControlList.PublicRead)
            );
        } catch (IOException e) {
            throw new RuntimeException("사진 저장 오류");
        }
        FileDataDto fileDataDto = new FileDataDto();
        // 접근가능한 URL 가져오기
        String imagePath = amazonS3Client.getUrl(S3Bucket, originalName).toString();
        fileDataDto.setImageName(originalName);
        fileDataDto.setImagePath(imagePath);

        return fileDataDto;
    }

}
