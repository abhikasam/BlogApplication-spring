package com.example.backend.entities;

import lombok.Data;

@Data
public class XPagination {
    private int pageNumber=1;
    private int pageSize=10;
    private int totalPages=1;
}
