package ilike.ildrio.mapper.sys;  
 
import egovframework.rte.psl.dataaccess.mapper.Mapper;  
import java.util.List; 
 
import ilike.ildrio.model.sys.SysCalendarModel;
 
 
@Mapper  
public interface SysCalendarMapper {   
 
 
	int getListCount_sysCalendar(SysCalendarModel model); 
 
	List<SysCalendarModel> getList_sysCalendar(SysCalendarModel model); 
 
	List<SysCalendarModel> subList_sysCalendar(SysCalendarModel model); 
 
	SysCalendarModel getMap_sysCalendar(SysCalendarModel model); 
 
	int getCount_sysCalendar(SysCalendarModel model); 
 
	void insert_sysCalendar(SysCalendarModel model); 
 
	void update_sysCalendar(SysCalendarModel model); 
 
	void delete_sysCalendar(SysCalendarModel model); 
 
}