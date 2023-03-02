package com.droppa.webapi.DroppaServices.restApi;

import java.util.List;

import javax.ejb.EJB;

import com.droppa.webapi.DroppaServices.DTO.CompanyDTO;
import com.droppa.webapi.DroppaServices.bean.CompanyService;
import com.droppa.webapi.DroppaServices.pojo.Company;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("companies")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CompanyRestApi {
	@EJB
	CompanyService companyService = new CompanyService();

	@POST
	@Path("/createcompany")
	public Response createCompany(CompanyDTO companyDTO) {
		Company company = companyService.createCompany(companyDTO);
		return Response.ok().entity(company).build();
	}
	
//	@GET
//	@Path("/getcompany/{id}")
//	public Response getCompanyById(@PathParam("id") String id) {
//		Company company = companyService.getCompanyById(id);
//		return Response.ok().entity(company).build();
//	}
	
	@GET
	@Path("/viewallcompanies")
	public Response viewAllCompanies() {
		List<Company> company = companyService.viewAllCompanies();
		return Response.ok().entity(company).build();
	}
}
