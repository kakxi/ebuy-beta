package com.kakxix.base.basic.dto;


import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 接收参数辅助
 */
@Setter
@Getter
public class ParamHelperDto extends BaseDto {

    private static final long serialVersionUID = -8186247590332156433L;

    private Long id;

    private String uId;

    private List<String> list;
}
