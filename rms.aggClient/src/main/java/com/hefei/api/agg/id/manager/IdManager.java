package com.hefei.api.agg.id.manager;


public interface IdManager {

	/****
	 * 获取数据库表id
	 * @return
	 * @throws Exception
	 */
	public Long getNextId()throws Exception ;
}
