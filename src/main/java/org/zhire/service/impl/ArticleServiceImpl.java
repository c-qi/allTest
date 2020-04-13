package org.zhire.service.impl;

import org.zhire.mapper.ArticleMapper;
import org.zhire.model.Article;
import org.zhire.service.IArticleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author chenqi
 * @since 2019-04-16
 */
@Service
public class ArticleServiceImpl implements IArticleService {
    @Resource
    private ArticleMapper articleMapper;

    @Override
    public int insert(Article article) {
        return articleMapper.insert(article);
    }

    @Override
    public Article selectById(Integer id) {
        return articleMapper.selectById(id);
    }

    @Override
    public List<Article> findPage(Map param) {
        return articleMapper.selectByMap(param);
    }

    @Override
    public String delete(Article article) {
        articleMapper.deleteById(article.getId());
        return "ok";
    }

    @Override
    public String updateById(Article article) {
        int i = articleMapper.updateById(article);
        return "ok";
    }
}
