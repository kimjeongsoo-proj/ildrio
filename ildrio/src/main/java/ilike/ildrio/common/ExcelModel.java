package ilike.ildrio.common;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.util.CellRangeAddress;

public class ExcelModel implements Serializable {

	private static final long serialVersionUID = 2929177538715832779L;

	private String title;
	private String caption;
	private String[] headerGroup;
	private String[][] header;
	private CellRangeAddress[] merge;
	private List<Map<String, Object>> itemList;
	private boolean showRowNum;
	private String summaryRowColumn;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	public String[] getHeaderGroup() {
		return headerGroup;
	}

	public void setHeaderGroup(String[] headerGroup) {
		this.headerGroup = headerGroup;
	}

	public String[][] getHeader() {
		return header;
	}

	public void setHeader(String[][] header) {
		this.header = header;
	}

	public CellRangeAddress[] getMerge() {
		return merge;
	}

	public void setMerge(CellRangeAddress[] merge) {
		this.merge = merge;
	}

	public List<Map<String, Object>> getItemList() {
		return itemList;
	}

	public void setItemList(List<Map<String, Object>> itemList) {
		this.itemList = itemList;
	}

	public boolean isShowRowNum() {
		return showRowNum;
	}

	public void setShowRowNum(boolean showRowNum) {
		this.showRowNum = showRowNum;
	}

	public String getSummaryRowColumn() {
		return summaryRowColumn;
	}

	public void setSummaryRowColumn(String summaryRowColumn) {
		this.summaryRowColumn = summaryRowColumn;
	}

	@Override
	public String toString() {
		return "excelJspFilel [" + (title != null ? "title=" + title + ", " : "")
				+ (caption != null ? "caption=" + caption + ", " : "")
				+ (headerGroup != null ? "headerGroup=" + Arrays.toString(headerGroup) + ", " : "")
				+ (header != null ? "header=" + Arrays.toString(header) + ", " : "")
				+ (merge != null ? "merge=" + Arrays.toString(merge) + ", " : "")
				+ (itemList != null ? "itemList=" + itemList + ", " : "") + "showRowNum=" + showRowNum + "]";
	}

}
