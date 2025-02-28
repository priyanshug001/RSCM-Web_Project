package com.RSCM.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.RSCM.models.AdminLoginModel;
import com.RSCM.models.AdminRegistrationModel;
import com.RSCM.models.ContactModel;
import com.RSCM.models.EditIndexPageContentModel;
import com.RSCM.models.RecruitmentModel;
import com.RSCM.models.UpdateAboutPageContentModel;
import com.RSCM.models.UpdateCareerPageModel;
import com.RSCM.models.UpdateServicesModel;
import com.RSCM.models.UpdatesModel;
import com.RSCM.services.AboutPageServices;
import com.RSCM.services.AdminLoginService;
import com.RSCM.services.AdminRegistrationService;
import com.RSCM.services.CareerPageServices;
import com.RSCM.services.ContactService;
import com.RSCM.services.EditIndexPageServices;
import com.RSCM.services.RecruitmentServices;
import com.RSCM.services.ServicePageServices;
import com.RSCM.services.UpdatesServices;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RestController
public class MainController {

	// All Services Instances

	@Autowired
	EditIndexPageServices editIndexPageServices;

	@Autowired
	ServicePageServices servicePageServices;

	@Autowired
	AboutPageServices aboutPageServices;

	@Autowired
	CareerPageServices careerPageServices;

	@Autowired
	RecruitmentServices recruitmentServices;

	@Autowired
	AdminLoginService adminLoginService;

	@Autowired
	ContactService contactService;

	@Autowired
	UpdatesServices updatesService;

	@Autowired
	AdminRegistrationService adminRegistrationService;

	// All Models Instances

	EditIndexPageContentModel editIndexPageContentModel = new EditIndexPageContentModel();

	UpdateServicesModel updateServicesModel = new UpdateServicesModel();

	UpdateCareerPageModel updateCareerPageModel = new UpdateCareerPageModel();

	UpdateAboutPageContentModel updateAboutPageContentModel = new UpdateAboutPageContentModel();

	RecruitmentModel recruitmentModel = new RecruitmentModel();

	AdminLoginModel adminLoginModel = new AdminLoginModel();

	UpdatesModel updatesModel = new UpdatesModel();

//	AdminRegistrationModel adminRegistrationModel = new AdminRegistrationModel();

// Main Admin Login Credentials...

	public String mainAdminLoginUsername = "mainadminrscmpcr";
	public String mainAdminLoginPassword = "mainadminrscmpcr";

// Some Functions for Fetch Data Save And other FUnctionalities like 
	// IndexPageData // AllUpdatesData // UpdatedServicesData //
	// SpecificAdminLoginData // UpdatedAboutPageData // CareerPageData
	// ContactData // AdminRegistrationData //SpecificUpdatedServicesData //
	// AdminLoginData

	public void returnIndexPageData(HttpServletRequest httpServletRequest) {
		this.editIndexPageContentModel = this.editIndexPageServices.getIndexPageContentData();
		httpServletRequest.setAttribute("DataForLoadIndexPage", editIndexPageContentModel);
	}

	public void returnAllUpdatesData(HttpServletRequest httpServletRequest) {
		List<UpdatesModel> list = this.updatesService.GetAllUpdatesData();
		httpServletRequest.setAttribute("AllUpdatesData", list);
	}

	public void returnUpdatedServicesData(HttpServletRequest httpServletRequest) {
		List<UpdateServicesModel> list = this.servicePageServices.GetUpdatedServiceData();
		httpServletRequest.setAttribute("UpdatedServicesData", list);
	}

	public void returnSpecificAdminLoginData(HttpServletRequest httpServletRequest, String fullname) {
		List<AdminLoginModel> list = this.adminLoginService.GetSpecificAdminLoginData(fullname);
		httpServletRequest.setAttribute("SpecificAdminLoginData", list);
		httpServletRequest.setAttribute("SpecificAdminLoginCount", list.size());
	}

	public void returnUpdatedAboutPageData(HttpServletRequest httpServletRequest) {
		this.updateAboutPageContentModel = this.aboutPageServices.GetUpdatedAboutPageData();
		httpServletRequest.setAttribute("UpdatedAboutPageData", updateAboutPageContentModel);
	}

	public void returnCareerPageData(HttpServletRequest httpServletRequest) {
		this.updateCareerPageModel = this.careerPageServices.GetCareerPageData();
		httpServletRequest.setAttribute("CareerPageData", updateCareerPageModel);
	}

	public void returnContactData(HttpServletRequest httpServletRequest) {
		List<ContactModel> list = this.contactService.GetContactData();
		httpServletRequest.setAttribute("ContactData", list);
	}

