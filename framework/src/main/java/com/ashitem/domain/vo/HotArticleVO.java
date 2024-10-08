package com.ashitem.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
//返回给前端特定的字段
public class HotArticleVO {
    private long id;
    //标题
    private String title;
    //访问量
    private long viewCount;
}
