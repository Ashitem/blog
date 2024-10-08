package com.ashitem.service.impl;

import com.ashitem.constants.SystemConstants;
import com.ashitem.domain.ResponseResult;
import com.ashitem.domain.entity.Article;
import com.ashitem.domain.vo.HotArticleVO;
import com.ashitem.mapper.ArticleMapper;
import com.ashitem.service.ArticleService;
import com.ashitem.utils.BeanCopyUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.databind.util.BeanUtil;
import org.apache.ibatis.javassist.expr.NewExpr;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 文章表(Article)表服务实现类
 *
 * @author makejava
 * @since 2024-10-07 22:28:56
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    @Override
    public ResponseResult hotArticleList() {
        LambdaQueryWrapper<Article> queryWrapper=new LambdaQueryWrapper<>();
        //查询热门文章，封装成ResponseResult返回。把所有查询条件写在queryWrapper里面
        //查询的不能是草稿。也就是Status字段不能是0
        queryWrapper.eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_NORMAL);
        //按照浏览量进行排序。也就是根据ViewCount字段降序排序
        queryWrapper.orderByDesc(Article::getViewCount);
        //最多只能查询出来10条消息。当前显示第一页的数据，每页显示10条数据
        Page<Article> page=new Page<>(SystemConstants.ARTICLE_STATUS_CURRENT,SystemConstants.ARTICLE_STATUS_SIZE);
        page(page, queryWrapper);
        //获取最终的查询结果，把结果封装在Article类里面会有很多不需要的字段
        List<Article> articles = page.getRecords();
        //Vo实现
        //解决: 把结果封装在HotArticleVo实体类里面，在HotArticleVo实体类只写我们要的字段
//        List<HotArticleVO> hotArticleVOS=new ArrayList<>();
//        for (Article article : articles) {
//            HotArticleVO hotArticleVO=new HotArticleVO();
//            //使用spring提供的BeanUtils类，来实现bean拷贝。第一个参数是源数据，第二个参数是目标数据，把源数据拷贝给目标数据
//            //虽然article里面有很多不同的字段，但是hotArticleVO里面只有3个字段(没有具体数据)，所以拷贝之后，就能把hotArticleVO里面的三个字段填充具体数据
//            BeanUtils.copyProperties(article,hotArticleVO);//article就是Article实体类，hotArticleVO就是HotArticleVo实体类
//            //把我们要的数据(也就是上一行的hotArticleVO)封装成集合
//            hotArticleVOS.add(hotArticleVO);
//        }
        //注释掉，使用我们定义的BeanCopyUtils工具类的copyBeanList方法。如下

        //一行搞定
        List<HotArticleVO> hotArticleVOS = BeanCopyUtils.copyBeanList(articles, HotArticleVO.class);

        return ResponseResult.okResult(hotArticleVOS);
    }
}