	public void returnAdminRegistrationData(HttpServletRequest httpServletRequest) {
		List<AdminRegistrationModel> list = this.adminRegistrationService.GetAdminRegistrationData();
		httpServletRequest.setAttribute("AdminRegistrationData", list);
	}

	public void returnAdminLoginData(HttpServletRequest httpServletRequest) {
		List<AdminLoginModel> list = this.adminLoginService.GetAdminLoginData();
		httpServletRequest.setAttribute("AdminLoginData", list);
	}

	public void returnSpecificUpdatedServicesData(HttpServletRequest httpServletRequest, String ServiceHeading) {
		this.updateServicesModel = this.servicePageServices.GetSpecificUpdatedServiceData(ServiceHeading);
		httpServletRequest.setAttribute("SpecificUpdatedServicesData", updateServicesModel);
	}

	// Index Page Content and Reference

	@GetMapping("/")
	public String HomePage(HttpServletRequest httpServletRequest) {
		returnIndexPageData(httpServletRequest);
		returnUpdatedServicesData(httpServletRequest);
		returnUpdatedAboutPageData(httpServletRequest);
		return "index";
	}

	@GetMapping("/index")
	public String IndexPage(HttpServletRequest httpServletRequest) {
		returnIndexPageData(httpServletRequest);

		return "index";
	}

	@RequestMapping("/editIndexPageContent")
	public String EditIndexPageContent(HttpServletRequest httpServletRequest) {
		returnIndexPageData(httpServletRequest);
		return "editIndexPageContent";
	}

	@PostMapping("/editIndexPageContent")
	public String editIndexPageContent(@RequestParam("cheading") String CompanyHeading,
			@RequestParam("cintro") String CompanyIntro, @RequestParam("cwheading") String CompanyWelcomeHeading,
			@RequestParam("csdescription") String CompanyShortDescription,
			@RequestParam("isheading") String IndexServiceHeading,
			@RequestParam("fsheading") String FirstServiceHeading,
			@RequestParam("fsdescription") String FirstServiceDescription,
			@RequestParam("ssheading") String SecondServiceHeading,
			@RequestParam("ssdescription") String SecondServiceDescription,
			@RequestParam("tsheading") String ThirdServiceHeading,
			@RequestParam("tsdescription") String ThirdServiceDescription,
			@RequestParam("fosheading") String FourthServiceHeading,
			@RequestParam("fosdescription") String FourthServiceDescription,
			@RequestParam("aboutcompany") String CompanyContactInfoHeading,
			@RequestParam("clocation") String CompanyLocation, @RequestParam("ccontact") String CompanyContactNo,
			@RequestParam("cemail") String CompanyEmail, @RequestParam("withusheading") String WithUsHeading,
			@RequestParam("withusdescription") String WithUsDescription,
			@RequestParam("ddsheading") String DisplayDemoServiceHeading,
			@RequestParam("ddsone") String DisplayDemoServiceOne, @RequestParam("ddstwo") String DisplayDemoServiceTwo,
			@RequestParam("ddsthree") String DisplayDemoServiceThree,
			@RequestParam("instagramLink") String InstagramLink, @RequestParam("facebookLink") String FacebookLink,
			@RequestParam("twitterOrXLink") String TwitterOrXLink, @RequestParam("linkedinLink") String LinkedinLink) {

		EditIndexPageContentModel editIndexPageContentModel = new EditIndexPageContentModel();

		editIndexPageContentModel.setCompanyHeading(CompanyHeading);
		editIndexPageContentModel.setCompanyIntro(CompanyIntro);
		editIndexPageContentModel.setCompanyWelcomeHeading(CompanyWelcomeHeading);
		editIndexPageContentModel.setCompanyShortDescription(CompanyShortDescription);
		editIndexPageContentModel.setIndexServiceHeading(IndexServiceHeading);
		editIndexPageContentModel.setFirstServiceHeading(FirstServiceHeading);
		editIndexPageContentModel.setFirstServiceDescription(FirstServiceDescription);
		editIndexPageContentModel.setSecondServiceHeading(SecondServiceHeading);
		editIndexPageContentModel.setSecondServiceDescription(SecondServiceDescription);
		editIndexPageContentModel.setThirdServiceHeading(ThirdServiceHeading);
		editIndexPageContentModel.setThirdServiceDescription(ThirdServiceDescription);
		editIndexPageContentModel.setFourthServiceHeading(FourthServiceHeading);
		editIndexPageContentModel.setFourthServiceDescription(FourthServiceDescription);
		editIndexPageContentModel.setContactInfoHeading(CompanyContactInfoHeading);
		editIndexPageContentModel.setCompanyLocation(CompanyLocation);
		editIndexPageContentModel.setCompanyContactNo(CompanyContactNo);
		editIndexPageContentModel.setCompanyEmail(CompanyEmail);
		editIndexPageContentModel.setWithUsHeading(WithUsHeading);
		editIndexPageContentModel.setWithUsDescription(WithUsDescription);
		editIndexPageContentModel.setDisplayDemoServicesHeading(DisplayDemoServiceHeading);
		editIndexPageContentModel.setDisplayDemoServicesOne(DisplayDemoServiceOne);
		editIndexPageContentModel.setDisplayDemoServicesTwo(DisplayDemoServiceTwo);
		editIndexPageContentModel.setDisplayDemoServicesThree(DisplayDemoServiceThree);

		if (InstagramLink != "") {
			editIndexPageContentModel.setInstagramLink(InstagramLink);
		} else {
			editIndexPageContentModel.setInstagramLink(null);
		}

		if (FacebookLink != "") {
			editIndexPageContentModel.setFacebookLink(FacebookLink);
		} else {
			editIndexPageContentModel.setFacebookLink(null);
		}

		if (TwitterOrXLink != "") {
			editIndexPageContentModel.setTwitterOrXLink(TwitterOrXLink);
		} else {
			editIndexPageContentModel.setTwitterOrXLink(null);
		}

		if (LinkedinLink != "") {
			editIndexPageContentModel.setLinkedinLink(LinkedinLink);
		} else {
			editIndexPageContentModel.setLinkedinLink(null);
		}

		editIndexPageServices.saveIndexPageContentData(editIndexPageContentModel);
		return "redirect:/adminPage";
	}

