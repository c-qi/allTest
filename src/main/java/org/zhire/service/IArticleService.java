package org.zhire.service;

import org.zhire.model.Article;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author chendongzhi
 * @since 2019-04-16
 */
public interface IArticleService {

    int insert(Article article);

    Article selectById(Integer id);

    List<Article> findPage(Map param);

    String delete(Article article);

    String updateById(Article article);
}
