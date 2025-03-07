package ilike.ildrio.service.employment;  
 
import egovframework.rte.psl.dataaccess.mapper.Mapper;  
import org.springframework.stereotype.Service;  
 
import java.util.List;  
import java.util.Map;  
import java.util.HashMap;  
 
import org.springframework.transaction.annotation.Transactional;  
 
import ilike.ildrio.model.employment.EmploymentAttendanceModel;  
import ilike.ildrio.mapper.employment.EmploymentAttendanceMapper;  
 
 
@Service  
public class EmploymentAttendanceService {   
 
	private final EmploymentAttendanceMapper employmentAttendanceMapper;  
 
	public EmploymentAttendanceService(EmploymentAttendanceMapper employmentAttendanceMapper) {   
		this.employmentAttendanceMapper = employmentAttendanceMapper;  
	}  
 
	@Transactional 
	public Map<String, Object> getListMap_employmentAttendance(EmploymentAttendanceModel model) throws Exception { 
 
		Map<String, Object> resultMap = new HashMap<String, Object>(); 
 
		List<EmploymentAttendanceModel> rsList = employmentAttendanceMapper.getList_employmentAttendance(model); 
		int totRow = employmentAttendanceMapper.getListCount_employmentAttendance(model); 
 
		resultMap.put("rsList", rsList); 
		resultMap.put("totRow", totRow); 
 
		return resultMap; 
	} 
 
	public List<EmploymentAttendanceModel> getList_employmentAttendance(EmploymentAttendanceModel model) {  
		return employmentAttendanceMapper.getList_employmentAttendance(model);  
	}  
 
	public List<EmploymentAttendanceModel> subList_employmentAttendance(EmploymentAttendanceModel model) {  
		return employmentAttendanceMapper.subList_employmentAttendance(model);  
	}  
 
	public EmploymentAttendanceModel getMap_employmentAttendance(EmploymentAttendanceModel model) {  
		EmploymentAttendanceModel rsModel =  employmentAttendanceMapper.getMap_employmentAttendance(model);  
		if(rsModel == null) {   
			rsModel = new EmploymentAttendanceModel();   
		}   
		return  rsModel;  
	}  
 
	public int getCount_employmentAttendance(EmploymentAttendanceModel model) {  
		return employmentAttendanceMapper.getCount_employmentAttendance(model);  
	}  
 
	public void insert_employmentAttendance(EmploymentAttendanceModel model) {  
		employmentAttendanceMapper.insert_employmentAttendance(model);  
	}  
 
	public void update_employmentAttendance(EmploymentAttendanceModel model) {  
		employmentAttendanceMapper.update_employmentAttendance(model);  
	}  
 
	public void delete_employmentAttendance(EmploymentAttendanceModel model) {  
		employmentAttendanceMapper.delete_employmentAttendance(model);  
	}  
 
}