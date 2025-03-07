package ilike.ildrio.service.common;

import java.util.List; 

//import ilike.ildrio.common.RespData;
import ilike.ildrio.common.ResponseMessage;
import ilike.ildrio.common.StatusCode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ilike.ildrio.mapper.common.CommonMapper;

@Service
public class CommonService {

	private CommonMapper commonMapper;

	public CommonService(CommonMapper commonMapper) {
		this.commonMapper = commonMapper;
	}

	public int sequence_common() {
		return commonMapper.sequence_common();
	}

}
