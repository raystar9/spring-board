package ind.raystar.mvc.dao;

import ind.raystar.mvc.dto.MemberDTO;

public interface LoginDAO {
	public int checkNickname(String nickname);

	public int checkLogin(MemberDTO memberModel);

	public int insertMember(MemberDTO memberModel);
}
