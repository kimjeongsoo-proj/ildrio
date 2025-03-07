package ilike.ildrio.service.employment;  
 
import egovframework.rte.psl.dataaccess.mapper.Mapper;  
import org.springframework.stereotype.Service;  
 
import java.util.List;  
import java.util.Map;  
import java.util.HashMap;  
 
import org.springframework.transaction.annotation.Transactional;  
 
import ilike.ildrio.model.employment.EmploymentMemberModel;  
import ilike.ildrio.mapper.employment.EmploymentMemberMapper;  
 
 
@Service  
public class EmploymentMemberService {   
 
	private final EmploymentMemberMapper employmentMemberMapper;  
 
	public EmploymentMemberService(EmploymentMemberMapper employmentMemberMapper) {   
		this.employmentMemberMapper = employmentMemberMapper;  
	}  
 
	@Transactional 
	public Map<String, Object> getListMap_employmentMember(EmploymentMemberModel model) throws Exception { 
 
		Map<String, Object> resultMap = new HashMap<String, Object>(); 
 
		List<EmploymentMemberModel> rsList = employmentMemberMapper.getList_employmentMember(model); 
		int totRow = employmentMemberMapper.getListCount_employmentMember(model); 
 
		resultMap.put("rsList", rsList); 
		resultMap.put("totRow", totRow); 
 
		return resultMap; 
	} 
 
	public List<EmploymentMemberModel> getList_employmentMember(EmploymentMemberModel model) {  
		return employmentMemberMapper.getList_employmentMember(model);  
	}  
 
	public List<EmploymentMemberModel> subList_employmentMember(EmploymentMemberModel model) {  
		return employmentMemberMapper.subList_employmentMember(model);  
	}  
 
	public EmploymentMemberModel getMap_employmentMember(EmploymentMemberModel model) {  
		EmploymentMemberModel rsModel =  employmentMemberMapper.getMap_employmentMember(model);  
		if(rsModel == null) {   
			rsModel = new EmploymentMemberModel();   
		}   
		return  rsModel;  
	}  
 
	public int getCount_employmentMember(EmploymentMemberModel model) {  
		return employmentMemberMapper.getCount_employmentMember(model);  
	}  
 
	public void insert_employmentMember(EmploymentMemberModel model) {  
		employmentMemberMapper.insert_employmentMember(model);  
	}  
 
	public void update_employmentMember(EmploymentMemberModel model) {  
		employmentMemberMapper.update_employmentMember(model);  
	}  
 
	public void delete_employmentMember(EmploymentMemberModel model) {  
		employmentMemberMapper.delete_employmentMember(model);  
	}  
 
}