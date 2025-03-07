package ilike.ildrio.model.job;  
 
import lombok.Data;  
 
import java.time.LocalDateTime;  
import java.time.LocalTime;  
import ilike.ildrio.model.common.CommModel; 
 
 
 @Data   
 public class JobApplyModel extends CommModel {   
 
 
	private String jobApplyNo;  
	private String jobNo;  
	private String techCode;  
	private String careerCode;  
	private String memberNo;  
	private String applyDatetime;  
 
 }