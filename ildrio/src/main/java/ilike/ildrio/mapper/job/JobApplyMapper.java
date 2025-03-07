package ilike.ildrio.mapper.job;  
 
import egovframework.rte.psl.dataaccess.mapper.Mapper;  
import java.util.List; 
 
import ilike.ildrio.model.job.JobApplyModel;
 
 
@Mapper  
public interface JobApplyMapper {   
 
 
	int getListCount_jobApply(JobApplyModel model); 
 
	List<JobApplyModel> getList_jobApply(JobApplyModel model); 
 
	List<JobApplyModel> subList_jobApply(JobApplyModel model); 
 
	JobApplyModel getMap_jobApply(JobApplyModel model); 
 
	int getCount_jobApply(JobApplyModel model); 
 
	void insert_jobApply(JobApplyModel model); 
 
	void update_jobApply(JobApplyModel model); 
 
	void delete_jobApply(JobApplyModel model); 
 
}