	// Admin Registration Data Save and Delete and Fetch

	@RequestMapping("/adminRegistration")
	public String AdminRegistrationPage() {
		return "adminRegistration";
	}

	@PostMapping("/adminRegistration")
	public String AdminRegistrationData(@RequestParam("fullName") String fullName, @RequestParam("e-mail") String email,
			@RequestParam("mobile-no") String mobileNo, @RequestParam("address") String address,
			@RequestParam("city") String cityOrVillage, @RequestParam("district") String district,
			@RequestParam("state") String state, @RequestParam("zip-code") String zipCode,
			@RequestParam("username") String username, @RequestParam("password") String password) {

		AdminRegistrationModel adminRegistrationModel = new AdminRegistrationModel();

		adminRegistrationModel.setFullName(fullName);
		adminRegistrationModel.setEmail(email);
		adminRegistrationModel.setMobileNo(mobileNo);
		adminRegistrationModel.setAddress(address);
		adminRegistrationModel.setCityOrVillage(cityOrVillage);
		adminRegistrationModel.setDistrict(district);
		adminRegistrationModel.setState(state);
		adminRegistrationModel.setZipCode(zipCode);
		adminRegistrationModel.setUserName(username);
		adminRegistrationModel.setPassword(password);
		String adminLoginID = username.substring(0, 4) + mobileNo.substring(5, 9);
		adminRegistrationModel.setAdminLoginId(adminLoginID);
		adminRegistrationService.AdminRegistrationDataSave(adminRegistrationModel);

		return "redirect:/adminLogin";
	}

	@RequestMapping("/checkAdminUpdates")
	public String checkAdminUpdates(HttpServletRequest httpServletRequest) {
		this.returnAdminRegistrationData(httpServletRequest);
		return "checkAdminUpdates";
	}

	@RequestMapping("/deleteAllAdminRegistrationData")
	public String DeleteAllAdminRegistrationData(HttpServletRequest httpServletRequest) {
		this.adminRegistrationService.DeleteAllAdminRegistrationData();
		return "redirect:/checkAdminUpdates";
	}

	@RequestMapping("/removeAdmin/{adminLoginId}")
	public String AdminRemove(@PathVariable String adminLoginId, HttpServletRequest httpServletRequest) {
		this.adminRegistrationService.AdminRemove(adminLoginId);
		// this.adminLoginService.AdminLoginRemove(adminLoginId);
		returnAdminRegistrationData(httpServletRequest);
		return "redirect:/checkAdminUpdates";
	}

	// Admin Login Data Save, Login Check, Main Admin Login Check, Fetch, Admin
	// Logout and Delete.

	@RequestMapping("/adminLogin")
	public String AdminLogin() {
		return "adminLogin";
	}

