package com.example.bbs_app.Form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * 記事投稿のフォームクラス.
 */
public class ArticleForm {
    /**
     * 投稿者名
     */
    @NotBlank(message = "投稿者名は必須です")
    @Size(min = 1, max = 50, message = "50文字以内で入力してください")
    private String name;
    /**
     * 記事内容
     */
    @NotBlank(message = "投稿内容は必須です")
    private String content;

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
        return "ArticleForm{" +
                "name='" + name + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
