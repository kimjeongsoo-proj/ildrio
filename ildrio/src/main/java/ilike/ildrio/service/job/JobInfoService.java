package ilike.ildrio.service.job;  
 
import egovframework.rte.psl.dataaccess.mapper.Mapper;  
import org.springframework.stereotype.Service;  
 
import java.util.List;  
import java.util.Map;  
import java.util.HashMap;  
 
import org.springframework.transaction.annotation.Transactional;  
 
import ilike.ildrio.model.job.JobInfoModel;  
import ilike.ildrio.mapper.job.JobInfoMapper;  
 
 
@Service  
public class JobInfoService {   
 
	private final JobInfoMapper jobInfoMapper;  
 
	public JobInfoService(JobInfoMapper jobInfoMapper) {   
		this.jobInfoMapper = jobInfoMapper;  
	}  
 
	@Transactional 
	public Map<String, Object> getListMap_jobInfo(JobInfoModel model) throws Exception { 
 
		Map<String, Object> resultMap = new HashMap<String, Object>(); 
 
		List<JobInfoModel> rsList = jobInfoMapper.getList_jobInfo(model); 
		int totRow = jobInfoMapper.getListCount_jobInfo(model); 
 
		resultMap.put("rsList", rsList); 
		resultMap.put("totRow", totRow); 
 
		return resultMap; 
	} 
 
	public List<JobInfoModel> getList_jobInfo(JobInfoModel model) {  
		return jobInfoMapper.getList_jobInfo(model);  
	}  
 
	public List<JobInfoModel> subList_jobInfo(JobInfoModel model) {  
		return jobInfoMapper.subList_jobInfo(model);  
	}  
 
	public JobInfoModel getMap_jobInfo(JobInfoModel model) {  
		JobInfoModel rsModel =  jobInfoMapper.getMap_jobInfo(model);  
		if(rsModel == null) {   
			rsModel = new JobInfoModel();   
		}   
		return  rsModel;  
	}  
 
	public int getCount_jobInfo(JobInfoModel model) {  
		return jobInfoMapper.getCount_jobInfo(model);  
	}  
 
	public void insert_jobInfo(JobInfoModel model) {  
		jobInfoMapper.insert_jobInfo(model);  
	}  
 
	public void update_jobInfo(JobInfoModel model) {  
		jobInfoMapper.update_jobInfo(model);  
	}  
 
	public void delete_jobInfo(JobInfoModel model) {  
		jobInfoMapper.delete_jobInfo(model);  
	}
	
	public void insert_copy_jobInfo(JobInfoModel model) {  
		jobInfoMapper.insert_copy_jobInfo(model);  
	} 
 
}