package com.yingtong.serviceImpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yingtong.dao.ArticleDao;
import com.yingtong.entity.Article;
import com.yingtong.service.ArticleService;
@Service("articleService")
@Transactional
public class ArticleServiceImpl implements ArticleService {
@Resource
private ArticleDao dao;

public List<Article> findAllArticles() {
	return dao.findAllArticles();
}

public List<Article> findArticlesByTitleId(Integer title_id) {
	 
	return dao.findArticlesByTitleId(title_id);
}

public boolean addArticle(Article article) {
	 
	return dao.addArticle(article);
}

public boolean updateArticle(Article article) {
 
	return dao.updateArticle(article);
}

public boolean deleteArticleById(Integer id) {
 
	return dao.deleteArticleById(id);
}

public Article findArticleById(Integer id) {
	 
	return dao.findArticleById(id);
}
 

}