	@PostMapping("/adminLogin")
	public String AdminLoginDataSave(@RequestParam("username") String username,
			@RequestParam("password") String password, HttpSession httpSession) {

		if (mainAdminLoginUsername.equals(username) && mainAdminLoginPassword.equals(password)) {
			httpSession.setAttribute("Main-Admin-RSCM-Username", mainAdminLoginUsername);
			return "adminRegistration";
		}

		if (adminRegistrationService.AdminLoginCheck(username, password) != null) {
			AdminLoginModel adminLoginModel = new AdminLoginModel();
			adminLoginModel.setAdminLoginId(adminRegistrationService.GetAdminLoginId(username, password));
			adminLoginModel.setFullName(adminRegistrationService.GetAdminFullName(username, password));
			adminLoginModel.setUsername(username);
			adminLoginModel.setPassword(password);
			adminLoginService.AdminLoginDataSave(adminLoginModel);

			httpSession.setAttribute("username", username);
			httpSession.setAttribute("Fullname", this.adminRegistrationService.GetAdminFullName(username, password));
			// Add a cookie for the user
//			Cookie cookie = new Cookie("username", username);
//			cookie.setMaxAge(60 * 60); // 1 hour
//			cookie.setHttpOnly(true);
//			httpServletResponse.addCookie(cookie);
			return "redirect:/adminPage";
		} else {
			return "redirect:/adminLogin";
		}

	}

	@RequestMapping("/adminLoginData")
	public String AdminLoginData(HttpServletRequest httpServletRequest) {
		this.returnAdminLoginData(httpServletRequest);
		return "adminLoginData";
	}

	@RequestMapping("/specificAdminLoginData/{username}")
	public String SpecificAdminLoginData(HttpServletRequest httpServletRequest,
			@PathVariable("username") String username) {
		returnSpecificAdminLoginData(httpServletRequest, username);
		return "specificAdminLoginData";
	}

	@RequestMapping("/deleteAllAdminLoginData")
	public String DeleteAllAdminLoginData(HttpServletRequest httpServletRequest) {
		this.adminLoginService.DeleteAllAdminLoginData();
		return "redirect:/adminLoginData";
	}

	@RequestMapping("/logout")
	public String Logout(HttpSession httpSession) {

		httpSession.invalidate();

		return "redirect:/adminLogin";
	}

	// Admin Panel

	@RequestMapping("/adminPage")
	public String AdminPage() {
		return "adminPage";
	}

	// Contact Data Save, Fetch, Delete.

	@RequestMapping("/contact")
	public String ContactPage(HttpServletRequest httpServletRequest) {
		returnIndexPageData(httpServletRequest);
		return "contact";
	}

	@PostMapping("/contact")
	public String ContactPageData(@RequestParam("fullname") String Fullname, @RequestParam("email") String Email,
			@RequestParam("mobileNo") String MobileNo, @RequestParam("query") String Query) {

		ContactModel contactModel = new ContactModel();

		contactModel.setFullname(Fullname);
		contactModel.setMobileNo(MobileNo);
		contactModel.setEmail(Email);
		contactModel.setQuery(Query);
		contactService.SaveContactData(contactModel);
		return "redirect:/index";
	}

	@RequestMapping("/contactData")
	public String ContactData(HttpServletRequest httpServletRequest) {
		returnContactData(httpServletRequest);
		return "contactData";
	}

	@RequestMapping("/deleteAllContactData")
	public String DeleteAllContactData(HttpServletRequest httpServletRequest) {
		this.contactService.DeleteAllContactData();
		return "redirect:/contactData";
	}

	// About Page Data Save, Update, Fetch.

	@RequestMapping("/about")
	public String AboutPage(HttpServletRequest httpServletRequest) {
		returnIndexPageData(httpServletRequest);
		returnUpdatedAboutPageData(httpServletRequest);
		return "about";
	}

	@RequestMapping("/updateAboutPageData")
	public String UpdateAboutPage(HttpServletRequest httpServletRequest) {
		returnUpdatedAboutPageData(httpServletRequest);
		return "updateAboutPageData";
	}

