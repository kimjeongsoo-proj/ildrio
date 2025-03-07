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
	private String customerNo;  
	private String companyNo;  
	private String jobTitle;  
	private String workDate;  
	private String jobStateCode;  
	private String taxType;  
	private String genderType;  
	private String minAge;  
	private String maxAge;  
	private String dailyWageInfo;  
	private String holidayNote;  
	private String mealProvideYn;  
	private String workStartTime;  
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
	private String onstructionCompanyNo;  
	private String jobDetail;  
	private String applyLimitHour;  
	private String adminMemo;  
	private String emergencyNoticeYn;  
	private String insertDatetime;  
	private String updateDatetime;  
 
 }