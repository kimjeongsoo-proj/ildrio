package ilike.ildrio.model.job;  
 
import lombok.Data;  
 
import java.time.LocalDateTime;  
import java.time.LocalTime;  
import ilike.ildrio.model.common.CommModel; 
 
 
 @Data   
 public class JobEmployModel extends CommModel {   
 
 
	private String jobNo;  
	private String jobApplyNo;  
	private String manpowerNo;  
	private String memberId;  
	private String memberName; 
	private String mobileNo; 
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
	private String customerId;
	private String customerName;
	private String companyId;
	private String jikjongName;
	private String careerGrade;
	private String birthDate;
	private String genderType;
	private String postalAddress;
	private String dailyWage;
	private String workDateDiff;
	private String workplacePostalAddress;
	
	private String manCount;
	private String memberAge;
	private String employmentCount;
	private String attendMemo;
	private String attendStartCcode;
	private String attendEndCcode;
	
	private String workDateKorean;
	private String jobStateCode;  
	private String taxType;  
	private String taxEmploymentYn;  
	private String taxWithholdingYn;  
	private String taxChargeYn;  
	private String actualSalaryAmount; 
	private String minAge;  
	private String maxAge;  
	private String dailyWageInfo;  
	private String holidayNote;  
	private String ealProvideType;  
	private String workStartHour;
	private String workStartMinute;
	private String workStartTime;  
	private String workEndHour;  
	private String workEndMinute;  
	private String workEndTime;  
	private String workplaceCompanyName;  
	private String workplacePostalNo;  
	private String workplaceDetailAddress;  
	


 
 }