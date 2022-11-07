package code.DTO.search;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;

import lombok.*;

@Getter @Setter @Builder
@ToString @EqualsAndHashCode
@RequiredArgsConstructor @AllArgsConstructor
public class SearchDto 
{
    // pageNum : 페이지 번호
    // key : 어떤 항목을 기반으로 검색할 것인지
    // str : 검색할 문자열
    // sortBy : 어떤 항목을 바탕으로 정렬할 것인지
    // opened : 개봉된 영화만을 볼 것이라면 true

    int pageNum; 
    int key; 
    String str; 
    Boolean opened; 
    int sortBy;

    static final int pageAmount = 30;

    public String toQuery()
    {
        String keyStr;
        String openedStr;
        if(key == 0) keyStr="title_kor";
        switch (key){
            case 0 : keyStr = "title_kor";
                     break;
            case 1 : keyStr = "director";
                     break;
            case 2 : keyStr = "actor";
                     break;
            default : keyStr = "genre";
                     break;
        }


        String query = "SELECT * FROM movie WHERE " + keyStr + " LIKE %" + str + "%";
        if(opened) query += "AND product_status = '개봉'";

        return query;
    }

    public Pageable toPageable()
    {
        String columnString = "views";
        if(sortBy == 1) columnString = "avr_rating";
        Pageable pageable = PageRequest.of(pageNum, pageAmount, Sort.by(columnString));

        return pageable;
    }

}
