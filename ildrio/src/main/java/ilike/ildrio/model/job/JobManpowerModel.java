package ilike.ildrio.model.job;  
 
import lombok.Data;  
 
import java.time.LocalDateTime;  
import java.time.LocalTime;  
import ilike.ildrio.model.common.CommModel; 
 
 
 @Data   
 public class JobManpowerModel extends CommModel {   
 
	private String manpowerNo;   
	private String jobNo;  
	private String techCode;  
	private String careerCode;  
	private String manCount;  
	private String dailyWage;  
	private String requestWage;  
	private String commissionFee;  
	private String payWage;  
	
	private String jobApplyNo;
	private String applyDatetime;
	private String applyCount;
	private String employmentCount;
	private String applyStateCode;
	private String employmentStatCode;
	private String memberId;
	private String applyState;
	
	private String newJobNo;
	
 
 }