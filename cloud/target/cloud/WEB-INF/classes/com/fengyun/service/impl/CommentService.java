package com.fengyun.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.cgwas.common.dataaccess.api.Page;
import com.fengyun.dao.ICommentDao;
import com.fengyun.entity.Comment;
import com.fengyun.entity.CommentVo;
import com.fengyun.service.ICommentService;
/**
*  Author yangjun
*/
@Service
public class CommentService implements ICommentService {
	private ICommentDao commentDao;

	public Serializable save(Comment comment){
		return commentDao.save(comment);
	}

	public void delete(Comment comment){
		commentDao.delete(comment);
	}
	
	public void deleteByExample(Comment comment){
		commentDao.deleteByExample(comment);
	}

	public void update(Comment comment){
		commentDao.update(comment);
	}
	
	public void updateIgnoreNull(Comment comment){
		commentDao.updateIgnoreNull(comment);
	}
		
	public void updateByExample(Comment comment){
		commentDao.update("updateByExampleComment", comment);
	}

	public CommentVo load(Comment comment){
		return (CommentVo)commentDao.reload(comment);
	}
	
	public CommentVo selectForObject(Comment comment){
		return (CommentVo)commentDao.selectForObject("selectComment",comment);
	}
	
	public List<CommentVo> selectForList(Comment comment){
		return (List<CommentVo>)commentDao.selectForList("selectComment",comment);
	}
	
	public Page page(Page page, Comment comment) {
		return commentDao.page(page, comment);
	}

	@Autowired
	public void setICommentDao(
			@Qualifier("commentDao") ICommentDao  commentDao) {
		this.commentDao = commentDao;
	}

	public Page page1(Page page, Comment comment) {
		return commentDao.page1(page, comment);
	}

	 

}
