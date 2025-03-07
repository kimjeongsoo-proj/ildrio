package ilike.ildrio.model.employment;  
 
import lombok.Data;  
 
import java.time.LocalDateTime;  
import java.time.LocalTime;  
import ilike.ildrio.model.common.CommModel; 
 
 
 @Data   
 public class EmploymentPaymentModel extends CommModel {   
 
	private String srhFromPaymentBaseDate = "";  
	private String srhToPaymentBaseDate = "";  
 
	private String jobNo;  
	private String memberNo;  
	private String paymentBaseDate;  
	private String workStartDate;  
	private String dailyWorkWage;  
	private String dailyCommison;  
	private String dailyRequestWage;  
	private String workDayCount;  
	private String paymentAmount;  
	private String taxAmount;  
	private String taxMemo;  
	private String paymentDate;  
	private String paymentStatus;  
	private String bankName;  
	private String bankAccountNo;  
	private String bankAccountName;  
	private String insertDatetime;  
	private String updateDatetime;  
 
 }