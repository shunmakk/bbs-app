package com.example.bbs_app.Repository;


import com.example.bbs_app.domain.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * commentsテーブルを操作するリポジトリです.
 */
@Repository
public class CommentRepository {
    @Autowired
    private NamedParameterJdbcTemplate template;
    /**
     * 記事コメントオブジェクトを生成するローマッパーです.
     */
    private static final RowMapper<Comment> COMMENT_ROW_MAPPER = (rs, i) -> {
        Comment comment = new Comment();
        comment.setId(rs.getObject("id", Integer.class));
        comment.setName(rs.getString("name"));
        comment.setContent(rs.getString("content"));
        comment.setArticleId(rs.getObject("article_id", Integer.class));
        return comment;
    };

    /**
     * 投稿idを基にテーブルからコメントの内容を取得する.
     *
     * @param articleId 投稿id
     * @return コメント一覧
     */
    public List<Comment> findByArticle(int articleId) {
        String sql = """
                SELECT id, name, content,article_id
                FROM comments
                WHERE article_id = :articleId
                ORDER BY id DESC;
                """;
        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("articleId", articleId);

        try {
            return template.query(sql, param, COMMENT_ROW_MAPPER);

        } catch (EmptyResultDataAccessException e) {
            System.out.println("指定したidからコメントを取得することができませんでした");
            return null;
        }
    }
}
