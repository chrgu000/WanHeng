package com.yingtong.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.yingtong.dao.TitleDao;
import com.yingtong.entity.Article;
import com.yingtong.entity.Title;
import com.yingtong.serviceImpl.ArticleServiceImpl;

@Controller
@RequestMapping("/article")
@Scope("prototype")
@SessionAttributes("page")
public class ArticleController {
	@Resource
	private ArticleServiceImpl service;//文章服务对象
	@Resource
	private TitleDao dao;//大标题对象
	
	@RequestMapping("/article_list.do")
	public String findAllAction(ModelMap map) {
		List<Article> articles = service.findAllArticles();//获取所有文章
		map.put("articles", articles);
		return "admin/article";
	}
	@RequestMapping("/toAddArticle.do")
	public String toAddAction(ModelMap map) {
		List<Title> titles = dao.findAllTitles();//获取所有标题
		map.put("titles", titles);
		return "admin/article_add";//转发到文章添加页面
	}

	@RequestMapping("/addArticle.do")
	public String addAction(Article article) {
		service.addArticle(article);//添加文章
		return "redirect:../article/article_list.do";//重定向到文章页面首页
	}

	@RequestMapping("/toUpdateArticle.do")
	public String toUpdateAction(ModelMap map, Integer id) {

		Article article = service.findArticleById(id);//根据id查文章
		List<Title> titles = dao.findAllTitles();//获取所有大标题
		map.put("titles", titles);
		map.put("article", article);
		return "admin/article_update";//转发到文章修改页面
	}

	@RequestMapping("/updateArticle.do")
	public String updateAction(Article article) {
		service.updateArticle(article);//修改文章页面
		return "redirect:../article/article_list.do";//重定向到文章首页
	}

	@RequestMapping("/deleteArticleById.do")
	public String deleteAction(Integer id) {
		service.deleteArticleById(id);//删除文章根据id
		return "redirect:../article/article_list.do";//重定向到文章首页
	}
}
