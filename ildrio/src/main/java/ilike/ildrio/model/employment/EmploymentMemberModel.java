package ilike.ildrio.model.employment;

import lombok.Data;

import java.time.LocalDateTime;
import java.time.LocalTime;
import ilike.ildrio.model.common.CommModel;

@Data
public class EmploymentMemberModel extends CommModel {

	private String jobNo;
	private String jobApplyNo;
	private String manpowerNo;
	private String memberId;
	private String memberNo;
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
	private String memberName;
	private String jikjongName;
	private String careerGrade;
	private String birthDate;
	private String genderType;
	private String postalAddress;
	private String dailyWage;
	
	private String workplacePostalAddress;
	private String workplaceDetailAddress;

}