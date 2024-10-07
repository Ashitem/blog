package com.ashitem.service.impl;

import com.ashitem.domain.entity.Article;
import com.ashitem.mapper.ArticleMapper;
import com.ashitem.service.ArticleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 文章表(Article)表服务实现类
 *
 * @author makejava
 * @since 2024-10-07 22:28:56
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

}
