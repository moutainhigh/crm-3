package com.rmsClient.test;

import java.util.Date;
import java.util.List;

import com.hefei.api.rms.attendance.manager.IAttendanceManager;
import com.hefei.api.rms.attendance.manager.impl.AttendanceManager;
import com.hefei.api.rms.attendance.vo.AttendanceInfo;
import com.hefei.api.rms.company.manager.ICompanyManager;
import com.hefei.api.rms.company.manager.impl.CompanyManager;
import com.hefei.api.rms.company.vo.CompanyInfo;
import com.hefei.api.rms.interview.manager.IInterviewManager;
import com.hefei.api.rms.interview.manager.impl.InterviewManager;

public class AttendanceManagerTest {

	private static void createAttendance(){
		AttendanceInfo attendanceInfo = new AttendanceInfo();
		IAttendanceManager AttendanceM = new AttendanceManager();
		try {
			attendanceInfo.setId(112l);
			attendanceInfo.setCompanyId(111l);
			attendanceInfo.setEmployeeId(111l);
			attendanceInfo.setGoworkTime(new Date());
			attendanceInfo.setOffworkTime(new Date());
			attendanceInfo.setNote("111");
			attendanceInfo.setDelFlag("1");
			attendanceInfo.setCreateTime(new Date());
			attendanceInfo.setUpdateTime(new Date());
			AttendanceM.createAttendance(attendanceInfo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	       //更改出勤信息
			private static void updateAttendance(){
				IAttendanceManager AttendanceM = new AttendanceManager();
				try {
					AttendanceM.updateAttendance(112l,"0");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			//查看出勤信息
			private static void findAttendance(){
				IAttendanceManager AttendanceM = new AttendanceManager();
				Long employeeId = 111l;
				try {
					List<AttendanceInfo> ybcList = AttendanceM.findAttendance(employeeId);
					for(AttendanceInfo m :ybcList){
						System.out.println(m.getId()+","+m.getEmployeeId());
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			public static void main(String[] args) {
				//createAttendance();
				//updateAttendance();
				findAttendance();
			}
}