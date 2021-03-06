/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.app.dao;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.app.entity.AirDrugRelationCategory;

/**
 * 药品与药品类别的多对多关系表DAO接口
 * @author 范耘诚
 * @version 2019-03-06
 */
@MyBatisDao
public interface AirDrugRelationCategoryDao extends CrudDao<AirDrugRelationCategory> {
	
}