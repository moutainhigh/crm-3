package com.hefei.frontsample.sample.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hefei.common.exception.BusinessException;
import com.hefei.common.page.Pagination;
import com.hefei.frontend.framework.http.request.CommonParameterThreadLocal;
import com.hefei.frontend.framework.http.request.RequestThreadLocal;
import com.hefei.frontsample.sample.client.SampleVO;
import com.hefei.frontsample.sample.service.ISampleService;

/**
 * 公司
 * @author zn
 *
 */
@Controller
@RequestMapping(value="/Sample",produces="text/plain;charset=UTF-8")
public class SampleController {
	
	private Logger logger = Logger.getLogger(SampleController.class);
	
	@Autowired
	private ISampleService sampleService;
	
	@RequestMapping("index")
	public String index(HttpServletRequest request, HttpServletResponse response){
		request.setAttribute("pageIndex", 1);
		request.setAttribute("pageSize", Pagination.DEFAULT_PAGE_SIZE);
		return "sample/index";
	}
	@RequestMapping("search")
	public String searchSample(HttpServletRequest request,HttpServletResponse response, String type, Integer pageIndex, Integer pageSize) {
		try{
			if(pageIndex == null || pageIndex <= 0){
				pageIndex = 1;
			}
			if(pageSize == null || pageSize <=0){
				pageSize = Pagination.DEFAULT_PAGE_SIZE;
			}
			request.setAttribute("pageIndex", pageIndex);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("type", type);
			Pagination<SampleVO> pagination = sampleService.findPagination(type, pageIndex, pageSize);
			request.setAttribute("pagination", pagination);
		} catch (Exception e) {
			logger.error(RequestThreadLocal.getTimer() + "error", e);
		}
		return "Sample/index";
	}
	
	@RequestMapping("toCreate")
	public String toCreate(HttpServletRequest request, HttpServletResponse response){
		return "sample/add";
	}
	
	@RequestMapping(value="/create",method=RequestMethod.POST)
	public String createSample(HttpServletRequest request) {
		try{
			SampleVO info = new SampleVO();
			sampleService.create(info);
		}catch (Exception e){
			logger.error(RequestThreadLocal.getTimer() + "error", e);
			return "Sample/add";
		}
		return "Sample/addResult";
	}
	
	
	@RequestMapping("toEdit")
	public String toEdit(HttpServletRequest request, HttpServletResponse response, Long sampleId){
		try {
			SampleVO sample = sampleService.get(sampleId);
			
			request.setAttribute("sample", sample);
		} catch (BusinessException e) {
			logger.error(RequestThreadLocal.getTimer() + "error", e);
		}
		return "sample/edit";
	}
	
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	public String edit(HttpServletRequest request) {
		try{
			SampleVO info = new SampleVO();

			Long userId = CommonParameterThreadLocal.getUserId();
			
			sampleService.edit(userId, info);
		}catch (Exception e){
			logger.error(RequestThreadLocal.getTimer() + "error", e);
			return "sample/edit";
		}
		return "sample/editResult";
	}
}