package com.example.employeemanagementsys.dto.response;

import lombok.Builder;
import lombok.Getter;


@Builder
@Getter
public class ApiResponse<T> {
    private int status;       // HTTP status code: 200, 201, 400, 404, 500
    private String message;   // Mô tả ngắn gọn kết quả
    private T data;           // Payload thực sự (null nếu lỗi)

    public static <T> ApiResponse<T> success(T data) {
        return ApiResponse.<T>builder()
                .status(200)
                .message("Thành công")
                .data(data)
                .build();
    }

    public static <T> ApiResponse<T> success(int status, String message, T data) {
        return ApiResponse.<T>builder()
                .status(status)
                .message(message)
                .data(data)
                .build();
    }

    public static <T> ApiResponse<T> error(int status, String message) {
        return ApiResponse.<T>builder()
                .status(status)
                .message(message)
                .data(null)
                .build();
    }
}