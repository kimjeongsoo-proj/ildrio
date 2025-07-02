package ilike.ildrio.model.company;  
 
import lombok.Data;  
 
import java.time.LocalDateTime;  
import java.time.LocalTime;  
import ilike.ildrio.model.common.CommModel; 
 
 
 @Data   
 public class CompanyInfoModel extends CommModel {   
 
 
	private String companyId;  
	private String companyName;  
	private String businessNo;  
	private String ceoName;  
	private String postalNo;  
	private String postalAddress;  
	private String detailAddress;  
	private String siteId;  
	private String insertDatetime;  
	private String updateDatetime;  
 
 }