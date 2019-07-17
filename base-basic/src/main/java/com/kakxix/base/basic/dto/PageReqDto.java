package com.kakxix.base.basic.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * 分页显示，接收参数
 */
@Getter
@Setter
public class PageReqDto extends BaseDto {

    private static final long serialVersionUID = -7475242033440862195L;

    private Integer pageNum = 1;

    private Integer pageSize = 10;
}
