package com.rmsClient.test;

import java.util.Date;
import java.util.List;

import com.hefei.api.rms.attendance.manager.IAttendanceManager;
import com.hefei.api.rms.attendance.manager.impl.AttendanceManager;
import com.hefei.api.rms.attendance.vo.AttendanceInfo;
import com.hefei.api.rms.interview.manager.IInterviewManager;
import com.hefei.api.rms.interview.manager.impl.InterviewManager;
import com.hefei.api.rms.interview.vo.InterviewInfo;
import com.hefei.api.rms.position.manager.IPositionManager;
import com.hefei.api.rms.position.manager.impl.PositionManager;

public class InterviewManagerTest {

	private static void saveInterview(){
		InterviewInfo interviewInfo = new InterviewInfo();
		IInterviewManager InterviewM = new InterviewManager();
		try {
			interviewInfo.setId(111l);
			interviewInfo.setCompanyId(111L);
			interviewInfo.setEmployeeId(222L);
			interviewInfo.setPositionId(111l);
			interviewInfo.setName("jun");
			interviewInfo.setPhone("13275816666");
			interviewInfo.setEmail("2897221555@qq.com");
			interviewInfo.setInterviewStatus("0");
			interviewInfo.setUserId(2322l);
			interviewInfo.setInterviewActualTime(new Date());
			interviewInfo.setInterviewYuyueTime(new Date());
			interviewInfo.setAddress("newworld");
			interviewInfo.setDelFlag("1");
			interviewInfo.setCreateTime(new Date());
			interviewInfo.setUpdateTime(new Date());
			InterviewM.saveInterviewInfo(interviewInfo);
			//System.out.println(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	    //更改面试信息
		private static void updateInterview(){
			IInterviewManager InterviewM = new InterviewManager();
			try {
				InterviewM.updateInterview(111l,"3");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		//查看出勤信息
		private static void findInterview(){
			IInterviewManager InterviewM = new InterviewManager();
			Long id = 111l;
			String interviewStatus = "3";
			try {
				List<InterviewInfo> ybcList = InterviewM.findInterview(id,interviewStatus);
				for(InterviewInfo m :ybcList){
					System.out.println(m.getId()+","+m.getName());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
			public static void main(String[] args) {
				//saveInterview();
				//updateInterview();
				findInterview();
			}
}