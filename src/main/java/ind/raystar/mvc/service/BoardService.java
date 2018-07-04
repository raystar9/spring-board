package ind.raystar.mvc.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

import ind.raystar.mvc.dto.MySession;
import ind.raystar.mvc.dto.PageSelectDTO;
import ind.raystar.mvc.dto.PostDTO;

public interface BoardService {
	public PageSelectDTO getInfo(int currentPage, String searchBy, String query);

	public String writeFileToServer(String fileName, String path, MultipartFile multipartFile) throws Exception;

	public void setAttachmentValues(PostDTO post, String fileName, String repFileName);

	public PostDTO writePost(String title, String content, MySession mySession, String path,
			MultipartFile multipartFile) throws Exception;

	public void downloadFile(int postNo, String rootPath, HttpServletResponse response) throws Exception;

	public List<PostDTO> getPosts(int page, String searchBy, String query);

	public PostDTO getPostDetail(int postNo);

	public void updatePost(PostDTO post);

	public PostDTO selectPost(int postNo);

	public void deletePost(int postNo);
}
