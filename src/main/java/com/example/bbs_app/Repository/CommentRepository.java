package com.example.bbs_app.Repository;


import com.example.bbs_app.domain.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

/**
 * commentsテーブルを操作するリポジトリです.
 */
@Repository
public class CommentRepository {
    @Autowired
    private NamedParameterJdbcTemplate template;

    /**
     * コメントをデータベースに挿入.
     *
     * @param comment コメントドメイン
     */
    public void insert(Comment comment) {
        SqlParameterSource param = new BeanPropertySqlParameterSource(comment);
        String sql = """
                 INSERT INTO comments (name, content,article_id) VALUES (:name,:content,:articleId);
                """;
        template.update(sql, param);
    }

}
