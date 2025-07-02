package ilike.ildrio.mapper.job;  
 
import egovframework.rte.psl.dataaccess.mapper.Mapper;  
import java.util.List; 
 
import ilike.ildrio.model.job.JobInfoModel;
 
 
@Mapper  
public interface JobInfoMapper {   
 
 
	int getListCount_jobInfo(JobInfoModel model); 
 
	List<JobInfoModel> getList_jobInfo(JobInfoModel model); 
 
	List<JobInfoModel> subList_jobInfo(JobInfoModel model); 
 
	JobInfoModel getMap_jobInfo(JobInfoModel model); 
 
	int getCount_jobInfo(JobInfoModel model); 
 
	void insert_jobInfo(JobInfoModel model); 
 
	void update_jobInfo(JobInfoModel model); 
 
	void delete_jobInfo(JobInfoModel model); 
	
	void insert_copy_jobInfo(JobInfoModel model); 
 
}