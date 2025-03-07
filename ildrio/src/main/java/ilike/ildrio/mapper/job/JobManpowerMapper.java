package ilike.ildrio.mapper.job;  
 
import egovframework.rte.psl.dataaccess.mapper.Mapper;  
import java.util.List; 
 
import ilike.ildrio.model.job.JobManpowerModel;
 
 
@Mapper  
public interface JobManpowerMapper {   
 
 
	int getListCount_jobManpower(JobManpowerModel model); 
 
	List<JobManpowerModel> getList_jobManpower(JobManpowerModel model); 
 
	List<JobManpowerModel> subList_jobManpower(JobManpowerModel model); 
 
	JobManpowerModel getMap_jobManpower(JobManpowerModel model); 
 
	int getCount_jobManpower(JobManpowerModel model); 
 
	void insert_jobManpower(JobManpowerModel model); 
 
	void update_jobManpower(JobManpowerModel model); 
 
	void delete_jobManpower(JobManpowerModel model); 
 
}