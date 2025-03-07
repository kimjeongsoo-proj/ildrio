package ilike.ildrio.service.employment;  
 
import egovframework.rte.psl.dataaccess.mapper.Mapper;  
import org.springframework.stereotype.Service;  
 
import java.util.List;  
import java.util.Map;  
import java.util.HashMap;  
 
import org.springframework.transaction.annotation.Transactional;  
 
import ilike.ildrio.model.employment.EmploymentPaymentModel;  
import ilike.ildrio.mapper.employment.EmploymentPaymentMapper;  
 
 
@Service  
public class EmploymentPaymentService {   
 
	private final EmploymentPaymentMapper employmentPaymentMapper;  
 
	public EmploymentPaymentService(EmploymentPaymentMapper employmentPaymentMapper) {   
		this.employmentPaymentMapper = employmentPaymentMapper;  
	}  
 
	@Transactional 
	public Map<String, Object> getListMap_employmentPayment(EmploymentPaymentModel model) throws Exception { 
 
		Map<String, Object> resultMap = new HashMap<String, Object>(); 
 
		List<EmploymentPaymentModel> rsList = employmentPaymentMapper.getList_employmentPayment(model); 
		int totRow = employmentPaymentMapper.getListCount_employmentPayment(model); 
 
		resultMap.put("rsList", rsList); 
		resultMap.put("totRow", totRow); 
 
		return resultMap; 
	} 
 
	public List<EmploymentPaymentModel> getList_employmentPayment(EmploymentPaymentModel model) {  
		return employmentPaymentMapper.getList_employmentPayment(model);  
	}  
 
	public List<EmploymentPaymentModel> subList_employmentPayment(EmploymentPaymentModel model) {  
		return employmentPaymentMapper.subList_employmentPayment(model);  
	}  
 
	public EmploymentPaymentModel getMap_employmentPayment(EmploymentPaymentModel model) {  
		EmploymentPaymentModel rsModel =  employmentPaymentMapper.getMap_employmentPayment(model);  
		if(rsModel == null) {   
			rsModel = new EmploymentPaymentModel();   
		}   
		return  rsModel;  
	}  
 
	public int getCount_employmentPayment(EmploymentPaymentModel model) {  
		return employmentPaymentMapper.getCount_employmentPayment(model);  
	}  
 
	public void insert_employmentPayment(EmploymentPaymentModel model) {  
		employmentPaymentMapper.insert_employmentPayment(model);  
	}  
 
	public void update_employmentPayment(EmploymentPaymentModel model) {  
		employmentPaymentMapper.update_employmentPayment(model);  
	}  
 
	public void delete_employmentPayment(EmploymentPaymentModel model) {  
		employmentPaymentMapper.delete_employmentPayment(model);  
	}  
 
}