package ilike.ildrio.model.employment;  
 
import lombok.Data;  
 
import java.time.LocalDateTime;  
import java.time.LocalTime;  
import ilike.ildrio.model.common.CommModel; 
 
 
 @Data   
 public class EmploymentMemberModel extends CommModel {   
 
 
	private String jobNo;  
	private String techCode;  
	private String careerCode;  
	private String memberNo;  
	private String jobApplyNo;  
	private String dailyWage;  
	private String requestWage;  
	private String comissionFee;  
	private String payWage;  
	private String employmentMemo;  
	private String employmentStatus;  
	private String billingNo;  
	private String insertDatetime;  
	private String updateDatetime;  
 
 }