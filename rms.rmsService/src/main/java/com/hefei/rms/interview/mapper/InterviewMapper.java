package com.hefei.rms.interview.mapper;

import java.util.List;
import java.util.Map;

import com.hefei.rms.behavior.po.BehaviorInfo;
import com.hefei.rms.interview.po.InterviewInfo;
import com.hefei.rms.position.po.Position;
import com.hefei.service.framework.base.mapper.Mapper;

public interface InterviewMapper extends Mapper{

	public void saveInterviewInfo(InterviewInfo interviewInfo);

	public void updateInterview(Map<String, Object> map);

	public List<InterviewInfo> findInterview(Long id, String interviewStatus);

}
