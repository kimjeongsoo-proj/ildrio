package ilike.ildrio.mapper.member;  
 
import egovframework.rte.psl.dataaccess.mapper.Mapper;  
import java.util.List; 
 
import ilike.ildrio.model.member.MemberLicenseModel;
 
 
@Mapper  
public interface MemberLicenseMapper {   
 
 
	int getListCount_memberLicense(MemberLicenseModel model); 
 
	List<MemberLicenseModel> getList_memberLicense(MemberLicenseModel model); 
 
	List<MemberLicenseModel> subList_memberLicense(MemberLicenseModel model); 
 
	MemberLicenseModel getMap_memberLicense(MemberLicenseModel model); 
 
	int getCount_memberLicense(MemberLicenseModel model); 
 
	void insert_memberLicense(MemberLicenseModel model); 
 
	void update_memberLicense(MemberLicenseModel model); 
 
	void delete_memberLicense(MemberLicenseModel model); 
 
}