package code.Service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import ch.qos.logback.core.joran.conditional.ElseAction;
import code.DTO.board.BoardDto;
import code.Domain.Board.BoardEntity;
import code.Domain.Board.BoardRepository;
import code.Domain.Board.CommentEntity;
import code.Domain.Board.CommentRepository;
import code.Domain.Movie.movie.MovieEntity;
import code.Domain.User.UserEntity;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@Service@RequiredArgsConstructor @AllArgsConstructor
public class BoardService 
{
    @Autowired
    BoardRepository boardRepository;
    @Autowired
    UserService userService;
    @Autowired
    CommentRepository commentRepository;

    // 게시물 화면에서 몇개의 게시물을 보여줄 것인지
    final Integer PAGESIZE = 15;

    ///////////////////
    ////// BOARD //////
    ///////////////////

    // Create
    public String post(BoardDto dto)
    {
        if(dto.getTitle().equals(""))
        {
            return "제목을 입력해주세요.";
        }
        if(dto.getContent().equals(""))
        {
            return "내용을 입력해주세요";
        }
        if(dto.getMovieCode().equals(""))
        {
            return "영화를 선택해주세요.";
        }
        if(dto.getRating() <= 0 || 5 < dto.getRating())
        {
            return "영화의 평가를 내려주세요.";
        }

        // 누가 쓴 글인지 넣어준다.
        UserEntity writer = new UserEntity();
        writer.setUserNo(userService.nowUser().map(u -> u.getNo()).get());
        dto.setWriter(writer);

        dto.setDateTime(LocalDateTime.now().toLocalDate().toString() + " " + LocalDateTime.now().toLocalTime().toString());

        System.out.println(dto.toEntity());
        boardRepository.save(dto.toEntity());

        return "success";
    }

    // Read

    // 게시물 최신순 조회
    public JSONObject getBoardList(int pageNum, int sortBy)
    {
        JSONObject json = new JSONObject();
        Pageable pageable;

        if(sortBy == 0)
        {
            pageable = PageRequest.of(pageNum, PAGESIZE, Sort.by("board_no").descending());
        }
        else if(sortBy == 1)
        {
            pageable = PageRequest.of(pageNum, PAGESIZE, Sort.by("views").descending());
        }
        else
        {
            pageable = PageRequest.of(pageNum, PAGESIZE, Sort.by("board_no").ascending());
        }

        Page<BoardEntity> page =  boardRepository.pageOfNotSpoiler(pageable);

        JSONArray array = new JSONArray();
        for(BoardEntity e : page)
        {
            array.put(e.toJsonForList());
        }

        // 페이지 번호를 정해준다.
        int pageStart = page.getNumber()-2;
        if(pageStart < 0) pageStart = 0;
        int pageLast = page.getNumber()+2;
        if(page.getTotalPages() <= pageLast) pageLast = page.getTotalPages()-1;

        json.put("boards", array);
        json.put("nowPage", page.getNumber());
        json.put("pageStart", pageStart);
        json.put("pageLast", pageLast);

        return json;
    }

    // 게시물 영화코드로 조회
    public JSONObject getBoardListByMovieCode(String movieCode, int pageNum)
    {
        JSONObject json = new JSONObject();
        Pageable pageable = PageRequest.of(pageNum, PAGESIZE);
        Page<BoardEntity> page =  boardRepository.pageOfMovie(movieCode, pageable);

        JSONArray array = new JSONArray();
        for(BoardEntity e : page)  
        {
            array.put(e.toJsonForList());
        }

        // 페이지 번호를 정해준다.
        int pageStart = page.getNumber()-2;
        if(pageStart < 0) pageStart = 0;
        int pageLast = page.getNumber()+2;
        if(page.getTotalPages() <= pageLast) pageLast = page.getTotalPages()-1;

        json.put("boards", array);
        json.put("nowPage", page.getNumber());
        json.put("pageStart", pageStart);
        json.put("pageLast", pageLast);

        return json;
    }

    // 게시물 상세조회
    public JSONObject getBoardDetail(int boardNo) throws Exception
    {
        BoardEntity e = boardRepository.findByBoardNo(boardNo).orElseThrow(() -> new Exception("게시물을 찾을 수 없습니다."));
        JSONObject object = e.toJsonForDetail();

        // 현재 유저와 같은 유저인지
        Long nowUserNo = userService.nowUser().map(u -> u.getNo()).orElse(-1L);
        boolean sameUser = nowUserNo == e.getWriter().getUserNo();

        addView(e);

        object.put("sameUser", sameUser);

        return object;
    }

    // 게시물 조회 수 +1
    public void addView(BoardEntity e)
    {
        e.setViews(e.getViews()+1);
        boardRepository.save(e);
    }


    // Update
    public JSONObject update()
    {

        return null;
    }
    
    /////////////////////
    ////// Comment //////
    /////////////////////
    public String createComment(Long boardNo, String content) throws Exception
    {
        Long userNo = userService.nowUser().map(u -> u.getNo()).orElseThrow(() -> new Exception("로그인 상태가 아닙니다."));
        CommentEntity e = new CommentEntity();
        e.setContent(content);
        e.setDate();
        e.setBoard(new BoardEntity(boardNo));
        e.setUser(new UserEntity(userNo));

        commentRepository.save(e);

        return "success";
    }

}
