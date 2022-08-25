package com.itwillbs.controller;

import java.io.File;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.itwillbs.domain.BoardDTO;
import com.itwillbs.domain.PageDTO;
import com.itwillbs.service.BoardService;

@Controller
public class BoardController {
	
	// 객체생성 부모인터페이스 = 자식클래스
	@Inject
	private BoardService boardService;
	
	// 업로드 경로 survlet-context.xml upload폴더 경로 이름
	@Resource(name = "uploadPath")
	private String uploadPath;
	
//	 가상주소 시작점: http://localhost:8080/myweb/board/write
	@RequestMapping(value = "/board/write", method = RequestMethod.GET)
	public String insert() {
		// 주소변경없이 이동
		// Web-INF/view/board/writeForm.jsp 이동
		return "/board/writeForm";
	}

//	 가상주소 시작점: http://localhost:8080/myweb/board/writePro
	@RequestMapping(value = "/board/writePro", method = RequestMethod.POST)
	public String writePro(BoardDTO boardDTO) {
		// 주소변경하면서 이동
		boardService.insertBoard(boardDTO);
		// Web-INF/view/board/list.jsp 이동
		return "redirect:/board/list";
	}
	
//	 가상주소 시작점: http://localhost:8080/myweb/board/list
//	 가상주소 시작점: http://localhost:8080/myweb/board/list?pageNum=2
	@RequestMapping(value = "/board/list", method = RequestMethod.GET)
	public String list(HttpServletRequest request, Model model) {
		// 한 화면에 보여줄 글 개수
		int pageSize=10;
		//현페이지 번호
		
		String pageNum = request.getParameter("pageNum");
		if(pageNum == null) {
			pageNum ="1";
		}
		// 현페이지 번호를 정수형으로 변경
		int currentPage = Integer.parseInt(pageNum);
		// PageDTO 객체 생성
		PageDTO pageDTO = new PageDTO();
		pageDTO.setPageSize(pageSize);
		pageDTO.setPageNum(pageNum);
		pageDTO.setCurrentPage(currentPage);
		
		List<BoardDTO> boardList= boardService.getBoardList(pageDTO);
		
		// pageBlock startPage endPage count pageCount
		int count = boardService.getBoardCount();
		int pageBlock = 10;
		int startPage = (currentPage=1)/pageBlock*pageBlock+1;
		int endPage = startPage+pageBlock-1;
		int pageCount = count /pageSize +(count % pageSize==0?0:1);
		if(endPage > pageCount) {
			endPage = pageCount;
		}
		
		pageDTO.setCount(count);
		pageDTO.setPageBlock(pageBlock);
		pageDTO.setStartPage(startPage);
		pageDTO.setEndPage(endPage);
		pageDTO.setPageCount(pageCount);
		
		//데이터 담아서 list.jsp
		model.addAttribute("boardList",boardList);
		model.addAttribute("pageDTO", pageDTO);
		
		
		
		// 데이터 담아서 list.jsp 이동
		model.addAttribute("boardlist", boardList);
		
		
		// 주소변경없이 이동
		// Web-INF/view/board/list.jsp 이동
		return "/board/list";
	}
	
//	 가상주소 시작점: http://localhost:8080/myweb/board/fwrite
	@RequestMapping(value = "/board/fwrite", method = RequestMethod.GET)
	public String finsert() {
		// 주소변경없이 이동
		// Web-INF/view/board/fwriteForm.jsp 이동
		return "/board/fwriteForm";
	}

//	 가상주소 시작점: http://localhost:8080/myweb/board/writePro
	@RequestMapping(value = "/board/fwritePro", method = RequestMethod.POST)
	public String fwritePro(HttpServletRequest request, MultipartFile file) throws Exception  {
		
		// 파일 이름 => 랜덤문자_파일이름
		
		UUID uuid = UUID.randomUUID();
		String FileName = uuid.toString() + "_" + file.getOriginalFilename();
		
		// 업로드파일 file.getBytes()=> upload/랜덤문자_파일이름 복사
		File uploadfile = new File(uploadPath,FileName);
		FileCopyUtils.copy(file.getBytes(), uploadfile);
		
		BoardDTO boardDTO = new BoardDTO();
		boardDTO.setName(request.getParameter("name"));
		boardDTO.setPass(request.getParameter("pass"));
		boardDTO.setSubject(request.getParameter("subject"));
		boardDTO.setContent(request.getParameter("content"));
		boardDTO.setFile(FileName);
		boardService.insertBoard(boardDTO);
		// Web-INF/view/board/list.jsp 이동
		return "redirect:/board/list";
	}
	
//	 가상주소 시작점: http://localhost:8080/myweb/board/content?num=2
	@RequestMapping(value = "/board/content", method = RequestMethod.GET)
	public String content(HttpServletRequest request, Model model ) {
		// 파라미터 가져오기
		int num =Integer.parseInt(request.getParameter("num"));
		
		// 디비 조회
		BoardDTO boardDTO =  boardService.getBoard(num);
		
		// model에 데이터 저장
		model.addAttribute("boardDTO",boardDTO);
		
		// 주소변경없이 이동
		// Web-INF/view/board/content.jsp 이동
		return "/board/content";
	}
	
//	 가상주소 시작점: http://localhost:8080/myweb/board/content?num=2
	@RequestMapping(value = "/board/update", method = RequestMethod.GET)
	public String update(HttpServletRequest request, Model model ) {
		// 파라미터 가져오기
		int num =Integer.parseInt(request.getParameter("num"));
		
		// 디비 조회
		BoardDTO boardDTO =  boardService.getBoard(num);
		
		// model에 데이터 저장
		model.addAttribute("boardDTO",boardDTO);
		
		// 주소변경없이 이동
		// Web-INF/view/board/updateForm.jsp 이동
		return "/board/updateForm";
	}
	
//	 가상주소 시작점: http://localhost:8080/myweb/board/updatePro
	@RequestMapping(value = "/board/updatePro", method = RequestMethod.POST)
	public String updatePro(BoardDTO boardDTO) {
		// num pass 일치 확인 
		BoardDTO boardDTO2= boardService.numCheck(boardDTO);
		if(boardDTO2!=null) {
			// num pass 일치
			boardService.updateBoard(boardDTO);
			// 주소변경하면서 이동
			// Web-INF/view/board/list.jsp 이동
			return "redirect:/board/list";
		} else {
			// 아이디 비밀번호가 틀림
			// "틀림" 뒤로이동
			// 주소변경없이 이동
			// Web-INF/view/board/msg.jsp 이동
			return "member/msg";
		}
		
				
		

	}
}
