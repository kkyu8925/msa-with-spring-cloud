package com.example.userservice.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL) // json null 값 전달x
public class ResponseUser {
    private String email;
    private String name;
    private String userId;

    // 사용자 주문 내역
    private List<ResponseOrder> orders;
}