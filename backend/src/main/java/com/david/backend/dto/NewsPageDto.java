package com.david.backend.dto;

import java.util.List;

import com.david.backend.entity.News;

public record NewsPageDto(List<News> news, long totalElements, int totalPages) {

}
