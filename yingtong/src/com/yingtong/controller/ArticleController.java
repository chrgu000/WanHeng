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
	private ArticleServiceImpl service;//���·������
	@Resource
	private TitleDao dao;//��������
	
	@RequestMapping("/article_list.do")
	public String findAllAction(ModelMap map) {
		List<Article> articles = service.findAllArticles();//��ȡ��������
		map.put("articles", articles);
		return "admin/article";
	}
	@RequestMapping("/toAddArticle.do")
	public String toAddAction(ModelMap map) {
		List<Title> titles = dao.findAllTitles();//��ȡ���б���
		map.put("titles", titles);
		return "admin/article_add";//ת�����������ҳ��
	}

	@RequestMapping("/addArticle.do")
	public String addAction(Article article) {
		service.addArticle(article);//�������
		return "redirect:../article/article_list.do";//�ض�������ҳ����ҳ
	}

	@RequestMapping("/toUpdateArticle.do")
	public String toUpdateAction(ModelMap map, Integer id) {

		Article article = service.findArticleById(id);//����id������
		List<Title> titles = dao.findAllTitles();//��ȡ���д����
		map.put("titles", titles);
		map.put("article", article);
		return "admin/article_update";//ת���������޸�ҳ��
	}

	@RequestMapping("/updateArticle.do")
	public String updateAction(Article article) {
		service.updateArticle(article);//�޸�����ҳ��
		return "redirect:../article/article_list.do";//�ض���������ҳ
	}

	@RequestMapping("/deleteArticleById.do")
	public String deleteAction(Integer id) {
		service.deleteArticleById(id);//ɾ�����¸���id
		return "redirect:../article/article_list.do";//�ض���������ҳ
	}
}
