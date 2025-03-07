package ilike.ildrio.model.member;  
 
import lombok.Data;  
 
import java.time.LocalDateTime;  
import java.time.LocalTime;  
import ilike.ildrio.model.common.CommModel; 
 
 
 @Data   
 public class MemberTechModel extends CommModel {   
 
 
	private String memberNo;  
	private String techCode;  
	private String careerCode;  
	private String careerMemo;  
	private String recomandReceiveYn;  
	private String insertDatetime;  
	private String updateDatetime;  
 
 }