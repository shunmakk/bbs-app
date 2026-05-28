package com.example.bbs_app.Repository;


import com.example.bbs_app.domain.Article;
import com.example.bbs_app.domain.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


/**
 * articlesテーブルを操作するリポジトリです.
 */
@Repository
public class ArticleRepository {

    @Autowired
    private NamedParameterJdbcTemplate template;

    //ResultSetExtractorを使用して複数行分のデータを取得します
    private static final ResultSetExtractor<List<Article>> ARTICLE_RESULTSET = (rs) -> {
        List<Article> articleList = new ArrayList<>();
        List<Comment> commentList = null;

        //articlesテーブルは結合した際に複数行にわたり同じデータが出力される可能性があるため、前のarticlesテーブルのIDを保持するための変数を宣言
        int beforeIdNum = 0;

        //ResultSetオブジェクトに格納された複数のデータをList<Articles>変数に格納していく
        while (rs.next()) {
            int nowIdNum = rs.getInt("a_id");

            if (nowIdNum != beforeIdNum) {
                Article article = new Article();
                article.setId(nowIdNum);
                article.setName(rs.getString("a_name"));
                article.setContent(rs.getString("a_content"));
                //コメントがあった際にArticleオブジェクトのcommentListに格納するため空のArrayListをセットしておく
                commentList = new ArrayList<Comment>();
                article.setCommentList(commentList);
                articleList.add(article);
            }
            //コメントがない場合はCommentオブジェクトを作成しないようにする
            if (rs.getInt("c_id") != 0) {
                Comment comment = new Comment();
                comment.setId(rs.getInt("c_id"));
                comment.setName(rs.getString("c_name"));
                comment.setContent(rs.getString("c_content"));
                //commentListにcommentオブジェクトの内容をぶちこむ！
                Objects.requireNonNull(commentList).add(comment);
            }
            //現在検索しているarticleテーブルのIDを前のarticleテーブルのIDを入れるbeforeIdNumに代入する
            beforeIdNum = nowIdNum;
        }
        return articleList;
    };

    /**
     * 投稿内容とコメントを全て取得する全件検索.
     *
     * @return 投稿一覧
     */
    public List<Article> findAllArticleAndComment() {
        String sql = """
                SELECT
                a.id AS a_id,
                a.name AS a_name,
                a.content AS a_content,
                c.id AS c_id,
                c.name AS c_name,
                c.content AS c_content
                FROM articles a
                LEFT OUTER JOIN comments c
                ON a.id = c.article_id
                ORDER BY a.id DESC, c.id ASC;
                """;
        return template.query(sql, ARTICLE_RESULTSET);
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

    /**
     * 指定されたidを基に投稿内容をテーブルから削除します.
     *
     * @param id 記事id
     */
    public void deletedById(Integer id) {
        String sql = """
                DELETE FROM articles WHERE id = :id;
                """;
        SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
        template.update(sql, param);
        System.out.println("指定されたidを基に記事削除することに成功しました");
    }


}
