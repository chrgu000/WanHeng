package com.cgwas.proofimage.entity;

import javax.persistence.Id;
import java.util.Date;
import java.io.Serializable;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author yangjun <br/>s
 *         表名： p_proof_image <br/>
 *         描述：p_proof_image <br/>
 */
@SuppressWarnings("serial")
public class ProofImage implements Serializable {
	protected Long id;// id
	protected String advice_path;// advice_path
	protected Long proof_id;// proof_id

	public ProofImage() {
		super();
	}

	public ProofImage(Long id) {
		super();
		this.id = id;
	}
	
	@Id// 主键
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getAdvice_path() {
		return advice_path;
	}
	public void setAdvice_path(String advice_path) {
		this.advice_path = advice_path;
	}
	
	public Long getProof_id() {
		return proof_id;
	}
	public void setProof_id(Long proof_id) {
		this.proof_id = proof_id;
	}
}
