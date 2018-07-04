package ind.raystar.mvc.dao;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ind.raystar.mvc.dto.PostDTO;
import ind.raystar.mvc.dto.PostSelectDTO;

@Repository("BoardDAO")
public class BoardDAOFake implements BoardDAO {

	/*
	 * 실제 DB를 구축하지 않고 Java만으로 DB에 접속한 것 처럼 보이기 위한 가짜 DAO이다.
	 */
	List<PostDTO> posts = new ArrayList<>();
	Logger logger = LoggerFactory.getLogger(BoardDAOFake.class);
	private int index = 0;

	@Autowired
	public void BoardDaoFake() {
		initPosts();
	}

	private void initPosts() {

		for (int i = 1; i < 120; i++) {
			PostDTO post = new PostDTO();
			post.setId(i);
			post.setPostNo(i);
			switch (i % 3) {
			case 0:
				post.setWriter("koo");
				break;
			case 1:
				post.setWriter("ray");
				break;
			case 2:
				post.setWriter("star");
				break;
			}

			post.setTitle(i + "번째 글의 제목");
			post.setContent(i + "번째 글의 내용입니다.");
			post.setReadCount(0);
			posts.add(post);
			index++;
		}
	}

	@Override
	public int selectListCount() {
		return posts.size();
	}

	@Override
	public List<PostDTO> selectPosts(PostSelectDTO postSelect) {
		int startPost = postSelect.getStart();
		int endPost = postSelect.getEnd();
		return getListWithBoundary(posts, startPost, endPost);
	}

	@Override
	public List<PostDTO> selectPostsWithSearchQuery(PostSelectDTO postSelect) {
		String searchBy = postSelect.getSearchBy();
		String query = postSelect.getQuery();
		int startPost = postSelect.getStart();
		int endPost = postSelect.getEnd();
		return getListWithBoundary(getSearchedList(searchBy, query), startPost, endPost);
	}

	private List<PostDTO> getSearchedList(String searchBy, String query) {
		List<PostDTO> results = new ArrayList<>();
		PostDTO post;
		switch (searchBy) {
		case "writer":
			for (int i = 0; i < posts.size(); i++) {
				post = posts.get(i);
				if (post.getWriter().contains(query)) {
					results.add(post);
				}
			}
			break;
		case "title":
			for (int i = 0; i < posts.size(); i++) {
				post = posts.get(i);
				if (post.getTitle().contains(query)) {
					results.add(post);
				}
			}
			break;
		case "content":
			for (int i = 0; i < posts.size(); i++) {
				post = posts.get(i);
				if (post.getContent().contains(query)) {
					results.add(post);
				}
			}
			break;
		}

		return results;
	}

	private List<PostDTO> getListWithBoundary(List<PostDTO> list, int start, int end) {
		List<PostDTO> result = new ArrayList<>();
		int startPost = list.size() - start - 1;
		int endPost = list.size() - end;
		logger.debug(startPost + " to " + endPost);
		for (int i = startPost; i >= endPost; i--) {
			result.add(list.get(i));
		}
		return result;
	}

	@Override
	public PostDTO selectPost(int postNo) {
		for (int i = 0; i < posts.size(); i++) {
			if (posts.get(i).getPostNo() == postNo) {
				return posts.get(i);
			}
		}
		return null;
	}

	@Override
	public void insertPost(PostDTO post) {
		post.setPostNo(++index);
		posts.add(post);
	}

	@Override
	public void updatePost(PostDTO post) {
		posts.set(posts.indexOf(selectPost(post.getPostNo())), post);
	}

	@Override
	public void updateReadCount(int postNo) {
		PostDTO post = selectPost(postNo);
		post.setReadCount(post.getReadCount() + 1);
	}

	@Override
	public void deletePost(int postNo) {
		posts.remove(selectPost(postNo));

	}

	@Override
	public String selectRepFileName(int postNo) {
		return selectPost(postNo).getAttachmentLink();
	}

	@Override
	public int selectListCountWithSearchQuery(PostSelectDTO postSelect) {
		String searchBy = postSelect.getSearchBy();
		String query = postSelect.getQuery();
		return getSearchedList(searchBy, query).size();
	}

}
