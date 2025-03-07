package ilike.ildrio.model.sys;  
 
import lombok.Data;  
 
import java.time.LocalDateTime;  
import java.time.LocalTime;  
import ilike.ildrio.model.common.CommModel; 
 
 
 @Data   
 public class SysCalendarModel extends CommModel {   
 
 
	private String calDate;  
	private String calYear;  
	private String calMonth;  
	private String calDay;  
	private String calWeekday;  
	private String holidayYn;  
	private String holidayName;  
 
 }