	@PostMapping("/updateAboutPageData")
	public String UpdateAboutPageData(@RequestParam("aboutUs") String aboutUs,
			@RequestParam("ourMission") String ourMission, @RequestParam("ourVision") String ourVision,
			@RequestParam("whoWeAre") String whoWeAre, @RequestParam("whatWeDo") String whatWeDo,
			@RequestParam("firstWhyChooseUs") String firstWhyChooseUs,
			@RequestParam("secondWhyChooseUs") String secondWhyChooseUs,
			@RequestParam("thirdWhyChooseUs") String thirdWhyChooseUs,
			@RequestParam("fourthWhyChooseUs") String fourthWhyChooseUs,
			@RequestParam("fifthWhyChooseUs") String fifthWhyChooseUs) {

		UpdateAboutPageContentModel updateAboutPageContentModel = new UpdateAboutPageContentModel();
		updateAboutPageContentModel.setAboutUs(aboutUs);
		updateAboutPageContentModel.setOurMission(ourMission);
		updateAboutPageContentModel.setOurVision(ourVision);
		updateAboutPageContentModel.setWhoWeAre(whoWeAre);
		updateAboutPageContentModel.setWhatWeDO(whatWeDo);
		if (firstWhyChooseUs != "") {
			updateAboutPageContentModel.setFirstWhyChooseUs(firstWhyChooseUs);
		} else {
			updateAboutPageContentModel.setFirstWhyChooseUs(null);
		}

		if (secondWhyChooseUs != "") {
			updateAboutPageContentModel.setSecondWhyChooseUs(secondWhyChooseUs);
		} else {
			updateAboutPageContentModel.setSecondWhyChooseUs(null);
		}

		if (thirdWhyChooseUs != "") {
			updateAboutPageContentModel.setThirdWhyChooseUs(thirdWhyChooseUs);
		} else {
			updateAboutPageContentModel.setThirdWhyChooseUs(null);
		}

		if (fourthWhyChooseUs != "") {
			updateAboutPageContentModel.setFourthWhyChooseUs(fourthWhyChooseUs);
		} else {
			updateAboutPageContentModel.setFourthWhyChooseUs(null);
		}

		if (fifthWhyChooseUs != "") {
			updateAboutPageContentModel.setFifthWhyChooseUs(fifthWhyChooseUs);
		} else {
			updateAboutPageContentModel.setFifthWhyChooseUs(null);
		}

		aboutPageServices.UpdateAboutPageData(updateAboutPageContentModel);

		return "redirect:/adminPage";
	}

	// Updates Page Data Save, Fetch, Update, Delete

	@RequestMapping("/updatesPage")
	public String UpdatesPage(HttpServletRequest httpServletRequest) {
		returnIndexPageData(httpServletRequest);
		returnAllUpdatesData(httpServletRequest);
		return "updatesPage";
	}

	@RequestMapping("/updateUpdatesPage")
	public String UpdateUpdatesPage(HttpServletRequest httpServletRequest) {
		returnUpdatedServicesData(httpServletRequest);
		return "updateUpdatesPage";
	}

	@PostMapping("/updateUpdatesPage")
	public String UpdateUpdatesPageData(@RequestParam("updatesCategory") String updatesCategory,
			@RequestParam("updatesHeading") String updatesHeading,
			@RequestParam("updatesDescription") String updatesDescription,
			@RequestParam("firstBulletPoint") String firstBulletPoint,
			@RequestParam("secondBulletPoint") String secondBulletPoint,
			@RequestParam("thirdBulletPoint") String thirdBulletPoint,
			@RequestParam("fourthBulletPoint") String fourthBulletPoint,
			@RequestParam("fifthBulletPoint") String fifthBulletPoint,
			@RequestParam("sixthBulletPoint") String sixthBulletPoint,
			@RequestParam("seventhBulletPoint") String seventhBulletPoint,
			@RequestParam("eighthBulletPoint") String eighthBulletPoint,
			@RequestParam("ninthBulletPoint") String ninthBulletPoint,
			@RequestParam("tenthBulletPoint") String tenthBulletPoint) {

		UpdatesModel updatesModel = new UpdatesModel();

		if (updatesCategory != "") {
			updatesModel.setUpdatesCategory(updatesCategory);
		} else {
			updatesModel.setUpdatesCategory(null);
		}

		if (updatesHeading != "") {
			updatesModel.setUpdatesHeading(updatesHeading);
		} else {
			updatesModel.setUpdatesHeading(null);
		}

		if (updatesDescription != "") {
			updatesModel.setUpdatesDescription(updatesDescription);
		} else {
			updatesModel.setUpdatesDescription(null);
		}

		if (firstBulletPoint != "") {
			updatesModel.setFirstBulletPoint(firstBulletPoint);
		} else {
			updatesModel.setFirstBulletPoint(null);
		}

		if (secondBulletPoint != "") {
			updatesModel.setSecondBulletPoint(secondBulletPoint);
		} else {
			updatesModel.setSecondBulletPoint(null);
		}

		if (thirdBulletPoint != "") {
			updatesModel.setThirdBulletPoint(thirdBulletPoint);
		} else {
			updatesModel.setThirdBulletPoint(null);
		}

		if (fourthBulletPoint != "") {
			updatesModel.setFourthBulletPoint(fourthBulletPoint);
		} else {
			updatesModel.setFourthBulletPoint(null);
		}

		if (fifthBulletPoint != "") {
			updatesModel.setFifthBulletPoint(fifthBulletPoint);
		} else {
			updatesModel.setFifthBulletPoint(null);
		}

		if (sixthBulletPoint != "") {
			updatesModel.setSixthBulletPoint(sixthBulletPoint);
		} else {
			updatesModel.setSixthBulletPoint(null);
		}

		if (seventhBulletPoint != "") {
			updatesModel.setSeventhBulletPoint(seventhBulletPoint);
		} else {
			updatesModel.setSeventhBulletPoint(null);
		}

		if (eighthBulletPoint != "") {
			updatesModel.setEighthBulletPoint(eighthBulletPoint);
		} else {
			updatesModel.setEighthBulletPoint(null);
		}

		if (ninthBulletPoint != "") {
			updatesModel.setNinthBulletPoint(ninthBulletPoint);
		} else {
			updatesModel.setNinthBulletPoint(null);
		}

		if (tenthBulletPoint != "") {
			updatesModel.setTenthBulletPoint(tenthBulletPoint);
		} else {
			updatesModel.setTenthBulletPoint(null);
		}

		updatesService.SaveUpdatesData(updatesModel);
		return "adminPage";
	}

