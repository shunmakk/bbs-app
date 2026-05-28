package com.example.bbs_app.Controller;


import com.example.bbs_app.Form.ArticleForm;
import com.example.bbs_app.Form.CommentForm;
import com.example.bbs_app.Repository.ArticleRepository;
import com.example.bbs_app.Repository.CommentRepository;
import com.example.bbs_app.domain.Article;
import com.example.bbs_app.domain.Comment;
import jakarta.servlet.ServletContext;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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

    @Autowired
    private CommentRepository commentRepository;

    /**
     * 初期表示.
     *
     * @return 掲示板の画面
     */
    @GetMapping("")
    public String index() {
        List<Article> articleList = articleRepository.findAllArticleAndComment();
        application.setAttribute("articleList", articleList);
        return "bbs";
    }

    /**
     * 投稿を追加.
     *
     * @return 掲示板の画面にリダイレクト
     */
    @PostMapping("/insert-article")
    public String insertArticle(ArticleForm form) {
        ModelMapper modelMapper = new ModelMapper();
        Article article = modelMapper.map(form, Article.class);
        articleRepository.insert(article);
        return "redirect:/article";
    }

    /**
     * コメントを追加.
     *
     * @return 掲示板の画面の画面にリダイレクト
     */
    @PostMapping("/insert-comment")
    public String insertComment(CommentForm form) {
        ModelMapper modelMapper = new ModelMapper();
        Comment comment = modelMapper.map(form, Comment.class);
        commentRepository.insert(comment);
        return "redirect:/article";
    }

    /**
     * 投稿とコメントの削除.
     *
     * @return 掲示板の画面の画面にリダイレクト
     */
    @PostMapping("/delete-article/")
    public String deleteArticle(Integer id) {
        articleRepository.deleteEverything(id);
        return "redirect:/article";
    }


}
