package com.cgwas.statement.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.statement.dao.api.IStatementDao;
import com.cgwas.statement.entity.Statement;
import com.cgwas.statement.entity.StatementVo;
import com.cgwas.statement.service.api.IStatementService;

@Service
public class StatementService implements IStatementService {
	private IStatementDao statementDao;

	public Serializable save(Statement statement) {
		return statementDao.save(statement);
	}

	public void delete(Statement statement) {
		statementDao.delete(statement);
	}

	public void deleteByExample(Statement statement) {
		statementDao.deleteByExample(statement);
	}

	public void update(Statement statement) {
		statementDao.update(statement);
	}

	public void updateIgnoreNull(Statement statement) {
		statementDao.updateIgnoreNull(statement);
	}

	public void updateByExample(Statement statement) {
		statementDao.update("updateByExampleStatement", statement);
	}

	public StatementVo load(Statement statement) {
		return (StatementVo) statementDao.reload(statement);
	}

	public StatementVo selectForObject(Statement statement) {
		return (StatementVo) statementDao.selectForObject("selectStatement",
				statement);
	}

	public List<StatementVo> selectForList(Statement statement) {
		return (List<StatementVo>) statementDao.selectForList(
				"selectStatement", statement);
	}

	public Page page(Page page, Statement statement) {
		return statementDao.page(page, statement);
	}

	@Autowired
	public void setIStatementDao(
			@Qualifier("statementDao") IStatementDao statementDao) {
		this.statementDao = statementDao;
	}

}
