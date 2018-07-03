package ind.raystar.mvc.service;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLConnection;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import ind.raystar.mvc.dao.BoardDAO;
import ind.raystar.mvc.dto.MySession;
import ind.raystar.mvc.dto.PageSelectDTO;
import ind.raystar.mvc.dto.PostDTO;
import ind.raystar.mvc.dto.PostSelectDTO;

@Service
public class BoardService {
	
	/*
	 * Service클래스의 역할은 Model에 담길 데이터에 대한 실질적 Business logic을 작성하며
	 * Database에서 값을 추출해오는 역할은 DAO에게 떠넘긴다.
	 */
	@Autowired
	private BoardDAO dao;
	
	private static final Logger logger = LoggerFactory.getLogger(BoardService.class);
	
	final int postPerPage = 10;
	final int pageSelectSize = 5;
	
	public PageSelectDTO getInfo(int currentPage, String searchBy, String query) {
		int listCount = 0;
		if(searchBy == null || query == null) {
			listCount = dao.selectListCount();
		} else {
			PostSelectDTO postSelect = new PostSelectDTO();
			postSelect.setSearchBy(searchBy);
			postSelect.setQuery(query);
			listCount = dao.getListCountWithSearchQuery(postSelect);
		}
		int lastPage = (listCount-1)/postPerPage + 1;
		int range = pageSelectSize / 2;
		
		int pageSelectStart = currentPage - range;
		if(pageSelectStart < 1) {
			pageSelectStart = 1;
		}
		int pageSelectEnd = currentPage + range;
		if(pageSelectEnd > lastPage) {
			pageSelectEnd = lastPage;
		}
		
		PageSelectDTO info = new PageSelectDTO();
		
		info.setStart(pageSelectStart);
		info.setEnd(pageSelectEnd);
		info.setLast(lastPage);
		info.setCurrent(currentPage);
		return info;
	}
	
	public String writeFileToServer(String fileName, String path, MultipartFile multipartFile) throws Exception{
		long timemills = Calendar.getInstance().getTimeInMillis();
		StringBuilder sb = new StringBuilder(multipartFile.getOriginalFilename());
		sb.insert(sb.lastIndexOf("."), timemills);
		String repFileName = sb.toString();
		
		sb.insert(0, path);
		String realFilePath = sb.toString();
		logger.debug("repFileName : " + repFileName);
		logger.debug("realFileName : " + realFilePath);
		
		File file = new File(realFilePath);
		FileOutputStream outputStream = new FileOutputStream(file);
		outputStream.write(multipartFile.getBytes());
		outputStream.close();
		return repFileName;
	}
	public void setAttachmentValues(PostDTO post, String fileName, String repFileName) {
		post.setAttachment(fileName);
		post.setAttachmentLink(repFileName);
	}
	
	public PostDTO writePost(String title, String content, MySession mySession, String path, MultipartFile multipartFile) throws Exception{
		PostDTO post = new PostDTO();
		post.setTitle(title);
		post.setContent(content);
		post.setWriter(mySession.getId());
		if(!multipartFile.isEmpty()) {
			String fileName = multipartFile.getOriginalFilename();
			String repFileName = writeFileToServer(fileName, path, multipartFile);
			setAttachmentValues(post, fileName, repFileName);
		}
		dao.insertPost(post);
		return post;
	}
	public void downloadFile(int postNo, String rootPath, HttpServletResponse response) throws Exception{

		String repFileName = dao.selectRepFileName(postNo);
		logger.debug(rootPath + repFileName);
		File file = new File(rootPath + repFileName);
		String mimeType= URLConnection.guessContentTypeFromName(file.getName());
        if(!file.exists()){
        	throw new IOException();
        }
        
        if(mimeType==null){
            System.out.println("mimetype is not detectable, will take default");
            mimeType = "application/octet-stream";
        }
        response.setHeader("Content-Disposition", String.format("attachment; filename=\"%s\"", file.getName()));
        response.setContentType(mimeType);
        response.setContentLength((int)file.length());
        
        InputStream in = new BufferedInputStream(new FileInputStream(file));
        OutputStream out = response.getOutputStream();
		FileCopyUtils.copy(in, out);
		in.close();
		out.close();
	}
	
	public List<PostDTO> getPosts(int page, String searchBy, String query) {
		List<PostDTO> posts;
		int startPost = (page-1)*postPerPage;
		int endPost = page*postPerPage;
		int maxCount = 0;
		PostSelectDTO postSelect = new PostSelectDTO();
		if(searchBy == null || query == null) {			
			maxCount = dao.selectListCount();
		} else {
			postSelect.setSearchBy(searchBy);
			postSelect.setQuery(query);
			maxCount = dao.getListCountWithSearchQuery(postSelect);
		}
		if(endPost > maxCount) {
			endPost = maxCount;
		}
		postSelect.setStart(startPost);
		postSelect.setEnd(endPost);
		if(searchBy == null || query == null) {
			posts = dao.selectPosts(postSelect);
		} else {
			posts = dao.selectPostsWithSearchQuery(postSelect);
		}
		return posts;
	}
	
	public PostDTO getPostDetail(int postNo) {
		PostDTO post = dao.selectPost(postNo);
		dao.addReadCount(postNo);
		return post;
	}
	
	public void updatePost(PostDTO post) {
		dao.updatePost(post);
	}
	public PostDTO selectPost(int postNo) {
		return dao.selectPost(postNo);
	}
	public void deletePost(int postNo) {
		dao.deletePost(postNo);
	}
}
