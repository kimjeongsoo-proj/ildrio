package ilike.ildrio.mapper.member;  
 
import egovframework.rte.psl.dataaccess.mapper.Mapper;  
import java.util.List; 
 
import ilike.ildrio.model.member.MemberTechModel;
 
 
@Mapper  
public interface MemberTechMapper {   
 
 
	int getListCount_memberTech(MemberTechModel model); 
 
	List<MemberTechModel> getList_memberTech(MemberTechModel model); 
 
	List<MemberTechModel> subList_memberTech(MemberTechModel model); 
 
	MemberTechModel getMap_memberTech(MemberTechModel model); 
 
	int getCount_memberTech(MemberTechModel model); 
 
	void insert_memberTech(MemberTechModel model); 
 
	void update_memberTech(MemberTechModel model); 
 
	void delete_memberTech(MemberTechModel model); 
 
}