package ind.raystar.mvc.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ind.raystar.mvc.dto.PostDTO;
import ind.raystar.mvc.dto.PostSelectDTO;

/*
 * BoardDAOFake의 @Repository annotation에 주석처리하고,
 * 이 클래스의 @Repository의 주석을 해제를 하면 실제 OracleDB와 연결이 가능하다.
 */
@Repository("boardDAOImpl")
public class BoardDAOImpl implements BoardDAO {

	/*
	 * DAO는 Database와 통신을 하여 Service가 요청한 데이터 묶음을 Database로부터 가져오거나 
	 * INSERT, DELETE등의 입력 쿼리문을 처리하고 Database Session을 관리한다.
	 */
	@Autowired
	SqlSessionFactory sqlSession;

	private SqlSession getSession() {
		return sqlSession.openSession();
	}

	public int selectListCount() {
		SqlSession session = getSession();
		int result = session.selectOne("Board.selectListCount");
		session.close();
		return result;
	}

	public int selectListCountWithSearchQuery(PostSelectDTO postSelect) {
		SqlSession session = getSession();
		int result = session.selectOne("Board.selectListCountWithSearch", postSelect);
		session.close();
		return result;
	}

	public List<PostDTO> selectPosts(PostSelectDTO postSelect) {
		SqlSession session = getSession();
		List<PostDTO> posts = session.selectList("Board.selectList", postSelect);
		session.close();
		return posts;
	}

	public List<PostDTO> selectPostsWithSearchQuery(PostSelectDTO postSelect) {
		SqlSession session = getSession();
		List<PostDTO> posts = session.selectList("Board.selectListWithSearch", postSelect);
		session.close();
		return posts;
	}

	public PostDTO selectPost(int postNo) {
		SqlSession session = getSession();
		PostDTO post = session.selectOne("Board.selectOne", postNo);
		session.close();
		return post;
	}

	public void insertPost(PostDTO post) {
		SqlSession session = getSession();
		session.insert("Board.insert", post);
		session.close();
	}

	public void updatePost(PostDTO post) {
		SqlSession session = getSession();
		session.update("Board.update", post);
		session.close();
	}

	public void updateReadCount(int postNo) {
		SqlSession session = getSession();
		session.update("Board.addReadCount", postNo);
		session.close();
	}

	public void deletePost(int postNo) {
		SqlSession session = getSession();
		session.delete("Board.delete", postNo);
		session.close();
	}

	public String selectRepFileName(int postNo) {
		SqlSession session = getSession();
		String fileName = session.selectOne("Board.selectRepNameById", postNo);
		session.close();
		return fileName;
	}
}
