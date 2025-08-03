package vn.pvhg.socialbackend.response;

import org.springframework.http.HttpStatus;

import java.util.List;

public record ApiPagedResponse<T>(
        HttpStatus status,
        boolean success,
        String message,
        List<T> data,
        int pageNumber,
        int pageSize,
        long totalElements,
        int totalPages
) {
}
