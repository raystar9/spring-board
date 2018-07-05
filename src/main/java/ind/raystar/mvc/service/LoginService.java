package ind.raystar.mvc.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ind.raystar.mvc.dao.LoginDAOFake;
import ind.raystar.mvc.dto.MemberDTO;

@Service
public class LoginService {

	@Autowired
	private LoginDAOFake loginDAO;

	Logger logger = LoggerFactory.getLogger(this.getClass());

	public int checkNickname(String nickname) {
		return loginDAO.checkNickname(nickname);
	}

	public int checkLogin(MemberDTO memberModel) {
		return loginDAO.checkLogin(memberModel);
	}

	public int insertMember(MemberDTO memberModel) {
		return loginDAO.insertMember(memberModel);
	}
}
