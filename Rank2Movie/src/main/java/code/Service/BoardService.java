package code.Service;

import java.time.LocalDateTime;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import code.DTO.board.BoardDto;
import code.Domain.Board.BoardRepository;
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

        dto.setDateTime(LocalDateTime.now().toString());

        System.out.println(dto.toEntity());
        boardRepository.save(dto.toEntity());

        return "success";
    }

    // Read

    // 게시물 최신순 조회
    public JSONObject getBoardList()
    {
        
        return null;
    }

    // 게시물 영화코드로 조회
    public JSONObject getBoardListByMovieCode()
    {

        return null;
    }

    // 게시물 상세조회
    public JSONObject getBoardDetail()
    {

        return null;
    }

    // Update
    public JSONObject update()
    {

        return null;
    }
    
}