	@RequestMapping("/updatesData")
	public String UpdatesData(HttpServletRequest httpServletRequest) {
		returnAllUpdatesData(httpServletRequest);
		return "updatesData";
	}

	@RequestMapping("/updatesDescriptionPage/{UpdatesId}")
	public String UpdatesDescriptionPageData(HttpServletRequest httpServletRequest, @PathVariable int UpdatesId) {
		this.updatesModel = this.updatesService.GetUpdatesDataById(UpdatesId);
		httpServletRequest.setAttribute("UpdatesDescriptionData", updatesModel);
		return "updatesDescriptionPage";
	}

	@RequestMapping("/editUpdates/{UpdatesId}")
	public String EditUpdates(HttpServletRequest httpServletRequest, @PathVariable int UpdatesId) {
		this.updatesModel = this.updatesService.GetUpdatesDataById(UpdatesId);
		httpServletRequest.setAttribute("UpdatesDescriptionData", updatesModel);
		returnUpdatedServicesData(httpServletRequest);
		// httpServletRequest.setAttribute("UpdatesID", UpdatesId);
		return "editUpdates";
	}

	@PostMapping("/editUpdates/{UpdatesId}")
	public String EditUpdatesData(@PathVariable int UpdatesId, @RequestParam("updatesCategory") String updatesCategory,
			@RequestParam("updatesHeading") String updatesHeading,
			@RequestParam("updatesDescription") String updatesDescription,
			@RequestParam("firstBulletPoint") String firstBulletPoint,
			@RequestParam("secondBulletPoint") String secondBulletPoint,
			@RequestParam("thirdBulletPoint") String thirdBulletPoint,
			@RequestParam("fourthBulletPoint") String fourthBulletPoint,
			@RequestParam("fifthBulletPoint") String fifthBulletPoint,
			@RequestParam("sixthBulletPoint") String sixthBulletPoint,
			@RequestParam("seventhBulletPoint") String seventhBulletPoint,
			@RequestParam("eighthBulletPoint") String eighthBulletPoint,
			@RequestParam("ninthBulletPoint") String ninthBulletPoint,
			@RequestParam("tenthBulletPoint") String tenthBulletPoint) {

		UpdatesModel updatesModel = new UpdatesModel();

		updatesModel.setUpdatesId(UpdatesId);

		if (updatesCategory != "") {
			updatesModel.setUpdatesCategory(updatesCategory);
		} else {
			updatesModel.setUpdatesCategory(null);
		}

		if (updatesHeading != "") {
			updatesModel.setUpdatesHeading(updatesHeading);
		} else {
			updatesModel.setUpdatesHeading(null);
		}

		if (updatesDescription != "") {
			updatesModel.setUpdatesDescription(updatesDescription);
		} else {
			updatesModel.setUpdatesDescription(null);
		}

		if (firstBulletPoint != "") {
			updatesModel.setFirstBulletPoint(firstBulletPoint);
		} else {
			updatesModel.setFirstBulletPoint(null);
		}

		if (secondBulletPoint != "") {
			updatesModel.setSecondBulletPoint(secondBulletPoint);
		} else {
			updatesModel.setSecondBulletPoint(null);
		}

		if (thirdBulletPoint != "") {
			updatesModel.setThirdBulletPoint(thirdBulletPoint);
		} else {
			updatesModel.setThirdBulletPoint(null);
		}

		if (fourthBulletPoint != "") {
			updatesModel.setFourthBulletPoint(fourthBulletPoint);
		} else {
			updatesModel.setFourthBulletPoint(null);
		}

		if (fifthBulletPoint != "") {
			updatesModel.setFifthBulletPoint(fifthBulletPoint);
		} else {
			updatesModel.setFifthBulletPoint(null);
		}

		if (sixthBulletPoint != "") {
			updatesModel.setSixthBulletPoint(sixthBulletPoint);
		} else {
			updatesModel.setSixthBulletPoint(null);
		}

		if (seventhBulletPoint != "") {
			updatesModel.setSeventhBulletPoint(seventhBulletPoint);
		} else {
			updatesModel.setSeventhBulletPoint(null);
		}

		if (eighthBulletPoint != "") {
			updatesModel.setEighthBulletPoint(eighthBulletPoint);
		} else {
			updatesModel.setEighthBulletPoint(null);
		}

		if (ninthBulletPoint != "") {
			updatesModel.setNinthBulletPoint(ninthBulletPoint);
		} else {
			updatesModel.setNinthBulletPoint(null);
		}

		if (tenthBulletPoint != "") {
			updatesModel.setTenthBulletPoint(tenthBulletPoint);
		} else {
			updatesModel.setTenthBulletPoint(null);
		}

		updatesService.SaveUpdatesData(updatesModel);
		return "redirect:/adminPage";
	}
	
