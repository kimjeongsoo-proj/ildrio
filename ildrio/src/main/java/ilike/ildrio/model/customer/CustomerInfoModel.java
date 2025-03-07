package ilike.ildrio.model.customer;  
 
import lombok.Data;  
 
import java.time.LocalDateTime;  
import java.time.LocalTime;  
import ilike.ildrio.model.common.CommModel; 
 
 
 @Data   
 public class CustomerInfoModel extends CommModel {   
 
 
	private String customerNo;  
	private String companyNo;  
	private String customerId;  
	private String customerPassword;  
	private String customerName;  
	private String businessNo;  
	private String ceoName;  
	private String postalNo;  
	private String postalAddress;  
	private String detailAddress;  
	private String paymentDay;  
	private String insertDatetime;  
	private String updateDatetime;  
 
 }