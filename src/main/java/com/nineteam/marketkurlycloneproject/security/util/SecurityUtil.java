//package com.nineteam.marketkurlycloneproject.security.util;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import java.util.Optional;
//
//public class SecurityUtil {
//
//    private static final Logger logger = LoggerFactory.getLogger(SecurityUtil.class);
//
//    private SecurityUtil() {
//    }
//    // SecurityContext 에서 Authentication 객체를 꺼내서 Authentication 객체를 이용해 loginId를 리턴해주는 메소드
//    public static Optional<String> getCurrentLoginId() {
//        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//
//        if (authentication == null) {
//            logger.debug("Security Context에 인증 정보가 없습니다.");
//            return Optional.empty();
//        }
//
//        String loginId = null;
//        if (authentication.getPrincipal() instanceof UserDetails) {
//            UserDetails springSecurityUser = (UserDetails) authentication.getPrincipal();
//            loginId = springSecurityUser.getUsername();
//        } else if (authentication.getPrincipal() instanceof String) {
//            loginId = (String) authentication.getPrincipal();
//        }
//
//        return Optional.ofNullable(loginId);
//    }
//}