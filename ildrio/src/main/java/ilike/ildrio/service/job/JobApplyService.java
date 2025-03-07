package ilike.ildrio.service.job;  
 
import egovframework.rte.psl.dataaccess.mapper.Mapper;  
import org.springframework.stereotype.Service;  
 
import java.util.List;  
import java.util.Map;  
import java.util.HashMap;  
 
import org.springframework.transaction.annotation.Transactional;  
 
import ilike.ildrio.model.job.JobApplyModel;  
import ilike.ildrio.mapper.job.JobApplyMapper;  
 
 
@Service  
public class JobApplyService {   
 
	private final JobApplyMapper jobApplyMapper;  
 
	public JobApplyService(JobApplyMapper jobApplyMapper) {   
		this.jobApplyMapper = jobApplyMapper;  
	}  
 
	@Transactional 
	public Map<String, Object> getListMap_jobApply(JobApplyModel model) throws Exception { 
 
		Map<String, Object> resultMap = new HashMap<String, Object>(); 
 
		List<JobApplyModel> rsList = jobApplyMapper.getList_jobApply(model); 
		int totRow = jobApplyMapper.getListCount_jobApply(model); 
 
		resultMap.put("rsList", rsList); 
		resultMap.put("totRow", totRow); 
 
		return resultMap; 
	} 
 
	public List<JobApplyModel> getList_jobApply(JobApplyModel model) {  
		return jobApplyMapper.getList_jobApply(model);  
	}  
 
	public List<JobApplyModel> subList_jobApply(JobApplyModel model) {  
		return jobApplyMapper.subList_jobApply(model);  
	}  
 
	public JobApplyModel getMap_jobApply(JobApplyModel model) {  
		JobApplyModel rsModel =  jobApplyMapper.getMap_jobApply(model);  
		if(rsModel == null) {   
			rsModel = new JobApplyModel();   
		}   
		return  rsModel;  
	}  
 
	public int getCount_jobApply(JobApplyModel model) {  
		return jobApplyMapper.getCount_jobApply(model);  
	}  
 
	public void insert_jobApply(JobApplyModel model) {  
		jobApplyMapper.insert_jobApply(model);  
	}  
 
	public void update_jobApply(JobApplyModel model) {  
		jobApplyMapper.update_jobApply(model);  
	}  
 
	public void delete_jobApply(JobApplyModel model) {  
		jobApplyMapper.delete_jobApply(model);  
	}  
 
}