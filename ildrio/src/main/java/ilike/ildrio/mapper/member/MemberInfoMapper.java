package ilike.ildrio.mapper.member;  
 
import egovframework.rte.psl.dataaccess.mapper.Mapper;  
import java.util.List; 
 
import ilike.ildrio.model.member.MemberInfoModel;
 
 
@Mapper  
public interface MemberInfoMapper {   
 
 
	int getListCount_memberInfo(MemberInfoModel model); 
 
	List<MemberInfoModel> getList_memberInfo(MemberInfoModel model); 
 
	List<MemberInfoModel> subList_memberInfo(MemberInfoModel model); 
 
	MemberInfoModel getMap_memberInfo(MemberInfoModel model); 
 
	int getCount_memberInfo(MemberInfoModel model); 
 
	void insert_memberInfo(MemberInfoModel model); 
 
	void update_memberInfo(MemberInfoModel model); 
 
	void delete_memberInfo(MemberInfoModel model); 
	
	void insert_memberMobileCert(MemberInfoModel model); 
	
	MemberInfoModel getMap_memberMobileCert(MemberInfoModel model); 
 
}