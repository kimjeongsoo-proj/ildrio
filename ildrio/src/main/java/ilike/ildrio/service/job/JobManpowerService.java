package ilike.ildrio.service.job;  
 
import egovframework.rte.psl.dataaccess.mapper.Mapper;  
import org.springframework.stereotype.Service;  
 
import java.util.List;  
import java.util.Map;  
import java.util.HashMap;  
 
import org.springframework.transaction.annotation.Transactional;  
 
import ilike.ildrio.model.job.JobManpowerModel;  
import ilike.ildrio.mapper.job.JobManpowerMapper;  
 
 
@Service  
public class JobManpowerService {   
 
	private final JobManpowerMapper jobManpowerMapper;  
 
	public JobManpowerService(JobManpowerMapper jobManpowerMapper) {   
		this.jobManpowerMapper = jobManpowerMapper;  
	}  
 
	@Transactional 
	public Map<String, Object> getListMap_jobManpower(JobManpowerModel model) throws Exception { 
 
		Map<String, Object> resultMap = new HashMap<String, Object>(); 
 
		List<JobManpowerModel> rsList = jobManpowerMapper.getList_jobManpower(model); 
		int totRow = jobManpowerMapper.getListCount_jobManpower(model); 
 
		resultMap.put("rsList", rsList); 
		resultMap.put("totRow", totRow); 
 
		return resultMap; 
	} 
 
	public List<JobManpowerModel> getList_jobManpower(JobManpowerModel model) {  
		return jobManpowerMapper.getList_jobManpower(model);  
	}  
 
	public List<JobManpowerModel> subList_jobManpower(JobManpowerModel model) {  
		return jobManpowerMapper.subList_jobManpower(model);  
	}  
 
	public JobManpowerModel getMap_jobManpower(JobManpowerModel model) {  
		JobManpowerModel rsModel =  jobManpowerMapper.getMap_jobManpower(model);  
		if(rsModel == null) {   
			rsModel = new JobManpowerModel();   
		}   
		return  rsModel;  
	}  
 
	public int getCount_jobManpower(JobManpowerModel model) {  
		return jobManpowerMapper.getCount_jobManpower(model);  
	}  
 
	public void insert_jobManpower(JobManpowerModel model) {  
		jobManpowerMapper.insert_jobManpower(model);  
	}  
 
	public void update_jobManpower(JobManpowerModel model) {  
		jobManpowerMapper.update_jobManpower(model);  
	}  
 
	public void delete_jobManpower(JobManpowerModel model) {  
		jobManpowerMapper.delete_jobManpower(model);  
	}  
 
	public void insert_copy_jobManpower(JobManpowerModel model) {  
		jobManpowerMapper.insert_copy_jobManpower(model);  
	}
}