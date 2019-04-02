package app.withyou.ahometoshare.service;

import app.withyou.ahometoshare.model.Article;

public interface ArticleService {

    public Article getTermsOfUse();

    public Article getPrivacyPolicy();

    public Article getArticleById(Integer articleId);
}
