package com.nineteam.marketkurlycloneproject.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.List;

@Getter
@Setter
public class TopCategoryMap {
    HashMap<String, List<String>> categoryMap;
    HashMap<String, Long> topCategoryMap;
}
