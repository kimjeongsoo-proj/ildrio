package ilike.ildrio.mapper.employment;  
 
import egovframework.rte.psl.dataaccess.mapper.Mapper;  
import java.util.List; 
 
import ilike.ildrio.model.employment.EmploymentAttendanceModel;
 
 
@Mapper  
public interface EmploymentAttendanceMapper {   
 
 
	int getListCount_employmentAttendance(EmploymentAttendanceModel model); 
 
	List<EmploymentAttendanceModel> getList_employmentAttendance(EmploymentAttendanceModel model); 
 
	List<EmploymentAttendanceModel> subList_employmentAttendance(EmploymentAttendanceModel model); 
 
	EmploymentAttendanceModel getMap_employmentAttendance(EmploymentAttendanceModel model); 
 
	int getCount_employmentAttendance(EmploymentAttendanceModel model); 
 
	void insert_employmentAttendance(EmploymentAttendanceModel model); 
 
	void update_employmentAttendance(EmploymentAttendanceModel model); 
 
	void delete_employmentAttendance(EmploymentAttendanceModel model); 
 
}