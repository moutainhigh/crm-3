package com.hefei.rms.attendance.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.hefei.api.rms.attendance.manager.IAttendanceManager;
import com.hefei.api.rms.attendance.manager.impl.AttendanceManager;
import com.hefei.api.rms.attendance.vo.AttendanceInfo;
import com.hefei.frontend.framework.http.request.RequestThreadLocal;
import com.hefei.rms.attendance.service.IUploadAttendanceService;

@Service
public class UploadAttendanceService implements IUploadAttendanceService {
	
	private static final Logger logger = Logger.getLogger(AttendanceService.class);
	private IAttendanceManager attendanceManager = new AttendanceManager();


	public void uploadAttendance(List<AttendanceInfo> attendanceInfo,List<String> erroReturnMessage) {
		AttendanceInfo infos = null;
		for(AttendanceInfo info:attendanceInfo){
			try{
				List<AttendanceInfo> existsInfo = attendanceManager.findAttendance(info.getEmployeeId());
				if(existsInfo == null){
					attendanceManager.createAttendance(infos);
				}
			}catch(Exception e){
				logger.info(RequestThreadLocal.getTimer() + " error " + info.getEmployeeId(), e);
				erroReturnMessage.add("商品ID=" + info.getEmployeeId() + " 上传出错");
				continue;
			}
		}
	}
}