	@RequestMapping("/deleteUpdates/{UpdatesId}")
	public String DeleteUpdates(HttpServletRequest httpServletRequest, @PathVariable int UpdatesId) {
		this.updatesService.DeleteUpdates(UpdatesId);
		return "redirect:/updatesData";
	}
	

	@RequestMapping("/deleteAllUpdatesData")
	public String DeleteAllUpdates(HttpServletRequest httpServletRequest) {
		this.updatesService.DeleteAllUpdates();
		return "redirect:/updatesData";
	}

	// Services Page Data Save, Fetch, Update, Delete

	@RequestMapping("/tempset")
	public String tempset(HttpServletRequest httpServletRequest) {
		returnIndexPageData(httpServletRequest);
		returnUpdatedServicesData(httpServletRequest);
		returnAllUpdatesData(httpServletRequest);
		return "tempset";
	}
	
	@RequestMapping("/services")
	public String ServicesPage(HttpServletRequest httpServletRequest) {
		returnIndexPageData(httpServletRequest);
		returnUpdatedServicesData(httpServletRequest);
		return "services";
	}

	@RequestMapping("/updateServices")
	public String UpdateServices(HttpServletRequest httpServletRequest) {
		return "updateServices";
	}

	@PostMapping("/updateServices")
	public String UpdateServicesData(@RequestParam("serviceHeading") String serviceHeading,
			@RequestParam("serviceDescription") String serviceDescription,
			@RequestParam("serviceSpecializationFirst") String serviceSpecializationFirst,
			@RequestParam("serviceSpecializationSecond") String serviceSpecializationSecond,
			@RequestParam("serviceSpecializationThird") String serviceSpecializationThird,
			@RequestParam("serviceSpecializationFourth") String serviceSpecializationFourth,
			@RequestParam("serviceSpecializationFifth") String serviceSpecializationFifth) {

		UpdateServicesModel updateServicesModel = new UpdateServicesModel();

		if (serviceHeading != "") {
			updateServicesModel.setServiceHeading(serviceHeading);
		} else {
			updateServicesModel.setServiceHeading(null);
		}

		if (serviceDescription != "") {
			updateServicesModel.setServiceDescription(serviceDescription);
		} else {
			updateServicesModel.setServiceDescription(null);
		}

		if (serviceSpecializationFirst != "") {
			updateServicesModel.setServiceSpecializationFirst(serviceSpecializationFirst);
		} else {
			updateServicesModel.setServiceSpecializationFirst(null);
		}

		if (serviceSpecializationSecond != "") {
			updateServicesModel.setServiceSpecializationSecond(serviceSpecializationSecond);
		} else {
			updateServicesModel.setServiceSpecializationSecond(null);
		}

		if (serviceSpecializationThird != "") {
			updateServicesModel.setServiceSpecializationThird(serviceSpecializationThird);
		} else {
			updateServicesModel.setServiceSpecializationThird(null);
		}

		if (serviceSpecializationFourth != "") {
			updateServicesModel.setServiceSpecializationFourth(serviceSpecializationFourth);
		} else {
			updateServicesModel.setServiceSpecializationFourth(null);
		}

		if (serviceSpecializationFifth != "") {
			updateServicesModel.setServiceSpecializationFifth(serviceSpecializationFifth);
		} else {
			updateServicesModel.setServiceSpecializationFifth(null);
		}

		servicePageServices.UpdateServicesData(updateServicesModel);

		return "redirect:/adminPage";
	}

	@RequestMapping("/servicesData")
	public String servicesData(HttpServletRequest httpServletRequest) {
		returnUpdatedServicesData(httpServletRequest);
		return "servicesData";
	}

