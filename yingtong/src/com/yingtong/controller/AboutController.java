package com.yingtong.controller;

import java.sql.Timestamp;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.yingtong.dao.AdviceDao;
import com.yingtong.dao.TitleDao;
import com.yingtong.entity.Advice;
import com.yingtong.entity.Article;
import com.yingtong.entity.Title;
import com.yingtong.entity.User;
import com.yingtong.page.AdvicePage;
import com.yingtong.service.ArticleService;

@Controller
@RequestMapping("/about")
@Scope("prototype")
@SessionAttributes("advicePage")
public class AboutController {
@Resource
private ArticleService service;//文章服务接口
@Resource
private TitleDao dao;//大标题接口
@Resource
private AdviceDao adao;
@RequestMapping("/index.do")
public String indexAction(ModelMap  map,Integer title_id,Integer id){
	List<Article> articles=service.findAllArticles();//获取所有文章
	List<Title> titles=dao.findAllTitles();//获取所有标题
	List<Article> articles2=service.findArticlesByTitleId(4);//获取会员条款
	if(id==null){//文章Id为空时，获取大标题id为title_id下的文章
		List<Article> articles1=service.findArticlesByTitleId(title_id);
		if(articles1.size()>0){
			map.put("article", articles1.get(0));
		}
	}else{
		Article article=service.findArticleById(id);//根据id查询文章
		map.put("article", article);
	}
	map.put("articles", articles);
	map.put("titles", titles);
 map.put("articles2", articles2);
	return "index/about";
}
@RequestMapping("/helpcenter.do")
public String helpCenterAction(ModelMap map){
	List<Article> articles=service.findArticlesByTitleId(5);
	map.put("articles", articles);
	return "mobile/bangzhu";
}
@RequestMapping("/help.do")
public String helpAction(ModelMap map,Integer id){
	Article article=service.findArticleById(id);
	map.put("article", article);
	return "mobile/help";
}
@RequestMapping("/addAdvice.do")
public String addAdviceAction(Advice advice,ModelMap map,HttpSession session){
	Timestamp time=new Timestamp(System.currentTimeMillis());
	User user=(User) session.getAttribute("user");
	advice.setAdviser(user.getUsername());
	advice.setTel(user.getTel());
	advice.setTime(time);
	adao.addAdvice(advice);
	map.put("msg", "建议成功提交,谢谢您!");
	return "mobile/fankui";
}
@RequestMapping("/advice_list.do")
public String findAllAdviceAction(ModelMap map,AdvicePage page){
	Integer rows=adao.findRows(page);
	page.setRows(rows);
	List<Advice> advices=adao.findAdviceByPage(page);
	map.put("advicePage",page);
	map.put("advices", advices);
	return "admin/advice";
}
@RequestMapping("/deleteAdvice.do")
public String deleteAction(Integer id){
	adao.deleteAdvice(id);
	return "redirect:../about/advice_list.do";
}

}
