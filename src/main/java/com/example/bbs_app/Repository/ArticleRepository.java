package com.example.bbs_app.Repository;


import com.example.bbs_app.domain.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * articlesテーブルを操作するリポジトリです.
 */
@Repository
public class ArticleRepository {

    @Autowired
    private NamedParameterJdbcTemplate template;

    /**
     * 投稿記事オブジェクトを生成するローマッパーです.
     */
    private static final RowMapper<Article> ARTICLE_ROW_MAPPER = (rs, i) -> {
        Article article = new Article();
        article.setId(rs.getInt("id"));
        article.setName(rs.getString("name"));
        article.setContent(rs.getString("content"));
        // コメントリストは、後から追加できるように空のリストを初期化してセットしておきます
        article.setCommentList(new java.util.ArrayList<>());
        return article;
    };

    /**
     * 全件検索.
     *
     * @return 投稿一覧
     */
    public List<Article> findAll() {
        String sql = """
                SELECT id, name, content
                FROM articles
                ORDER BY id DESC;
                """;
        return template.query(sql, ARTICLE_ROW_MAPPER);
    }

    /**
     * 投稿内容をデータベースに挿入.
     *
     * @param article 記事ドメイン
     */
    public void insert(Article article) {
        SqlParameterSource param = new BeanPropertySqlParameterSource(article);
        String sql = """
                 INSERT INTO articles (name, content) VALUES (:name,:content);
                """;
        template.update(sql, param);
        System.out.println("記事の追加に成功しました");
    }


}