	@RequestMapping("/serviceDescription/{ServiceHeading}")
	public String ServicesDescription(HttpServletRequest httpServletRequest, @PathVariable String ServiceHeading) {
		returnSpecificUpdatedServicesData(httpServletRequest, ServiceHeading);
		return "serviceDescription";
	}

	@RequestMapping("/removeSpecificService/{dataId}")
	public String RemoveSpecificService(@PathVariable int dataId) {
		this.servicePageServices.RemoveSpecificService(dataId);
		return "redirect:/servicesData";
	}

	@RequestMapping("/deleteAllServices")
	public String RemoveAllServices() {
		this.servicePageServices.RemoveAllServices();
		return "redirect:/servicesData";
	}

	// Career Page Data Save, Update, Fetch

	@RequestMapping("/career")
	public String Career(HttpServletRequest httpServletRequest) {
		returnIndexPageData(httpServletRequest);
		returnCareerPageData(httpServletRequest);
		return "career";
	}

	@RequestMapping("/updateCareerPage")
	public String UpdateCareerPage(HttpServletRequest httpServletRequest) {
		returnCareerPageData(httpServletRequest);
		return "updateCareerPage";
	}

	@PostMapping("/updateCareerPage")
	public String UpdateCareerPageData(HttpServletRequest httpServletRequest,
			@RequestParam("careerPageDescription") String careerPageDescription,
			@RequestParam("firstRecruitment") String firstRecruitment,
			@RequestParam("secondRecruitment") String secondRecruitment,
			@RequestParam("thirdRecruitment") String thirdRecruitment,
			@RequestParam("fourthRecruitment") String fourthRecruitment,
			@RequestParam("fifthRecruitment") String fifthRecruitment) {

		UpdateCareerPageModel updateCareerPageModel = new UpdateCareerPageModel();

		updateCareerPageModel.setCareerDescription(careerPageDescription);

		if (firstRecruitment != "") {
			updateCareerPageModel.setFirstRecruitment(firstRecruitment);
		} else {
			updateCareerPageModel.setFirstRecruitment(null);
		}

		if (secondRecruitment != "") {
			updateCareerPageModel.setSecondRecruitment(secondRecruitment);
		} else {
			updateCareerPageModel.setSecondRecruitment(null);
		}

		if (thirdRecruitment != "") {
			updateCareerPageModel.setThirdRecruitment(thirdRecruitment);
		} else {
			updateCareerPageModel.setThirdRecruitment(null);
		}

		if (fourthRecruitment != "") {
			updateCareerPageModel.setFourthRecruitment(fourthRecruitment);
		} else {
			updateCareerPageModel.setFourthRecruitment(null);
		}

		if (fifthRecruitment != "") {
			updateCareerPageModel.setFifthRecruitment(fifthRecruitment);
		} else {
			updateCareerPageModel.setFifthRecruitment(null);
		}

		careerPageServices.UpdateCareerPageData(updateCareerPageModel);

		return "redirect:/adminPage";
	}

	// Recruitment Data

//	@RequestMapping("/recruitmentForm/{DomainName}")
//	public String RecruitmentForm(HttpServletRequest httpServletRequest, @PathVariable String DomainName) {
//		returnIndexPageData(httpServletRequest);
//		httpServletRequest.setAttribute("DomainName", DomainName);
//		return "recruitmentForm";
//	}
//
//	@PostMapping("/recruitmentForm")
//	public String Recruitment(HttpServletRequest httpServletRequest,
//			@RequestParam("resumeOrCV") MultipartFile multiPartData, @RequestParam("fullName") String fullName,
//			@RequestParam("email") String email, @RequestParam("mobileNo") String mobileNo,
//			@RequestParam("dateOfBirth") String dateOfBirth, @RequestParam("address") String address,
//			@RequestParam("cityOrVillage") String cityOrVillage, @RequestParam("district") String district,
//			@RequestParam("state") String state, @RequestParam("yourDomain") String yourDomain) throws IOException {
//
//		RecruitmentModel recruitmentModel = new RecruitmentModel();
//
//		recruitmentModel.setFileName(multiPartData.getOriginalFilename());
//		recruitmentModel.setFileType(multiPartData.getContentType());
//		recruitmentModel.setData(multiPartData.getBytes());
//		recruitmentModel.setFullName(fullName);
//		recruitmentModel.setEmail(email);
//		recruitmentModel.setMobileNo(mobileNo);
//		recruitmentModel.setAddress(address);
//		recruitmentModel.setDOB(dateOfBirth);
//		recruitmentModel.setCity(cityOrVillage);
//		recruitmentModel.setDistrict(district);
//		recruitmentModel.setState(state);
//		recruitmentModel.setDomainName(yourDomain);
//
//		recruitmentServices.SaveRecruitmentData(recruitmentModel);
//		return "recruitmentForm";
//	}

}
