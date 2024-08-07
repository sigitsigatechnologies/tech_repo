package com.bjj.rabbitmq_scurity.model.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CollectionPageableResponse<T> {
    private List<T> data;
    private int number;
    private int size;
    private int totalPages;
    private long totalElement;
}
