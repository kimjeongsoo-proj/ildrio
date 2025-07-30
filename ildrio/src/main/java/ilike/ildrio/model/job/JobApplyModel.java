package ilike.ildrio.model.job;  
 
import lombok.Data;  
 
import java.time.LocalDateTime;  
import java.time.LocalTime;  
import ilike.ildrio.model.common.CommModel; 
 
 
 @Data   
 public class JobApplyModel extends CommModel {   
 
 
	private String jobNo;  
	private String jobApplyNo;  
	private String manpowerNo;  
	private String memberId;  
	private String applyDatetime;  
	private String employmentDatetime;  
	private String employmentStateCode;  
	private String employmentMessage;  
	
	private String applyLimitHour; 
	private String applyLimitDate; 
	private String applyState; 

	private String workDate;
	private String jobTitle;
	private String techCode;
	private String careerCode;
	private String applyStateCode;
	private String employmentStatus;
	private String attendDepartDatetime;
	private String attendStartHhmm;
	private String attendEndHhmm;
	private String attendStatusCode;
	private String customerId;
	private String customerName;
	private String companyId;
	private String memberName;
	private String jikjongName;
	private String careerGrade;
	private String birthDate;
	private String genderType;
	private String postalAddress;
	private String dailyWage;
	private String workDateDiff;
	private String workplacePostalAddress;
	private String photoImageFileName;
	
	private String manCount;
	private String memberAge;
	private String employmentCount;
	private String attendMemo;
	private String attendStartCode;
	private String attendEndCode;
	
	private String businessName;
	private String manpowerCompanyId;
	private String mobileNo;
	
	private String emergencyYn;
	private String jobNoticeState;
	private String workDateKorean;
	private String workStartHour;
	private String workStartMinute;
	private String workEndHour;
	private String workEndMinute;


 
 }