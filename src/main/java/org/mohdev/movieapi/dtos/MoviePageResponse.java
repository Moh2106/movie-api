package org.mohdev.movieapi.dtos;

import lombok.Builder;

import java.util.List;

@Builder
public record MoviePageResponse(List<MovieDto> movieDtos,
                               Integer pageNumber,
                               Integer pageSize,
                               int totalElements,
                               int totalPages,
                               boolean isLast) {
}
