package com.gleb.zemskoi.adverts.entity.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pagination {
    private Long pageNumber;
    private Long pageSize;
    private Long pageTotal;
    private Long total;
}
