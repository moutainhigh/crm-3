package com.hefei.agg.log.mapper;

import com.hefei.api.agg.log.manager.po.UserLoginHistory;
import com.hefei.service.framework.base.mapper.Mapper;

public interface UserLogHistoryMapper extends Mapper{

	int saveUserLoginHistory(UserLoginHistory userLoginHistory);

}
