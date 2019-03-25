/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.app.web;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.JSONObject;
import com.jeesite.common.web.BaseController;
import com.jeesite.modules.app.entity.DoctorInfo;
import com.jeesite.modules.app.service.DoctorInfoService;
import com.jeesite.modules.app.utils.CodeMsg;
import com.jeesite.modules.app.utils.PageModel;
import com.jeesite.modules.app.utils.Result;
import com.jeesite.modules.app.utils.TokenTools;
import com.jeesite.modules.app.utils.exception.RedisCheckException;

/**
 * 医生管理Controller
 * @author 李昊翀
 * @version 2019-02-21
 */
@Controller
@SuppressWarnings("rawtypes")
@RequestMapping(value = "${frontPath}/sys/doctorInfo")
public class DoctorInfoController extends BaseController {

	@Autowired
	private DoctorInfoService doctorInfoService;
	
	@Autowired
	private StringRedisTemplate redis;
	
	/**
	 * 用户端根据医生标签检索医生列表
	 * */
	@ResponseBody
	@RequestMapping(value = "/queryListByLabel")
	public Result queryListByLabel(@RequestBody DoctorInfo requestParams) {
		try {
			PageModel pageModel = new PageModel(requestParams.getPageNum(), requestParams.getPageSize());
			requestParams.setCount(requestParams.getLabels().size());
			requestParams.setPageModel(pageModel);
			List<Map<String, Object>> resultList = doctorInfoService.queryListByLabel(requestParams);
			JSONObject result = new JSONObject();
			result.put("items", resultList);
			result.put("count", doctorInfoService.queryCountByLabel(requestParams));
			return Result.success(result);			
		}
		catch (Exception e) {
			logger.error(e.getMessage(), e);
			return Result.error(CodeMsg.PARAMETER_ISNULL);
		}
	}
	
	/**
	 * 用户端多条件查询医生列表
	 * */
	@ResponseBody
	@RequestMapping(value = "/queryList")
	public Result queryList(@RequestBody DoctorInfo requestParams) {
		try {
			PageModel pageModel = new PageModel(requestParams.getPageNum(), requestParams.getPageSize());
			requestParams.setPageModel(pageModel);
			List<Map<String, Object>> resultList = doctorInfoService.queryList(requestParams);
			JSONObject result = new JSONObject();
			result.put("items", resultList);
			result.put("count", doctorInfoService.queryCount(requestParams));
			return Result.success(result);
		}
		catch (Exception e) {
			logger.error(e.getMessage(), e);
			return Result.error(CodeMsg.PARAMETER_ISNULL);
		}
	}
	
	/**
	 * 查询医生的详细信息
	 * */
	@ResponseBody
	@RequestMapping(value = "/findOneByDoctorid/{doctorid}/{token}")
	public Result findOneByDoctorid(@PathVariable String doctorid, @PathVariable String token) {
		try {			
			TokenTools.checkToken(token, redis);
			return Result.success(doctorInfoService.findOneByDoctorid(doctorid));
		}
		catch (RedisCheckException e2) {
			logger.error(e2.getMessage(), e2);
			return Result.error(CodeMsg.TOKEN_INVALID);
		}
		catch (Exception e) {
			logger.error(e.getMessage(), e);
			return Result.error(CodeMsg.PARAMETER_ISNULL);
		}
	}
	
	/**
	 * 查询医生评论列表
	 * */
	@ResponseBody
	@RequestMapping(value = "/queryCommentList")
	public Result queryCommentList(@RequestBody DoctorInfo requestParams) {
		try {
			PageModel pageModel = new PageModel(requestParams.getPageNum(), requestParams.getPageSize());
			requestParams.setPageModel(pageModel);
			List<Map<String, Object>> resultList = doctorInfoService.queryCommentList(requestParams);
			JSONObject result = new JSONObject();
			result.put("items", resultList);
			result.put("count", doctorInfoService.queryCommentCount(requestParams));
			return Result.success(result);
		}
		catch (Exception e) {
			logger.error(e.getMessage(), e);
			return Result.error(CodeMsg.PARAMETER_ISNULL);
		}
	}
	
	/**
	 * 查询医生问诊列表
	 * */
	@ResponseBody
	@RequestMapping(value = "/queryConsultationList")
	public Result queryConsultationList(@RequestBody DoctorInfo requestParams) {
		try {
			PageModel pageModel = new PageModel(requestParams.getPageNum(), requestParams.getPageSize());
			requestParams.setPageModel(pageModel);
			List<Map<String, Object>> resultList = doctorInfoService.queryConsultationList(requestParams);
			JSONObject result = new JSONObject();
			result.put("items", resultList);
			result.put("count", doctorInfoService.queryCommentCount(requestParams));
			return Result.success(result);
		}
		catch (Exception e) {
			logger.error(e.getMessage(), e);
			return Result.error(CodeMsg.PARAMETER_ISNULL);
		}
	}
	
	/**
	 * 查询医生订单数量
	 * */
	@ResponseBody
	@RequestMapping(value = "/findOrderCount/{id}/{token}")
	public Result findOrderCount(@PathVariable String id, @PathVariable String token) {
		try {			
			TokenTools.checkToken(token, redis);
			return Result.success(doctorInfoService.findOrderCount(id));
		}
		catch (RedisCheckException e2) {
			logger.error(e2.getMessage(), e2);
			return Result.error(CodeMsg.TOKEN_INVALID);
		}
		catch (Exception e) {
			logger.error(e.getMessage(), e);
			return Result.error(CodeMsg.PARAMETER_ISNULL);
		}
	}

}