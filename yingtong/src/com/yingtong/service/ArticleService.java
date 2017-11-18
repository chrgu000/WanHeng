package com.yingtong.service;

import java.util.List;

import com.yingtong.entity.Article;

public interface ArticleService {
	List<Article> findAllArticles();

	List<Article> findArticlesByTitleId(Integer title_id);

	Article findArticleById(Integer id);

	boolean addArticle(Article article);

	boolean updateArticle(Article article);

	boolean deleteArticleById(Integer id);
}
