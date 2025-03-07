package ilike.ildrio.controller.member;

import egovframework.rte.psl.dataaccess.mapper.Mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ilike.ildrio.common.EncryptionUtils;
import ilike.ildrio.common.SessionParam;
import ilike.ildrio.common.StringUtil;

import ilike.ildrio.service.common.CommonService;
import ilike.ildrio.model.member.MemberInfoModel;
import ilike.ildrio.service.member.MemberInfoService;

@RestController
public class ApiMemberInfoController {

	@Autowired
	CommonService commonService;

	@Autowired
	MemberInfoService memberInfoService;

	
}