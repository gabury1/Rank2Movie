package code.Controller;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import code.DTO.board.BoardDto;
import code.Domain.Board.BoardRepository;
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

    // Create
    @PostMapping("/")
    @ResponseBody
    public String posting(BoardDto dto)
    {

        return boardService.post(dto);
    }

    
}
