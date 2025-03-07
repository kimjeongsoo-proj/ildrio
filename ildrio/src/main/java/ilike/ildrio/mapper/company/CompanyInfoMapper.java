package ilike.ildrio.mapper.company;  
 
import egovframework.rte.psl.dataaccess.mapper.Mapper;  
import java.util.List; 
 
import ilike.ildrio.model.company.CompanyInfoModel;
 
 
@Mapper  
public interface CompanyInfoMapper {   
 
 
	int getListCount_companyInfo(CompanyInfoModel model); 
 
	List<CompanyInfoModel> getList_companyInfo(CompanyInfoModel model); 
 
	List<CompanyInfoModel> subList_companyInfo(CompanyInfoModel model); 
 
	CompanyInfoModel getMap_companyInfo(CompanyInfoModel model); 
 
	int getCount_companyInfo(CompanyInfoModel model); 
 
	void insert_companyInfo(CompanyInfoModel model); 
 
	void update_companyInfo(CompanyInfoModel model); 
 
	void delete_companyInfo(CompanyInfoModel model); 
 
}