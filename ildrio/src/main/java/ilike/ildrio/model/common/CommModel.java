package ilike.ildrio.model.common;

import lombok.Data;

@Data
public class CommModel {

	private String shopId;
	private String shopName;
	
	private String currPage;
	private String totRow;
	private String pageRow;
	private String pageRows = "20";
	private String startRow = "0"; 
	private String pageBlock = "10";
	private String totalRowCount;

	private String lastPage;
	private String pageNo;

	private String srhOption;
	private String srhText; 
	private String srhTotalText;
	
	private String srhOrderName;
	private String srhOrderBy;
	private String srhOrderAsc;
	
	private String srhDateOption;
	private String srhFromDate;
	private String srhToDate;

	private String srhNumberOption;
	private String srhFromNumber;
	private String srhToNumber;

	private String trxnMode;
	private String trxnType;
	private String selectMode;
	private String selectType;
	private String updateType;
	private String insertType;
	private String deleteType;
	private String popupMode;
	private String popupParam;
	
	private String selectedRowIdx;
	private String menuCode;
	private String readonlyMode;
	
	

}
