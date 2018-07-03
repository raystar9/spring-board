package ind.raystar.mvc.dao;

import java.util.List;

import ind.raystar.mvc.dto.PostDTO;
import ind.raystar.mvc.dto.PostSelectDTO;

/*
 * BoardDAOFake와 BoardDAOImpl의 공통 인터페이스이다.
 * 둘 각각은 BoardDAO의 구현에 불과하다.
 */
public interface BoardDAO {
	public int selectListCount();
	public int getListCountWithSearchQuery(PostSelectDTO postSelect);
	public List<PostDTO> selectPosts(PostSelectDTO postSelect);
	public List<PostDTO> selectPostsWithSearchQuery(PostSelectDTO postSelect);
	public PostDTO selectPost(int postNo);
	public void insertPost(PostDTO post);
	public void updatePost(PostDTO post);
	public void addReadCount(int postNo);
	public void deletePost(int postNo);
	public String selectRepFileName(int postNo);
}
