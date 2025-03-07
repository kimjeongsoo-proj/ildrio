package ilike.ildrio.mapper.employment;  
 
import egovframework.rte.psl.dataaccess.mapper.Mapper;  
import java.util.List; 
 
import ilike.ildrio.model.employment.EmploymentPaymentModel;
 
 
@Mapper  
public interface EmploymentPaymentMapper {   
 
 
	int getListCount_employmentPayment(EmploymentPaymentModel model); 
 
	List<EmploymentPaymentModel> getList_employmentPayment(EmploymentPaymentModel model); 
 
	List<EmploymentPaymentModel> subList_employmentPayment(EmploymentPaymentModel model); 
 
	EmploymentPaymentModel getMap_employmentPayment(EmploymentPaymentModel model); 
 
	int getCount_employmentPayment(EmploymentPaymentModel model); 
 
	void insert_employmentPayment(EmploymentPaymentModel model); 
 
	void update_employmentPayment(EmploymentPaymentModel model); 
 
	void delete_employmentPayment(EmploymentPaymentModel model); 
 
}