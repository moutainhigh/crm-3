package com.rmsClient.test;

import java.util.Date;

import com.hefei.api.rms.position.manager.IPositionManager;
import com.hefei.api.rms.position.manager.impl.PositionManager;
import com.hefei.api.rms.position.vo.PositionInfo;

public class PositionManagerTest {

	private static void savePosition(){
		PositionInfo positionInfo = new PositionInfo();
		//IdManager im = new IdManagerImpl();
		IPositionManager PositionM = new PositionManager();
		try {
			//Long id = im.getNextId();
			positionInfo.setId(112l);
			positionInfo.setDepartmentId(1122l);
			positionInfo.setDelFlag("1");
			positionInfo.setPositionName("kaifa");
			positionInfo.setCreateTime(new Date());
			positionInfo.setUpdateTime(new Date());
			PositionM.savePosition(positionInfo);
			//System.out.println(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//更改状态
	private static void updatePosition(){
		IPositionManager PositionM = new PositionManager();
		try {
			PositionM.updatePosition(111l,"0");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
			//查看信息
			private static void findPosition(){
				IPositionManager PositionM = new PositionManager();
				Long id = 112l;
				try {
					PositionInfo PositionInfo = PositionM.getPosition(id);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			public static void main(String[] args) {
				//savePosition();
				//updatePosition();
				findPosition();
			}
}