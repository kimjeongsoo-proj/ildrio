package ilike.ildrio.model.member;  
 
import lombok.Data;  
 
import java.time.LocalDateTime;  
import java.time.LocalTime;  
import ilike.ildrio.model.common.CommModel; 
 
 
 @Data   
 public class MemberLicenseModel extends CommModel {   
 
 
	private String memberNo;  
	private String licenseName;  
	private String licenseImage;  
	private String licenseMemo;  
	private String insertDatetime;  
	private String updateDatetime;  
 
 }