package ind.raystar.mvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import ind.raystar.mvc.dto.MemberDTO;
import ind.raystar.mvc.dto.MySession;
import ind.raystar.mvc.service.LoginService;

@Controller
@SessionAttributes("mySession")
public class LoginController{
	
	@Autowired
	LoginService loginService;
//	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@RequestMapping(value="login", method=RequestMethod.GET)
	public String login() {
		return "loginForm";
	}
	
	@RequestMapping(value="login", method=RequestMethod.POST)
	public String loginAuth(MemberDTO memberModel, MySession mySession) {
		return loginProcess(memberModel, mySession);
	}
	
	@RequestMapping(value="logout", method=RequestMethod.GET)
	public String logout(MemberDTO loginInfo, MySession mySession) {
		mySession.setId(null);
		return "alert/logout";
	}
	
	@RequestMapping(value="join", method=RequestMethod.GET)
	public String join() {
		return "joinForm";
	}
	
	@RequestMapping(value="join", method=RequestMethod.POST)
	public String joinAdd(MemberDTO memberModel, MySession mySession) {
		loginService.insertMember(memberModel);
		return loginProcess(memberModel, mySession);
	}
	
	public String loginProcess(MemberDTO memberModel, MySession mySession) {
		switch(loginService.checkLogin(memberModel)) {
		case 1:
			mySession.setId(memberModel.getNickname());
			return "redirect:/board";
		case 0:
		default:
			return "alert/login-error";
		}
	}
	@RequestMapping(value="idcheck", method=RequestMethod.GET)
	public String idCheck(Model model, String nickname) {
		model.addAttribute("result", loginService.checkNickname(nickname));
		return "idCheck";
	}
}
