package com.example.bbs_app.Form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * コメントのフォームクラス.
 */
public class CommentForm {
    /**
     * 記事id
     */
    private String articleId;
    /**
     * コメント者名
     */
    @NotBlank(message = "名前は必須です")
    @Size(max = 50, message = "50文字以内で入力してください")
    private String name;
    /**
     * コメント内容
     */
    @NotBlank(message = "コメントは必須です")
    private String content;

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "CommentForm{" +
                "articleId='" + articleId + '\'' +
                ", name='" + name + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
