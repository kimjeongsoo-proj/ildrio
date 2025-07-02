package ilike.ildrio.mapper.job;  
 
import egovframework.rte.psl.dataaccess.mapper.Mapper;  
import java.util.List; 
 
import ilike.ildrio.model.job.JobEmployModel;
 
 
@Mapper  
public interface JobEmployMapper {   
 
 
	int getListCount_jobEmploy(JobEmployModel model); 
 
	List<JobEmployModel> getList_jobEmploy(JobEmployModel model); 
 
	List<JobEmployModel> subList_jobEmploy(JobEmployModel model); 
 
	JobEmployModel getMap_jobEmploy(JobEmployModel model); 
 
	int getCount_jobEmploy(JobEmployModel model); 
 
	void insert_jobEmploy(JobEmployModel model); 
 
	void update_jobEmploy(JobEmployModel model); 
 
	void delete_jobEmploy(JobEmployModel model);
 
}