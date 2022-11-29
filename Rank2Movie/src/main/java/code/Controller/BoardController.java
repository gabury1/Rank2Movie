package code.Controller;

import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import code.DTO.board.BoardDto;
import code.Domain.Board.BoardRepository;
import code.Domain.Board.CommentEntity;
import code.Domain.User.UserEntity;
import code.Service.BoardService;

@Controller
@RequestMapping("/board")
public class BoardController 
{
    @Autowired
    BoardService boardService;

    // 게시물 쓰기 페이지
    @GetMapping("/posting")
    public String create()
    {
        return "/board/create";
    }

    // 상세조회 페이지
    @GetMapping("/{no}")
    public String detail()
    {
        return "/board/detail";
    }

    // Create
    @PostMapping("/")
    @ResponseBody
    public String posting(BoardDto dto)
    {

        return boardService.post(dto);
    }

    //Read
    // 메인화면 게시물 탭에 들어갈 리스트
    @GetMapping("/main_list")
    public void read(Integer pageNum, int sortBy, HttpServletResponse response)
    {
        try{
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json");
            response.getWriter().println(boardService.getBoardList(pageNum, sortBy));

        }catch(Exception e)
        {
            System.out.println(e.getLocalizedMessage());
        }
    }

    // 영화 상세보기에 들어갈 리스트
    @GetMapping("/movie_list")
    public void read(String code, Integer page, HttpServletResponse response)
    {
        try{
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json");
            response.getWriter().println(boardService.getBoardListByMovieCode(code, page));

        }catch(Exception e)
        {
            System.out.println(e.getLocalizedMessage());
        }

    }

    // 상세조회
    @GetMapping("/detail")
    public void read(Integer no, HttpServletResponse response)
    {
        try{
            JSONObject object = boardService.getBoardDetail(no);

            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json");
            response.getWriter().println(object);

        }catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
        
    }

    ///////////////////////////
    ///////// Comment /////////
    ///////////////////////////

    // Create
    @PostMapping("/comment")
    @ResponseBody
    public String createComment(Long boardNo, String content)
    {
        try{
            boardService.createComment(boardNo, content);
            return "success";
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            return "fail";
        }
        
    } 

    // Read
    @GetMapping("/comment")
    public void getComment()
    {

        
    }

    //



}
