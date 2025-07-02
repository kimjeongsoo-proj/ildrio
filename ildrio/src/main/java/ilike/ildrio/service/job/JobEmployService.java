package ilike.ildrio.service.job;  
 
import egovframework.rte.psl.dataaccess.mapper.Mapper;  
import org.springframework.stereotype.Service;  
 
import java.util.List;  
import java.util.Map;  
import java.util.HashMap;  
 
import org.springframework.transaction.annotation.Transactional;  
 
import ilike.ildrio.model.job.JobEmployModel;  
import ilike.ildrio.mapper.job.JobEmployMapper;  
 
 
@Service  
public class JobEmployService {   
 
	private final JobEmployMapper jobApplyMapper;  
 
	public JobEmployService(JobEmployMapper jobApplyMapper) {   
		this.jobApplyMapper = jobApplyMapper;  
	}  
 
	@Transactional 
	public Map<String, Object> getListMap_jobEmploy(JobEmployModel model) throws Exception { 
 
		Map<String, Object> resultMap = new HashMap<String, Object>(); 
 
		List<JobEmployModel> rsList = jobApplyMapper.getList_jobEmploy(model); 
		int totRow = jobApplyMapper.getListCount_jobEmploy(model); 
 
		resultMap.put("rsList", rsList); 
		resultMap.put("totRow", totRow); 
 
		return resultMap; 
	} 
 
	public List<JobEmployModel> getList_jobEmploy(JobEmployModel model) {  
		return jobApplyMapper.getList_jobEmploy(model);  
	}  
 
	public List<JobEmployModel> subList_jobEmploy(JobEmployModel model) {  
		return jobApplyMapper.subList_jobEmploy(model);  
	}  
 
	public JobEmployModel getMap_jobEmploy(JobEmployModel model) {  
		JobEmployModel rsModel =  jobApplyMapper.getMap_jobEmploy(model);  
		if(rsModel == null) {   
			rsModel = new JobEmployModel();   
		}   
		return  rsModel;  
	}  
 
	public int getCount_jobEmploy(JobEmployModel model) {  
		return jobApplyMapper.getCount_jobEmploy(model);  
	}  
 
	public void insert_jobEmploy(JobEmployModel model) {  
		jobApplyMapper.insert_jobEmploy(model);  
	}  
 
	public void update_jobEmploy(JobEmployModel model) {  
		jobApplyMapper.update_jobEmploy(model);  
	}  
 
	public void delete_jobEmploy(JobEmployModel model) {  
		jobApplyMapper.delete_jobEmploy(model);  
	}  
 
}