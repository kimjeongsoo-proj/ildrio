package ilike.ildrio.service.sys;  
 
import egovframework.rte.psl.dataaccess.mapper.Mapper;  
import org.springframework.stereotype.Service;  
 
import java.util.List;  
import java.util.Map;  
import java.util.HashMap;  
 
import org.springframework.transaction.annotation.Transactional;  
 
import ilike.ildrio.model.sys.SysCalendarModel;  
import ilike.ildrio.mapper.sys.SysCalendarMapper;  
 
 
@Service  
public class SysCalendarService {   
 
	private final SysCalendarMapper sysCalendarMapper;  
 
	public SysCalendarService(SysCalendarMapper sysCalendarMapper) {   
		this.sysCalendarMapper = sysCalendarMapper;  
	}  
 
	@Transactional 
	public Map<String, Object> getListMap_sysCalendar(SysCalendarModel model) throws Exception { 
 
		Map<String, Object> resultMap = new HashMap<String, Object>(); 
 
		List<SysCalendarModel> rsList = sysCalendarMapper.getList_sysCalendar(model); 
		int totRow = sysCalendarMapper.getListCount_sysCalendar(model); 
 
		resultMap.put("rsList", rsList); 
		resultMap.put("totRow", totRow); 
 
		return resultMap; 
	} 
 
	public List<SysCalendarModel> getList_sysCalendar(SysCalendarModel model) {  
		return sysCalendarMapper.getList_sysCalendar(model);  
	}  
 
	public List<SysCalendarModel> subList_sysCalendar(SysCalendarModel model) {  
		return sysCalendarMapper.subList_sysCalendar(model);  
	}  
 
	public SysCalendarModel getMap_sysCalendar(SysCalendarModel model) {  
		SysCalendarModel rsModel =  sysCalendarMapper.getMap_sysCalendar(model);  
		if(rsModel == null) {   
			rsModel = new SysCalendarModel();   
		}   
		return  rsModel;  
	}  
 
	public int getCount_sysCalendar(SysCalendarModel model) {  
		return sysCalendarMapper.getCount_sysCalendar(model);  
	}  
 
	public void insert_sysCalendar(SysCalendarModel model) {  
		sysCalendarMapper.insert_sysCalendar(model);  
	}  
 
	public void update_sysCalendar(SysCalendarModel model) {  
		sysCalendarMapper.update_sysCalendar(model);  
	}  
 
	public void delete_sysCalendar(SysCalendarModel model) {  
		sysCalendarMapper.delete_sysCalendar(model);  
	}  
 
}