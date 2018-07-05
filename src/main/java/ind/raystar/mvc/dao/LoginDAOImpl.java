package ind.raystar.mvc.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import ind.raystar.mvc.dto.MemberDTO;

@Repository
@Qualifier("LoginDAOImpl")
public class LoginDAOImpl implements LoginDAO {

	@Autowired
	SqlSessionFactory sqlSession;

	private SqlSession getSession() {
		return sqlSession.openSession();
	}

	@Override
	public int checkNickname(String nickname) {
		SqlSession session = getSession();
		int result = session.selectOne("Login.checkNickname", nickname);
		session.close();
		return result;
	}

	@Override
	public int checkLogin(MemberDTO memberModel) {
		SqlSession session = getSession();
		int result = session.selectOne("Login.check", memberModel);
		session.close();
		return result;
	}

	@Override
	public int insertMember(MemberDTO memberModel) {
		SqlSession session = getSession();
		int result = session.insert("Login.insertMember", memberModel);
		session.close();
		return result;
	}

}
