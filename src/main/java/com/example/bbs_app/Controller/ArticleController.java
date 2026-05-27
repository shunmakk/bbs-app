package com.example.bbs_app.Controller;


import com.example.bbs_app.Repository.ArticleRepository;
import com.example.bbs_app.domain.Article;
import jakarta.servlet.ServletContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * 掲示板アプリの表示ロジックを記載するコントローラー.
 */
@Controller
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ServletContext application;

    @Autowired
    private ArticleRepository articleRepository;

    /**
     * 初期表示.
     *
     * @return 掲示板の画面
     */
    @GetMapping("/")
    public String index() {
        List<Article> articleList = articleRepository.findAll();
        application.setAttribute("articleList", articleList);
        return "bbs";
    }

}
