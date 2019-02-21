package com.notion.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.notion.model.LoginVO;
import com.notion.model.RegVO;
import com.notion.model.UserProfileVO;
import com.notion.service.*;

@Controller
public class LoginController {
	
	@Autowired
	LoginService loginService;
	
	@Autowired
	RegService regService;
	
	@Autowired
	InstituteService instituteService;
	
	@Autowired
	UserProfileService userProfileService;
	
	@Autowired
	QRService qrService;
	
	@Autowired
	EmailService emailService;
	
	HttpSession session;
	
	@RequestMapping(value="/", method = RequestMethod.GET, headers = "Accept=application/json")
	public ModelAndView redirectLogin()
	{
		return new ModelAndView("redirect:/login");
	}
	
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public ModelAndView loadLogin(@RequestParam(value="error",required=false)String error)
	{
		return new ModelAndView("login","errormsg",error);
	}
	
	@RequestMapping(value="/forgotPassword",method=RequestMethod.GET)
	public String loadForgotPassword()
	{
		return "forgotPassword";
	}
	
	@RequestMapping(value="/resetPasswordLink",method=RequestMethod.GET)
	public void sendPwdResetLink(HttpServletResponse response,@RequestParam("email") String email,LoginVO loginVO) throws IOException
	{
		PrintWriter out=response.getWriter();
		loginVO.setUsername(email);
		List<LoginVO> getToken = new ArrayList<LoginVO>();
		getToken=this.loginService.getUser(loginVO);
		String token=getToken.get(0).getPassword();
		this.emailService.sendResetLink(email,token);
		out.print("sent");
	}
	
	@RequestMapping(value="/resetPassword",method=RequestMethod.GET)
	public ModelAndView loadResetPassword(HttpServletRequest request,@RequestParam("username") String email,@RequestParam("token") String token,@ModelAttribute LoginVO loginVO)
	{
		session=request.getSession();
		session.setAttribute("resetMail",email);
		session.setAttribute("resetToken", token);
		return new ModelAndView("/newPassword","passwordReset",loginVO);
	}
	
	@RequestMapping(value="/updatePassword",method=RequestMethod.POST)
	public String updatePassword(HttpServletRequest request,@ModelAttribute("passwordReset") LoginVO loginVO1)
	{
		session=request.getSession();
		String email=(String)session.getAttribute("resetMail");
		String token=(String)session.getAttribute("resetToken");
		String PasswordHash=this.qrService.createMd5(loginVO1.getPassword());
		List<LoginVO> comparePass=new ArrayList<LoginVO>();
		loginVO1.setUsername(email);
		comparePass=this.loginService.getUser(loginVO1);
		if(token.equals(comparePass.get(0).getPassword()))
		{
			loginVO1.setPassword(PasswordHash);
			this.loginService.resetPassword(loginVO1);
		}
		session.removeAttribute("resetMail");
		session.removeAttribute("resetToken");
		
		return "redirect:/login";
	}
	
