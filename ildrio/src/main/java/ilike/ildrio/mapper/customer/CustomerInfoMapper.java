package ilike.ildrio.mapper.customer;  
 
import egovframework.rte.psl.dataaccess.mapper.Mapper;  
import java.util.List; 
 
import ilike.ildrio.model.customer.CustomerInfoModel;
 
 
@Mapper  
public interface CustomerInfoMapper {   
 
 
	int getListCount_customerInfo(CustomerInfoModel model); 
 
	List<CustomerInfoModel> getList_customerInfo(CustomerInfoModel model); 
 
	List<CustomerInfoModel> subList_customerInfo(CustomerInfoModel model); 
 
	CustomerInfoModel getMap_customerInfo(CustomerInfoModel model); 
 
	int getCount_customerInfo(CustomerInfoModel model); 
 
	void insert_customerInfo(CustomerInfoModel model); 
 
	void update_customerInfo(CustomerInfoModel model); 
 
	void delete_customerInfo(CustomerInfoModel model); 
 
}