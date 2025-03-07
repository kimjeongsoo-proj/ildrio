package ilike.ildrio.model.sys;  
 
import lombok.Data;  
 
import java.time.LocalDateTime;  
import java.time.LocalTime;  
import ilike.ildrio.model.common.CommModel; 
 
 
 @Data   
 public class SysMenuModel extends CommModel {   
 
 
	private String groupCode;  
	private String menuCode;  
	private String menuUpCode  = "0";  
	private String menuLevel = "0";  
	private String namePath;  
	private String menuSort = "0";  
	private String menuName;  
	private String menuIcon;  
	private String menuCode1;  
	private String menuCode2;  
	private String menuCode3;  
	private String menuCode4;  
	private String menuCode5;  
	private String codePath;  
	private String sortPath;  
	private String linkUrl;  
	private String defaultCode;  
	private String targetType;  
	private String useYn;  
	private String helpHtml;  
	private String insertDatetime;  
	private String updateDatetime; 
	private String subCount; 
	
	private String menuNewCode; 
	private String srhMenuLevel; 
	private String nowSort; 
 
 }