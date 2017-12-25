package com.rmsClient.test;

import java.util.Date;
import java.util.List;

import com.hefei.api.rms.attendance.manager.IAttendanceManager;
import com.hefei.api.rms.attendance.manager.impl.AttendanceManager;
import com.hefei.api.rms.attendance.vo.AttendanceInfo;
import com.hefei.api.rms.contract.manager.IContractManager;
import com.hefei.api.rms.contract.manager.impl.ContractManager;
import com.hefei.api.rms.contract.vo.ContractInfo;

public class ContractManagerTest {

	private static void saveInfo(){
		ContractInfo contractInfo = new ContractInfo();
		IContractManager ContractM = new ContractManager();
		try {
			contractInfo.setId(112l);
			contractInfo.setCompanyId(111l);
			contractInfo.setEmployeeId(112l);
			contractInfo.setAuditUserId(112l);
			contractInfo.setContractPics("788.jpg");
			contractInfo.setContractType("1");
			contractInfo.setDelFlag("1");
			contractInfo.setAuditStatus("0");
			contractInfo.setContractStartTime(new Date());
			contractInfo.setContractEndTime(new Date());
			contractInfo.setCreateTime(new Date());
			contractInfo.setUpdateTime(new Date());
			ContractM.saveInfo(contractInfo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//更新合同
		private static void updateContract(){
			IContractManager ContractM = new ContractManager();
			try {
				ContractM.updateContract(111l,"1","2","1");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		//查看合同信息
		private static void findContract(){
			IContractManager ContractM = new ContractManager();
			Long employeeId = 112l;
			try {
				List<ContractInfo> ybcList = ContractM.findContract(employeeId);
				for(ContractInfo m :ybcList){
					System.out.println(m.getId()+","+m.getEmployeeId());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
			public static void main(String[] args) {
				//saveInfo();
				//updateContract();
				findContract();
			}
}