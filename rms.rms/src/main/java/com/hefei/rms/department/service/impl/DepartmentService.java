package com.hefei.rms.department.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.hefei.api.rms.department.manager.IDepartmentManager;
import com.hefei.api.rms.department.manager.impl.DepartmentManager;
import com.hefei.api.rms.department.vo.DepartmentInfo;
import com.hefei.common.exception.BusinessException;
import com.hefei.common.exception.ClientException;
import com.hefei.common.page.Pagination;
import com.hefei.common.returncode.ReturnCode;
import com.hefei.frontend.framework.http.request.RequestThreadLocal;
import com.hefei.rms.department.service.IDepartmentService;
import com.hefei.rms.department.vo.DepartmentTreeNode;


@Service
public class DepartmentService implements IDepartmentService{
	
	private Logger logger = Logger.getLogger(DepartmentService.class);
	
	private IDepartmentManager departmentManager = new DepartmentManager();

	@Override
	public DepartmentInfo createDepartment(DepartmentInfo info) throws BusinessException {
		try{
			return departmentManager.saveDepartment(info);
		} catch (ClientException e) {
			logger.error(RequestThreadLocal.getTimer() + " error", e);
			throw new BusinessException(e.getErrorCode());
		}
	}
	
	public DepartmentInfo getDepartment(Long departmentId) throws BusinessException{
		try{
			return departmentManager.getDepartmentById(departmentId);
		} catch (ClientException e) {
			logger.error(RequestThreadLocal.getTimer() + " error", e);
			throw new BusinessException(e.getErrorCode());
		}
	}
	public List<DepartmentInfo> getDepartmentsByCompany(Long companyId) throws BusinessException{
		try {
			int pageIndex =1;
			int pageSize = 1000;
			Pagination<DepartmentInfo> pagination = findDeparmentPaginationByCompanyId(companyId, pageIndex, pageSize);
			if(pagination != null){
				return pagination.getItems();
			}
			return null;
		} catch (Exception e) {
			logger.error(RequestThreadLocal.getTimer() + " error", e);
			return null;
		}
		
	}
	
	public Pagination<DepartmentInfo> findDeparmentPaginationByCompanyId(Long companyId, int pageIndex, int pageSize) throws BusinessException{
		try {
			return departmentManager.findDepartmentPaginationByCompany(companyId, pageIndex, pageSize);
		} catch (ClientException e) {
			logger.error(RequestThreadLocal.getTimer() + " error", e);
			return null;
		}
	}
	
	public List<DepartmentTreeNode> treeNode(Long companyId) throws BusinessException {
		try {
			Pagination<DepartmentInfo> pagination = findDeparmentPaginationByCompanyId(companyId, 1, 1000);
			List<DepartmentTreeNode> trees = new ArrayList<DepartmentTreeNode>();
			DepartmentTreeNode rootTree = new DepartmentTreeNode();
			rootTree.setChecked(false);
			rootTree.setOpen(true);
			rootTree.setId("0");
			rootTree.setpId("-1");
			rootTree.setName("公司部门");
			trees.add(rootTree);
			
			if(pagination == null || pagination.getItems() == null || pagination.getItems().size() <=0){
				
			}else{
				DepartmentTreeNode tree = null;
				List<DepartmentInfo> departments = pagination.getItems();
				for(DepartmentInfo department : departments){
					tree = new DepartmentTreeNode();
					tree.setDepartmentId(department.getId());
					tree.setId(department.getId().toString());
					tree.setpId(department.getParentId().toString());
					tree.setName(department.getDepartmentName());
					tree.setOpen(false);
					
					trees.add(tree);
				}
			}
			return trees;
		} catch (BusinessException e) {
			throw e;
		}catch (Exception e) {
			logger.error(RequestThreadLocal.getTimer() + "error", e);
			throw new BusinessException(ReturnCode.RETURN_CODE_ERROR);
		}
		
	}
}
