package ilike.ildrio.service.member;  
 
import egovframework.rte.psl.dataaccess.mapper.Mapper;  
import org.springframework.stereotype.Service;  
 
import java.util.List;  
import java.util.Map;  
import java.util.HashMap;  
 
import org.springframework.transaction.annotation.Transactional;  
 
import ilike.ildrio.model.member.MemberInfoModel;  
import ilike.ildrio.mapper.member.MemberInfoMapper;  
 
 
@Service  
public class MemberInfoService {   
 
	private final MemberInfoMapper memberInfoMapper;  
 
	public MemberInfoService(MemberInfoMapper memberInfoMapper) {   
		this.memberInfoMapper = memberInfoMapper;  
	}  
 
	@Transactional 
	public Map<String, Object> getListMap_memberInfo(MemberInfoModel model) throws Exception { 
 
		Map<String, Object> resultMap = new HashMap<String, Object>(); 
 
		List<MemberInfoModel> rsList = memberInfoMapper.getList_memberInfo(model); 
		int totRow = memberInfoMapper.getListCount_memberInfo(model); 
 
		resultMap.put("rsList", rsList); 
		resultMap.put("totRow", totRow); 
 
		return resultMap; 
	} 
 
	public List<MemberInfoModel> getList_memberInfo(MemberInfoModel model) {  
		return memberInfoMapper.getList_memberInfo(model);  
	}  
 
	public List<MemberInfoModel> subList_memberInfo(MemberInfoModel model) {  
		return memberInfoMapper.subList_memberInfo(model);  
	}  
 
	public MemberInfoModel getMap_memberInfo(MemberInfoModel model) {  
		MemberInfoModel rsModel =  memberInfoMapper.getMap_memberInfo(model);  
		if(rsModel == null) {   
			rsModel = new MemberInfoModel();   
		}   
		return  rsModel;  
	}  
 
	public int getCount_memberInfo(MemberInfoModel model) {  
		return memberInfoMapper.getCount_memberInfo(model);  
	}  
 
	public void insert_memberInfo(MemberInfoModel model) {  
		memberInfoMapper.insert_memberInfo(model);  
	}  
 
	public void update_memberInfo(MemberInfoModel model) {  
		memberInfoMapper.update_memberInfo(model);  
	}  
 
	public void delete_memberInfo(MemberInfoModel model) {  
		memberInfoMapper.delete_memberInfo(model);  
	}  
 
	public MemberInfoModel getMap_memberMobileCert(MemberInfoModel model) {  
		MemberInfoModel rsModel =  memberInfoMapper.getMap_memberMobileCert(model);  
		if(rsModel == null) {   
			rsModel = new MemberInfoModel();   
		}   
		return  rsModel;  
	} 
	
	public void insert_memberMobileCert(MemberInfoModel model) {  
		memberInfoMapper.insert_memberMobileCert(model);  
	} 
}