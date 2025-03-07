package ilike.ildrio.service.sys;  
 
import egovframework.rte.psl.dataaccess.mapper.Mapper;  
import org.springframework.stereotype.Service;  
 
import java.util.List;  
import java.util.Map;  
import java.util.HashMap;  
 
import org.springframework.transaction.annotation.Transactional;  
 
import ilike.ildrio.model.sys.SysMenuModel;  
import ilike.ildrio.mapper.sys.SysMenuMapper;  
 
 
@Service  
public class SysMenuService {   
 
	private final SysMenuMapper sysMenuMapper;  
 
	public SysMenuService(SysMenuMapper sysMenuMapper) {   
		this.sysMenuMapper = sysMenuMapper;  
	}  
 
	@Transactional 
	public Map<String, Object> getListMap_sysMenu(SysMenuModel model) throws Exception { 
 
		Map<String, Object> resultMap = new HashMap<String, Object>(); 
 
		List<SysMenuModel> rsList = sysMenuMapper.getList_sysMenu(model); 
		int totRow = sysMenuMapper.getListCount_sysMenu(model); 
 
		resultMap.put("rsList", rsList); 
		resultMap.put("totRow", totRow); 
 
		return resultMap; 
	} 
 
	public List<SysMenuModel> getList_sysMenu(SysMenuModel model) {  
		return sysMenuMapper.getList_sysMenu(model);  
	}  
 
	public List<SysMenuModel> subList_sysMenu(SysMenuModel model) {  
		return sysMenuMapper.subList_sysMenu(model);  
	}  
 
	public SysMenuModel getMap_sysMenu(SysMenuModel model) {  
		SysMenuModel rsModel =  sysMenuMapper.getMap_sysMenu(model);  
		if(rsModel == null) {   
			rsModel = new SysMenuModel();   
		}   
		return  rsModel;  
	}  
 
	public int getCount_sysMenu(SysMenuModel model) {  
		return sysMenuMapper.getCount_sysMenu(model);  
	}  
 
	public void insert_sysMenu(SysMenuModel model) {  
		sysMenuMapper.insert_sysMenu(model);  
	}  
 
	public void update_sysMenu(SysMenuModel model) {  
		sysMenuMapper.update_sysMenu(model);  
	}  
 
	public void delete_sysMenu(SysMenuModel model) {  
		sysMenuMapper.delete_sysMenu(model);  
	}  
 
}