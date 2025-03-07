package ilike.ildrio.model.employment;  
 
import lombok.Data;  
 
import java.time.LocalDateTime;  
import java.time.LocalTime;  
import ilike.ildrio.model.common.CommModel; 
 
 
 @Data   
 public class EmploymentAttendanceModel extends CommModel {   
 
 
	private String jobNo;  
	private String workDate;  
	private String memberNo;  
	private String departDatetime;  
	private String departStatus;  
	private String departMessage;  
	private String attendanceStatus;  
	private String attendanceTime;  
	private String leaveStatus;  
	private String leaveTime;  
	private String leaveMemo;  
	private String workHourCount;  
	private String workMemo;  
	private String penaltyCode;  
	private String penaltyMemo;  
	private String insertDatetime;  
	private String updateDatetime;  
 
 }