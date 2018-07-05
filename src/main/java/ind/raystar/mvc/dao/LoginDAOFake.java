package ind.raystar.mvc.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import ind.raystar.mvc.dto.MemberDTO;

@Repository
@Qualifier("LoginDAOFake")
public class LoginDAOFake implements LoginDAO {

	List<MemberDTO> members = new ArrayList<>();

	@Autowired
	public LoginDAOFake() {
		initMembers();
	}

	@Override
	public int checkNickname(String nickname) {
		for (int i = 0; i < members.size(); i++) {
			if (nickname.equals(members.get(i).getNickname())) {
				return 1;
			}
		}
		return 0;
	}

	private void initMembers() {
		MemberDTO member1 = new MemberDTO();
		member1.setNickname("scott");
		member1.setPassword("tiger");
		member1.setEmail("scott@gmail.com");
		members.add(member1);
	}

	@Override
	public int checkLogin(MemberDTO memberModel) {
		MemberDTO memberEach;
		for (int i = 0; i < members.size(); i++) {
			memberEach = members.get(i);
			if (memberEach.getNickname().equals(memberModel.getNickname())) {
				if (memberEach.getPassword().equals(memberModel.getPassword())) {
					return 1;
				} else {
					return 0;
				}
			}
		}
		return 0;
	}

	@Override
	public int insertMember(MemberDTO memberModel) {
		members.add(memberModel);
		return 1;
	}

}
