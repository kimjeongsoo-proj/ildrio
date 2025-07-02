package ilike.ildrio.model.job;  
 
import lombok.Data;  
 
import java.time.LocalDateTime;  
import java.time.LocalTime;  
import ilike.ildrio.model.common.CommModel; 
 
 
 @Data   
 public class JobInfoModel extends CommModel {   
 
	private String srhFromWorkDate = "";  
	private String srhToWorkDate = "";  
 
	private String jobNo;  
	private String customerId;  
	private String companyId;  
	private String jobTitle;  
	private String workDate;
	private String workDateKorean;
	private String jobStateCode;  
	private String taxType;  
	private String taxEmploymentYn;  
	private String taxWithholdingYn;  
	private String taxChargeYn;  
	private String actualSalaryAmount; 
	private String genderType;  
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
	private String workplacePostalAddress;  
	private String workplaceDetailAddress;  
	private String workplaceNote;  
	private String workplaceImage;  
	private String workManagerName;  
	private String workManagerHpno;  
	private String workQualificationDetail;  
	private String workWelfareDetail;  
	private String bringThings;
	private String onstructionCompanyId;  
	private String jobDetail;  
	private String applyLimitHour;  
	private String adminMemo;  
	private String emergencyYn;  
	private String insertDatetime;  
	private String updateDatetime;
	private String workDateDiff;
	private String noticeCloseYn;
	
	private String manpowerList;  
	private String[] bringThingList;
	private String techCode;  
	private String careerCode; 
	private String jobNoticeState; 
	private String mealProvideType;
	private String businessName;
	
	private String newJobNo;
 
 }