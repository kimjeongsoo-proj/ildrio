package ilike.ildrio.mapper.sys;  
 
import egovframework.rte.psl.dataaccess.mapper.Mapper;  
import java.util.List; 
 
import ilike.ildrio.model.sys.SysMenuModel;
 
 
@Mapper  
public interface SysMenuMapper {   
 
 
	int getListCount_sysMenu(SysMenuModel model); 
 
	List<SysMenuModel> getList_sysMenu(SysMenuModel model); 
 
	List<SysMenuModel> subList_sysMenu(SysMenuModel model); 
 
	SysMenuModel getMap_sysMenu(SysMenuModel model); 
 
	int getCount_sysMenu(SysMenuModel model); 
 
	void insert_sysMenu(SysMenuModel model); 
 
	void update_sysMenu(SysMenuModel model); 
 
	void delete_sysMenu(SysMenuModel model); 
 
}