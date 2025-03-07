package ilike.ildrio.service.member;  
 
import egovframework.rte.psl.dataaccess.mapper.Mapper;  
import org.springframework.stereotype.Service;  
 
import java.util.List;  
import java.util.Map;  
import java.util.HashMap;  
 
import org.springframework.transaction.annotation.Transactional;  
 
import ilike.ildrio.model.member.MemberLicenseModel;  
import ilike.ildrio.mapper.member.MemberLicenseMapper;  
 
 
@Service  
public class MemberLicenseService {   
 
	private final MemberLicenseMapper memberLicenseMapper;  
 
	public MemberLicenseService(MemberLicenseMapper memberLicenseMapper) {   
		this.memberLicenseMapper = memberLicenseMapper;  
	}  
 
	@Transactional 
	public Map<String, Object> getListMap_memberLicense(MemberLicenseModel model) throws Exception { 
 
		Map<String, Object> resultMap = new HashMap<String, Object>(); 
 
		List<MemberLicenseModel> rsList = memberLicenseMapper.getList_memberLicense(model); 
		int totRow = memberLicenseMapper.getListCount_memberLicense(model); 
 
		resultMap.put("rsList", rsList); 
		resultMap.put("totRow", totRow); 
 
		return resultMap; 
	} 
 
	public List<MemberLicenseModel> getList_memberLicense(MemberLicenseModel model) {  
		return memberLicenseMapper.getList_memberLicense(model);  
	}  
 
	public List<MemberLicenseModel> subList_memberLicense(MemberLicenseModel model) {  
		return memberLicenseMapper.subList_memberLicense(model);  
	}  
 
	public MemberLicenseModel getMap_memberLicense(MemberLicenseModel model) {  
		MemberLicenseModel rsModel =  memberLicenseMapper.getMap_memberLicense(model);  
		if(rsModel == null) {   
			rsModel = new MemberLicenseModel();   
		}   
		return  rsModel;  
	}  
 
	public int getCount_memberLicense(MemberLicenseModel model) {  
		return memberLicenseMapper.getCount_memberLicense(model);  
	}  
 
	public void insert_memberLicense(MemberLicenseModel model) {  
		memberLicenseMapper.insert_memberLicense(model);  
	}  
 
	public void update_memberLicense(MemberLicenseModel model) {  
		memberLicenseMapper.update_memberLicense(model);  
	}  
 
	public void delete_memberLicense(MemberLicenseModel model) {  
		memberLicenseMapper.delete_memberLicense(model);  
	}  
 
}