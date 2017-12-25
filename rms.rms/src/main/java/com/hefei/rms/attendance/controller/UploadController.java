//package com.hefei.rms.attendance.controller;
//
//import java.io.BufferedInputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.apache.commons.lang.StringUtils;
//import org.apache.log4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.util.MultiValueMap;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.multipart.MultipartFile;
//import org.springframework.web.multipart.MultipartHttpServletRequest;
//
//import com.hefei.api.rms.attendance.vo.AttendanceInfo;
//import com.hefei.frontend.framework.http.request.RequestThreadLocal;
//import com.hefei.rms.attendance.create.service.IAttendanceService;
//
//
//@Controller
//@RequestMapping(value="/uploadAttendance",produces="text/plain;charset=UTF-8")
//public class UploadController {
//	
//	private Logger logger = Logger.getLogger(UploadController.class);
//	@Autowired
//	private IAttendanceService attendanceService;	
//	
//	@RequestMapping(value="/uploadAttendance",method=RequestMethod.POST)
//	public String uploadAttendance(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		List<String> erroReturnMessage = new ArrayList<String>();
//		try {
//			List<AttendanceInfo> list = null;
//			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
//			// 获得上传的文件（根据前台的name名称得到上传的文件）
//			MultiValueMap<String, MultipartFile> multiValueMap = multipartRequest.getMultiFileMap();
//			List<MultipartFile> files = multiValueMap.get("Excelfile");
//
//			String fileFileName = "";
//			MultipartFile file = null;
//			Long employeeId = null;
//			if (files != null && files.size() > 0) {
//				for (MultipartFile ifile : files) {
//					if (ifile.getSize() > 0) {
//						fileFileName = ifile.getOriginalFilename(); // 获取文件名
//						file = ifile;
//					}
//				}
//				
//				// 读取excel中的数据
//				list=getExcelData(file.getInputStream(),employeeId, erroReturnMessage);
//				if(list != null){
//					//导入员工数据
//					attendanceService.uploadAttendance(list,erroReturnMessage);
//				}
//			} else {
//				erroReturnMessage.add("请选择文件");
//			}
//
//		} catch (Exception e) {
//			logger.error(RequestThreadLocal.getTimer() + " error", e);
//			if(StringUtils.isNotBlank(e.getMessage())){
//				erroReturnMessage.add(e.getMessage());
//			}else{
//				erroReturnMessage.add(StrackTraceUtil.getStackMsg(e));
//			}
//		}
//		if(erroReturnMessage.isEmpty()){
//			erroReturnMessage.add("上传成功");
//		}
//		request.setAttribute("erroReturnMessage", erroReturnMessage);
//		return "user/upload";
//	}
//
//	private List<AttendanceInfo> getExcelData(InputStream inputStream, Long employeeId,List<String> erroReturnMessage) {
//		List<AttendanceInfo> list = new ArrayList<AttendanceInfo>();
//		Workbook wb = null;
//		// List<ExcelImportIdNo> importList = new ArrayList<ExcelImportIdNo>();
//		try {
//			inputStream = new BufferedInputStream(inputStream);
//			wb = Workbook.getWorkbook(inputStream);
//			// 解析第一张表
//			Sheet sheet = wb.getSheet(0);
//			Cell cMark = sheet.getCell(0, 1);
//			String savaOrDeleteMark = cMark.getContents();
//			
//			int rows = sheet.getRows();// 得到所有的行
//			
//			if(rows > 4000){
//				logger.error("表格不能超过4000行  " + rows);
//				erroReturnMessage.add("表格不能超过4000行");
//				return null;
//			}
//			int clos = sheet.getColumns();// 得到所有的列
//			if(rows <= 4000){
//				AttendanceInfo uiInfo = null;
//				for (int i = 1; i < rows; i++) {// 解析excel每一行数据
//					uiInfo = new AttendanceInfo();
//					int sign =0;
//					for (int j = 0; j < clos; j++) {// 解析excel每一列数据
//						try{
//							// 第一个是列数，第二个是行数
//							String item = sheet.getCell(j, i).getContents();
//							if(StringUtils.isBlank(item)){
//								if(j == 0 || j == 1 || j == 2 ){
//									erroReturnMessage.add("第" + i + "行" +"第"+ j +"列数据不能为空！");
//									sign=1;
//									break;
//								}
//							}
//							switch (j) {
//							case 0:
//								if(StringUtils.isBlank(item)){
//									uiInfo.setEmployeeId(null);
//								}
//								break;
//							case 1:
//								if(StringUtils.isBlank(item)){
//									uiInfo.setOffworkTime(null);
//								}
//								break;
//							case 2:
//								if(StringUtils.isBlank(item)){
//									uiInfo.setGoworkTime(null);
//								}
//								break;
//							default:
//								break;
//							}
//							
//						}catch(Exception e){
//							logger.info(RequestThreadLocal.getTimer() + " error row:" + i + " col:" + j + " data:" +  uiInfo, e);
//						}
//					}
//				}
//				return list;
//			}else{
//				throw new Exception("row > 4000");
//			}
//			
//		} catch (Exception e) {
//			logger.error("商品上传文件解析 Exception : ", e);
//			if(StringUtils.isNotBlank(e.getMessage())){
//				erroReturnMessage.add(e.getMessage());
//			}else{
//				erroReturnMessage.add(StrackTraceUtil.getStackMsg(e));
//			}
//		} finally {
//			if (inputStream != null) {
//				try {
//					inputStream.close();
//				} catch (IOException e) {
//					logger.error("商品上传文件解析 Exception : ", e);
//				}
//				inputStream = null;
//			}
//			if (wb != null) {
//				wb.close();
//			}
//		}
//		return null;
//	}
//}
