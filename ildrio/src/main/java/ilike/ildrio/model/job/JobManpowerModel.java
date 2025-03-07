package ilike.ildrio.model.job;  
 
import lombok.Data;  
 
import java.time.LocalDateTime;  
import java.time.LocalTime;  
import ilike.ildrio.model.common.CommModel; 
 
 
 @Data   
 public class JobManpowerModel extends CommModel {   
 
 
	private String jobNo;  
	private String techCode;  
	private String careerCode;  
	private String manCount;  
	private String dailyWage;  
	private String requestWage;  
	private String comissionFee;  
	private String payWage;  
 
 }