package ilike.ildrio.service.company;  
 
import egovframework.rte.psl.dataaccess.mapper.Mapper;  
import org.springframework.stereotype.Service;  
 
import java.util.List;  
import java.util.Map;  
import java.util.HashMap;  
 
import org.springframework.transaction.annotation.Transactional;  
 
import ilike.ildrio.model.company.CompanyInfoModel;  
import ilike.ildrio.mapper.company.CompanyInfoMapper;  
 
 
@Service  
public class CompanyInfoService {   
 
	private final CompanyInfoMapper companyInfoMapper;  
 
	public CompanyInfoService(CompanyInfoMapper companyInfoMapper) {   
		this.companyInfoMapper = companyInfoMapper;  
	}  
 
	@Transactional 
	public Map<String, Object> getListMap_companyInfo(CompanyInfoModel model) throws Exception { 
 
		Map<String, Object> resultMap = new HashMap<String, Object>(); 
 
		List<CompanyInfoModel> rsList = companyInfoMapper.getList_companyInfo(model); 
		int totRow = companyInfoMapper.getListCount_companyInfo(model); 
 
		resultMap.put("rsList", rsList); 
		resultMap.put("totRow", totRow); 
 
		return resultMap; 
	} 
 
	public List<CompanyInfoModel> getList_companyInfo(CompanyInfoModel model) {  
		return companyInfoMapper.getList_companyInfo(model);  
	}  
 
	public List<CompanyInfoModel> subList_companyInfo(CompanyInfoModel model) {  
		return companyInfoMapper.subList_companyInfo(model);  
	}  
 
	public CompanyInfoModel getMap_companyInfo(CompanyInfoModel model) {  
		CompanyInfoModel rsModel =  companyInfoMapper.getMap_companyInfo(model);  
		if(rsModel == null) {   
			rsModel = new CompanyInfoModel();   
		}   
		return  rsModel;  
	}  
 
	public int getCount_companyInfo(CompanyInfoModel model) {  
		return companyInfoMapper.getCount_companyInfo(model);  
	}  
 
	public void insert_companyInfo(CompanyInfoModel model) {  
		companyInfoMapper.insert_companyInfo(model);  
	}  
 
	public void update_companyInfo(CompanyInfoModel model) {  
		companyInfoMapper.update_companyInfo(model);  
	}  
 
	public void delete_companyInfo(CompanyInfoModel model) {  
		companyInfoMapper.delete_companyInfo(model);  
	}  
 
}