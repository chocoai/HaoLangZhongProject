package com.jeesite.modules.app.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.app.dao.AirDrugRelationCategoryDao;
import com.jeesite.modules.app.entity.AirDrugRelationCategory;

@Service
@Transactional(propagation = Propagation.REQUIRED ,isolation = Isolation.DEFAULT,rollbackFor=Exception.class)
public class AirDrugRelationCategoryService extends CrudService<AirDrugRelationCategoryDao,AirDrugRelationCategory> {

}
