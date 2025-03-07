package ilike.ildrio.service.customer;  
 
import egovframework.rte.psl.dataaccess.mapper.Mapper;  
import org.springframework.stereotype.Service;  
 
import java.util.List;  
import java.util.Map;  
import java.util.HashMap;  
 
import org.springframework.transaction.annotation.Transactional;  
 
import ilike.ildrio.model.customer.CustomerInfoModel;  
import ilike.ildrio.mapper.customer.CustomerInfoMapper;  
 
 
@Service  
public class CustomerInfoService {   
 
	private final CustomerInfoMapper customerInfoMapper;  
 
	public CustomerInfoService(CustomerInfoMapper customerInfoMapper) {   
		this.customerInfoMapper = customerInfoMapper;  
	}  
 
	@Transactional 
	public Map<String, Object> getListMap_customerInfo(CustomerInfoModel model) throws Exception { 
 
		Map<String, Object> resultMap = new HashMap<String, Object>(); 
 
		List<CustomerInfoModel> rsList = customerInfoMapper.getList_customerInfo(model); 
		int totRow = customerInfoMapper.getListCount_customerInfo(model); 
 
		resultMap.put("rsList", rsList); 
		resultMap.put("totRow", totRow); 
 
		return resultMap; 
	} 
 
	public List<CustomerInfoModel> getList_customerInfo(CustomerInfoModel model) {  
		return customerInfoMapper.getList_customerInfo(model);  
	}  
 
	public List<CustomerInfoModel> subList_customerInfo(CustomerInfoModel model) {  
		return customerInfoMapper.subList_customerInfo(model);  
	}  
 
	public CustomerInfoModel getMap_customerInfo(CustomerInfoModel model) {  
		CustomerInfoModel rsModel =  customerInfoMapper.getMap_customerInfo(model);  
		if(rsModel == null) {   
			rsModel = new CustomerInfoModel();   
		}   
		return  rsModel;  
	}  
 
	public int getCount_customerInfo(CustomerInfoModel model) {  
		return customerInfoMapper.getCount_customerInfo(model);  
	}  
 
	public void insert_customerInfo(CustomerInfoModel model) {  
		customerInfoMapper.insert_customerInfo(model);  
	}  
 
	public void update_customerInfo(CustomerInfoModel model) {  
		customerInfoMapper.update_customerInfo(model);  
	}  
 
	public void delete_customerInfo(CustomerInfoModel model) {  
		customerInfoMapper.delete_customerInfo(model);  
	}  
 
}