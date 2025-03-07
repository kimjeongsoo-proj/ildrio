package ilike.ildrio.mapper.sys;

 
import ilike.ildrio.model.sys.SysCodeModel;
import egovframework.rte.psl.dataaccess.mapper.Mapper;
import java.util.List;

@Mapper
public interface SysCodeMapper {

	List<SysCodeModel> getList_sysCode(SysCodeModel sysCodeModel);

	SysCodeModel getMap_sysCode(SysCodeModel sysCodeModel);

	int getChkCnt_sysCode(SysCodeModel sysCodeModel);

	void insert_sysCode(SysCodeModel sysCodeModel);

	void update_sysCode(SysCodeModel sysCodeModel);

	void delete_sysCode(SysCodeModel sysCodeModel);
}