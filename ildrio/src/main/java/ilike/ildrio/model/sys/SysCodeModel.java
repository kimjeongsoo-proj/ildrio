package ilike.ildrio.model.sys;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.LocalTime;
import ilike.ildrio.model.common.CommModel;

@Data
public class SysCodeModel extends CommModel {

	private String srhCodeId;
	private String srhCodeValue;

	private String codeId;
	private String codeValue;
	private String codeText;
	private String sortOrder = "0";
	private String codeMemo;
	private String insertDatetime;
	private String updateDatetime;
}