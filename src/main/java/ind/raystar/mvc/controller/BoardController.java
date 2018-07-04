package ind.raystar.mvc.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import ind.raystar.mvc.dto.MySession;
import ind.raystar.mvc.dto.PostDTO;
import ind.raystar.mvc.service.BoardService;

@Controller
@SessionAttributes("mySession")
public class BoardController {

	/*
	 * Controller는 
	 * 1. uri 요청을 받아들이고 적절히 분배 
	 * 2. 해당 요청에 맞는 Model 작성(Business logic은 Service객체로 떠넘김) 
	 * 3. 요청에 맞는 view(.jsp파일)와 연결 의 역할을 한다.
	 */
	@Autowired
	private BoardService boardService;
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);

	@RequestMapping(value = "board")
	public String boardList(Model model, @RequestParam(name = "page", defaultValue = "1") String pageParam,
			@RequestParam(required = false) String query,
			@RequestParam(name = "searchby", required = false) String searchBy, MySession mySession) {
		int page = Integer.parseInt(pageParam);

		logger.debug(mySession.getId());

		model.addAttribute("posts", boardService.getPosts(page, searchBy, query));
		model.addAttribute("page", page);
		model.addAttribute("searchBy", searchBy);
		model.addAttribute("query", query);
		model.addAttribute("pageSelectInfo", boardService.getInfo(page, searchBy, query));
		return "list";
	}

	/*
	 * RequestMapping의 value값과 return값이 일치한다면 반환형을 void로 해도 같은 결과를 얻을 수 있다.
	 */
	@RequestMapping(value = "board/{postNo}")
	public String postPost(Model model, @PathVariable() int postNo, MySession mySession) {
		logger.debug(mySession.getId());
		model.addAttribute("post", boardService.getPostDetail(postNo));
		return "board/post";
	}

	@RequestMapping(value = "board/{postNo}", method = RequestMethod.PUT)
	public String putPost(Model model, PostDTO post) {
		boardService.updatePost(post);
		model.addAttribute("post", post);
		return "redirect:/board/" + post.getPostNo();
	}

	@RequestMapping(value = "board/{postNo}/download", method = RequestMethod.GET)
	public void downloadFile(HttpServletRequest request, HttpServletResponse response, @PathVariable() int postNo)
			throws Exception {
		String rootPath = request.getSession().getServletContext().getRealPath("/");
		boardService.downloadFile(postNo, rootPath, response);
	}

	@RequestMapping(value = "board/new")
	public String boardNew(Model model, MySession mySession) {
		model.addAttribute("sessionId", mySession.getId());
		return "board/new";
	}

	@RequestMapping(value = "board/new", method = RequestMethod.POST)
	public String boardNewInsert(@RequestParam String title, @RequestParam String content,
			@RequestParam MultipartFile multipartFile, MultipartHttpServletRequest request, MySession mySession)
			throws Exception {
		String rootPath = request.getSession().getServletContext().getRealPath("/");

		logger.debug(rootPath);
		boardService.writePost(title, content, mySession, rootPath, multipartFile);

		return "redirect:/board";
	}

	@RequestMapping(value = "board/{postNo}/modify", method = RequestMethod.GET)
	public String boardModify(Model model, @PathVariable int postNo) {
		// int postNo = Integer.parseInt(postNo);
		model.addAttribute("post", boardService.selectPost(postNo));
		return "board/post/modify";
	}

	@RequestMapping(value = "board/{postNo}", method = RequestMethod.DELETE)
	public String boardDelete(@RequestParam("postno") int postNo) {
		boardService.deletePost(postNo);
		return "redirect:/board";
	}
}