	@RequestMapping(value="/logout",method=RequestMethod.GET)
	public String loadLogout(ModelMap model,HttpServletResponse response,HttpServletRequest request)
	{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null)
        {
            new SecurityContextLogoutHandler().logout(request, response, auth);
            request.getSession().invalidate();
            request.getSession().setAttribute("tempStatus", "success");
            request.getSession().setAttribute("statusText", "Logout Successfully!");
            request.getSession().removeAttribute("regDetails");
            request.getSession().removeAttribute("profileDetails");
        }
		return "redirect:/login";
	}
	
	@RequestMapping(value="/user/logout",method=RequestMethod.GET)
	public String userLogout()
	{
		return "redirect:/logout";
	}
	
	@RequestMapping(value="/admin/logout",method=RequestMethod.GET)
	public String adminLogout()
	{
		return "redirect:/logout";
	}
	
	@RequestMapping(value="/checkUser",method=RequestMethod.GET)
	public void checkUser(HttpServletResponse response,@RequestParam("username") String checkuser,LoginVO loginVO) throws IOException
	{
		PrintWriter out=response.getWriter();
		loginVO.setUsername(checkuser);
		List<LoginVO> emailCheck=new ArrayList<LoginVO>();
		emailCheck=this.loginService.getUser(loginVO);
		if(!emailCheck.isEmpty())
		{
			out.print("exist");
		}
	}
	
	@RequestMapping(value="/userVerification",method=RequestMethod.GET)
	public void sendVerifyLink(HttpServletResponse response,@RequestParam("username") String email,@RequestParam("firstname") String name) throws IOException
	{
		PrintWriter out=response.getWriter();
		String msg=this.emailService.sendVerificationLink(email, name);
		if(msg=="sent")
		{
			out.print("sent");
		}
		else
		{
			out.print("notsent");
		}
	}
	
	@RequestMapping(value="/insertRegData",method=RequestMethod.POST)
	public String insertRegData(@ModelAttribute RegVO regVO,LoginVO loginVO1)
	{
		loginVO1.setUsername(regVO.getLoginVO().getUsername());
		String passwordHash=this.qrService.createMd5(regVO.getLoginVO().getPassword());
		loginVO1.setPassword(passwordHash);
		loginVO1.setEnabled(0);
		loginVO1.setRole("ROLE_USER");
		this.loginService.insertToLogin(loginVO1);
		
		regVO.setLoginVO(loginVO1);
		regVO.setProfileStatus("empty");
		this.regService.insertToRegister(regVO);
		
		return "redirect:/signup?mail=sent";
	}
	
	@RequestMapping(value="verifyUser",method=RequestMethod.GET)
	public String verifyUser(@RequestParam("username") String email, LoginVO loginVO2)
	{
		loginVO2.setUsername(email);
		this.loginService.verifyUser(loginVO2);
		return "redirect:/login";
	}
	
	@RequestMapping(value="/admin/Dashboard",method=RequestMethod.GET)
	public ModelAndView loadAdminDashboard(HttpServletRequest request)
	{
		session=request.getSession();
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String userName = user.getUsername();
		session.setAttribute("adminMail", userName);
		return new ModelAndView("admin/adminDashboard");
	}
	
	@RequestMapping(value="/admin/collector",method=RequestMethod.GET)
	public String setCollectorSession(HttpServletRequest request)
	{
		session=request.getSession();
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String userName = user.getUsername();
		session.setAttribute("adminMail", userName);
		
		return "redirect:/admin/payments";
	}
	
	@RequestMapping(value="/user/Dashboard",method=RequestMethod.GET)
	public ModelAndView loadUserDashboard(HttpServletRequest request,@ModelAttribute LoginVO loginVO3,RegVO regVO1,UserProfileVO userProfileVO1) 
	{	
		session=request.getSession();
		
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String userName = user.getUsername();
		session.setAttribute("userMail", userName);
		
		loginVO3.setUsername(userName);
		regVO1.setLoginVO(loginVO3);
		List<RegVO> regDetailsList=new ArrayList<RegVO>();
		regDetailsList=this.regService.getRegDetails(regVO1);
		regVO1=regDetailsList.get(0);
		userProfileVO1.setRegVO(regVO1);
		session.setAttribute("regDetails", regVO1);
		
		if(regVO1.getProfileStatus().equals("complete"))
		{
			List<UserProfileVO> profileDetailsList=this.userProfileService.getUserProfileByReg(userProfileVO1);
			userProfileVO1=profileDetailsList.get(0);
			session.setAttribute("profileDetails", userProfileVO1);
			return new ModelAndView("/user/userDashboard");
		}
		else
		{
			return new ModelAndView("redirect:/user/profile");
		}
	}
	
	@RequestMapping(value="/admin/blockUser",method=RequestMethod.GET)
	public String blockUser(@RequestParam("selectedUser") String user,LoginVO loginVO4)
	{
		loginVO4.setUsername(user);
		loginVO4.setEnabled(0);
		this.loginService.changeEnabled(loginVO4);
		
		return "redirect:/admin/registeredUsers";
	}
	
	@RequestMapping(value="/admin/unblockUser",method=RequestMethod.GET)
	public String unblockUser(@RequestParam("selectedUser") String user,LoginVO loginVO5)
	{
		loginVO5.setUsername(user);
		loginVO5.setEnabled(1);
		this.loginService.changeEnabled(loginVO5);
		
		return "redirect:/admin/registeredUsers";
	}
}
