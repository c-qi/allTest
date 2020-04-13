package org.zhire.controller;

import com.github.pagehelper.PageHelper;
import org.zhire.model.Article;
import org.zhire.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.zhire.service.IArticleService;

import java.util.List;
import java.util.Map;
import java.util.Date;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author chendongzhi
 * @since 2019-04-16
 */
@RestController
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    private IArticleService articleService;

    /**
     * 通过ID查询
     *
     * @param article ID
     * @return Article
     */
    @GetMapping("/{id}")
    public R get(@RequestBody Article article) {
        articleService.selectById(article.getId());
        return R.ok();
    }


    /**
     * 分页查询信息
     *
     * @param param 分页参数
     * @return 分页对象
     */
    // 线下课报名列表
    @RequestMapping("/offlineCourseEnrollList")
    public R offlineCourseEnrollList(@RequestBody Map param) {
        // 默认第1页,每页10条
        int pageNo = Integer.parseInt(null != param.get("pageNo") ? param.get("pageNo").toString() : "1");
        int pageSize = Integer.parseInt(null != param.get("pageSize") ? param.get("pageSize").toString() : "10");
        // 分页参数
        com.github.pagehelper.Page page = PageHelper.startPage(pageNo, pageSize);
        List<Article> list = articleService.findPage(param);
        return R.ok().put("total", page.getTotal()).
                put("pageNo", page.getPageNum()).
                put("pageSize", page.getPageSize()).
                put("totalPage", page.getPages()).
                put("list", page);
    }

    /**
     * 添加
     *
     * @param article 实体
     * @return R
     */
    @PostMapping
    public R add(@RequestBody Article article) {
        articleService.insert(article);
        return R.ok("OK");
    }

    /**
     * 删除
     *
     * @param article 文章
     * @return success/false
     */
    @DeleteMapping("/{id}")
    public R delete(@RequestBody  Article article) {
        return  R.ok(articleService.delete(article));
    }

    /**
     * 编辑
     *
     * @param article 实体
     * @return success/false
     */
    @PutMapping
    public R  edit(@RequestBody Article article) {
        article.setDate(new Date());
        return  R.ok(articleService.updateById(article));
    }
}
