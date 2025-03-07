package ilike.ildrio.model.company;  
 
import lombok.Data;  
 
import java.time.LocalDateTime;  
import java.time.LocalTime;  
import ilike.ildrio.model.common.CommModel; 
 
 
 @Data   
 public class CompanyInfoModel extends CommModel {   
 
 
	private String companyNo;  
	private String companyId;  
	private String companyPassword;  
	private String compayName;  
	private String businessNo;  
	private String ceoName;  
	private String postalNo;  
	private String postalAddress;  
	private String detailAddress;  
	private String insertDatetime;  
	private String updateDatetime;  
 
 }