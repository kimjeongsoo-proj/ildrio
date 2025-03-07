package ilike.ildrio.service.sys;

 
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

import org.springframework.transaction.annotation.Transactional;

import ilike.ildrio.model.sys.SysCodeModel;
import ilike.ildrio.mapper.sys.SysCodeMapper;

@Service
public class SysCodeService {

	private final SysCodeMapper sysCodeMapper;

	public SysCodeService(SysCodeMapper sysCodeMapper) {
		this.sysCodeMapper = sysCodeMapper;
	}

	public List<SysCodeModel> getList_sysCode(SysCodeModel sysCodeModel) {
		return sysCodeMapper.getList_sysCode(sysCodeModel);
	}

	public SysCodeModel getMap_sysCode(SysCodeModel sysCodeModel) {
		SysCodeModel rsMap = sysCodeMapper.getMap_sysCode(sysCodeModel);
		if (rsMap == null) {
			rsMap = new SysCodeModel();
		}
		return rsMap;
	}

	public int getChkCnt_sysCode(SysCodeModel sysCodeModel) {
		return sysCodeMapper.getChkCnt_sysCode(sysCodeModel);
	}

	public void insert_sysCode(SysCodeModel sysCodeModel) {
		sysCodeMapper.insert_sysCode(sysCodeModel);
	}

	public void update_sysCode(SysCodeModel sysCodeModel) {
		sysCodeMapper.update_sysCode(sysCodeModel);
	}

	public void delete_sysCode(SysCodeModel sysCodeModel) {
		sysCodeMapper.delete_sysCode(sysCodeModel);
	}

}