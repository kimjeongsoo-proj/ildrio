package ilike.ildrio.mapper.employment;  
 
import egovframework.rte.psl.dataaccess.mapper.Mapper;  
import java.util.List; 
 
import ilike.ildrio.model.employment.EmploymentMemberModel;
 
 
@Mapper  
public interface EmploymentMemberMapper {   
 
 
	int getListCount_employmentMember(EmploymentMemberModel model); 
 
	List<EmploymentMemberModel> getList_employmentMember(EmploymentMemberModel model); 
 
	List<EmploymentMemberModel> subList_employmentMember(EmploymentMemberModel model); 
 
	EmploymentMemberModel getMap_employmentMember(EmploymentMemberModel model); 
 
	int getCount_employmentMember(EmploymentMemberModel model); 
 
	void insert_employmentMember(EmploymentMemberModel model); 
 
	void update_employmentMember(EmploymentMemberModel model); 
 
	void delete_employmentMember(EmploymentMemberModel model); 
 
}