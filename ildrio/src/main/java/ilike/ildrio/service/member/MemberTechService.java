package ilike.ildrio.service.member;  
 
import egovframework.rte.psl.dataaccess.mapper.Mapper;  
import org.springframework.stereotype.Service;  
 
import java.util.List;  
import java.util.Map;  
import java.util.HashMap;  
 
import org.springframework.transaction.annotation.Transactional;  
 
import ilike.ildrio.model.member.MemberTechModel;  
import ilike.ildrio.mapper.member.MemberTechMapper;  
 
 
@Service  
public class MemberTechService {   
 
	private final MemberTechMapper memberTechMapper;  
 
	public MemberTechService(MemberTechMapper memberTechMapper) {   
		this.memberTechMapper = memberTechMapper;  
	}  
 
	@Transactional 
	public Map<String, Object> getListMap_memberTech(MemberTechModel model) throws Exception { 
 
		Map<String, Object> resultMap = new HashMap<String, Object>(); 
 
		List<MemberTechModel> rsList = memberTechMapper.getList_memberTech(model); 
		int totRow = memberTechMapper.getListCount_memberTech(model); 
 
		resultMap.put("rsList", rsList); 
		resultMap.put("totRow", totRow); 
 
		return resultMap; 
	} 
 
	public List<MemberTechModel> getList_memberTech(MemberTechModel model) {  
		return memberTechMapper.getList_memberTech(model);  
	}  
 
	public List<MemberTechModel> subList_memberTech(MemberTechModel model) {  
		return memberTechMapper.subList_memberTech(model);  
	}  
 
	public MemberTechModel getMap_memberTech(MemberTechModel model) {  
		MemberTechModel rsModel =  memberTechMapper.getMap_memberTech(model);  
		if(rsModel == null) {   
			rsModel = new MemberTechModel();   
		}   
		return  rsModel;  
	}  
 
	public int getCount_memberTech(MemberTechModel model) {  
		return memberTechMapper.getCount_memberTech(model);  
	}  
 
	public void insert_memberTech(MemberTechModel model) {  
		memberTechMapper.insert_memberTech(model);  
	}  
 
	public void update_memberTech(MemberTechModel model) {  
		memberTechMapper.update_memberTech(model);  
	}  
 
	public void delete_memberTech(MemberTechModel model) {  
		memberTechMapper.delete_memberTech(model);  
	}  
 
}