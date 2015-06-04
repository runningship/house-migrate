package com.youwei.zjb.house.migrate;

import java.util.Date;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.bc.sdak.SimpDaoTool;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;


/**
 * 终端用户
 */
@Entity
@Table(name="uc_user")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class User {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Integer id;
	
	public Integer cid;
	
	/**
	 * 分店
	 */
	@Column(name="did")
	public Integer did;
	
	public String uname;
	
	/**
	 * 登录帐号
	 */
	public String lname;
	
	@Column(name="lpwds")
	public String pwd;
	
	public Date addtime;
	
	public Date lasttime;
	
	@Column(name="unid")
	public Integer roleId;
	
	/**
	 * 0锁定
	 */
	@Column(name="llock")
	public Integer lock;
	
	public String tel;
	
	public String ip;
	
	@Column(name="dizhi")
	public String address;
	
	/**
	 *TODO 好像无用，值全为1
	 */
	public Integer flag;
	
	/**
	 * TODO 好像无用
	 */
	public Integer sh;
	
	@Column(name="sex")
	public Integer gender;
	
	public Integer share;
	
	
	public String toString(){
		return uname;
	}
}
