package ilike.ildrio.model.member;  
 
import lombok.Data;  
 
import java.time.LocalDateTime;  
import java.time.LocalTime;  
import ilike.ildrio.model.common.CommModel; 
 
 
 @Data   
 public class MemberInfoModel extends CommModel {   
 
 
	private String memberNo;  
	private String memberId;  
	private String password;  
	private String newPassword; 
	private String memberPassword;  
	private String memberPasswordDec;  
	private String memberName;  
	private String emailAddress;  
	private String mobileNo;  
	private String birthDate;  
	private String juminNo;  
	private String genderType;  
	private String postalNo;  
	private String postalAddress;  
	private String detailAddress;  
	private String photoImage;  
	private String signImage;  
	private String ageAgreeYn;  
	private String serviceAgreeYn;  
	private String personalAgreeYn;  
	private String oauthProvider;  
	private String oauthCertKey;  
	private String lastLoginDatetime;  
	private String passwordChangeDatetime;  
	private String cookieSession;  
	private String workType;  
	private String juminImage;  
	private String juminBackImage;  
	private String safeEducationYn;  
	private String safeEducationImage;  
	private String safeEducationHours;  
	private String foreignerYn;  
	private String stayStartDate;  
	private String stayEndDate;  
	private String agencyCompanyId;  
	private String agencyStatusCode;  
	private String bankName;  
	private String bankAccountNo;  
	private String bankAccountName;  
	private String insertDatetime;  
	private String updateDatetime; 
	
	private String juminImageFileName;  
	private String juminImageFileName2;  
	private String juminBackImageFileName;  
	private String photoImageFileName;  
	private String signImageFileName;  
	private String safeEducationImageFileName;  
	private String licenseImage1;  
	private String licenseImage1FileName;
	private String licenseImage2;  
	private String licenseImage2FileName;     
	
	private String certKey; 
	private String certNo; 
	private String careerGrade; 
	private String careerYears; 
	
	private String jikjongName; 
	private String[] jikjongNames; 
	
	private String memberType; 
	private String customerName; 
	private String companyId; 
	private String companyName; 
	
	private String manpowerCompanyId; 
	private String businessNo; 
	private String businessName; 
	private String businessTelno; 
	
	private String ceoName; 
	private String customerPostalAddress; 
	private String customerDetailAddress; 
	
 

	
 
 }