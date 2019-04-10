/*
 * File: ArticleServiceImpl.java
 * Author: Nan Jiang
 * Clients: Michelle Bilek - A Home To Share
 * Course: CST8334 Software Development Project - 2019W
 * Professor: Reg Dyer
 * Project: A Home to Share
 * Copyright @ 2019
 */


package app.withyou.ahometoshare.service.impl;

import app.withyou.ahometoshare.dao.ArticleMapper;
import app.withyou.ahometoshare.model.Article;
import app.withyou.ahometoshare.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    ArticleMapper articleMapper;

    @Override
    public Article getTermsOfUse() {
        Article article = articleMapper.selectByPrimaryKey(1);
        return article;
    }

    @Override
    public Article getPrivacyPolicy() {
        Article article = articleMapper.selectByPrimaryKey(2);
        return article;
    }

    @Override
    public Article getArticleById(Integer articleId) {
        return articleMapper.selectByPrimaryKey(articleId);
    }

}
