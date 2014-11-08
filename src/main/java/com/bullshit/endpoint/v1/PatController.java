package com.bullshit.endpoint.v1;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.apache.commons.lang3.StringUtils;
import org.glassfish.jersey.media.multipart.BodyPart;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bullshit.endpoint.entity.Account;
import com.bullshit.endpoint.entity.Cases;
import com.bullshit.endpoint.entity.Department;
import com.bullshit.endpoint.exception.ApiException;
import com.bullshit.endpoint.service.DocBusinessLogic;
import com.bullshit.endpoint.service.PatBusinessLogic;

@Component
@Path("/v1/pat")
public class PatController {
	Logger log = LoggerFactory.getLogger(PatController.class);

	@Autowired
	DocBusinessLogic docLogic;
	
	@Autowired
	PatBusinessLogic patLogic;	
	
	/* ### 获取所有科室的列表 */
	@GET
	@Path("/deptlist")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Department> getDepartmentList() throws ApiException {
		return patLogic.getDepartmentList();
	};

	/* ### 查找医生列表 */
	@GET
	@Path("/doclist/")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Account> getDoctorListByDepartId(
			@QueryParam("deptid") String did,
			@DefaultValue("1") @QueryParam("from") int fromNum,
			@DefaultValue("10") @QueryParam("to") int toNum)
			throws ApiException {
		List<Account> docList = new ArrayList<Account>();

		if (StringUtils.isNotEmpty(did)) {
			Account account = new Account();
			account.setId("13340855555");
			account.setName("王五");
			account.setImageurl("http://p0.qhimg.com/dmsmty/70_70_100/t016b4e0227f9b9b042.png");
			account.setAge("55");
			account.setDocTitle("主治医师");
			account.setDocProfessional("妇产，婴幼儿保健");
			account.setMobilePhone("18622345678");
			account.setTelPhone("0103456789");
			account.setDocDescription("2007年，毕业于中国医科大学毕业。");
			account.setDocDepartmentName("妇产科");

			Account account2 = new Account();
			account2.setId("13340857777");
			account2.setName("李七");
			account2.setImageurl("http://p0.qhimg.com/dmsmty/70_70_100/t016b4e0227f9b9b042.png");
			account2.setAge("77");
			account2.setDocTitle("教授");
			account2.setDocProfessional("妇产，婴幼儿保健");
			account2.setMobilePhone("18622345678");
			account2.setTelPhone("0103456789");
			account2.setDocDescription("留学海外多年，有多年临床经验");
			account2.setDocDepartmentName("妇产科");

			docList.add(account);
			docList.add(account2);
		} else {
			for (int i = 1; i < 4; i++) {
				Account account3 = new Account();
				account3.setId("13340857777");
				account3.setName("李七");
				account3.setImageurl("http://p0.qhimg.com/dmsmty/70_70_100/t016b4e0227f9b9b042.png");
				account3.setAge("77");
				account3.setDocTitle("教授");
				account3.setDocProfessional("妇产，婴幼儿保健");
				account3.setMobilePhone("18622345678");
				account3.setTelPhone("0103456789");
				account3.setDocDescription("留学海外多年，有多年临床经验");
				account3.setDocDepartmentName("妇产科");
				docList.add(account3);
			}

		}
		return docList;
	};
	
	/* 病人填写病例 */
	@POST
	@Path("/cases/upload")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_JSON)
	public String insertPatientCase(@FormParam("user_id") String user_id,
			@FormParam("message") String message, FormDataMultiPart multiPart)
			throws ApiException {
		for (BodyPart bodyPart : multiPart.getBodyParts()) {
			System.out.println(bodyPart.getEntityAs(String.class));
		}
		return null;
	};

	/* ### 通过病人id获取病例 */
	@GET
	@Path("/caseinfo/{patient_id}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Cases> getCaseInfoByPatientId(
			@PathParam("patient_id") String patient_id) throws ApiException {
		return patLogic.getCasesList(patient_id);
	};

}
