package code.DTO.letter;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import groovy.transform.EqualsAndHashCode;
import groovy.transform.ToString;
import groovy.transform.builder.Builder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter @Setter 
@RequiredArgsConstructor
@AllArgsConstructor
@ToString @EqualsAndHashCode
@Builder

public class RoomDto 
{
    Long roomNo; // 방 번호
    String master; // 방을 만든 사람

    String movie; // 방의 주제가 되는 영화

    String title; // 방의 제목
    String content; // 방의 내용

    List<String> users = new ArrayList<>();

    public JSONObject toJSON()
    {
        JSONObject object = new JSONObject();

        object.put("roomNo", getRoomNo());
        object.put("master", getMaster());
        object.put("title", getTitle());
        object.put("content", getContent());
        object.put("movie", getMovie());

        return object;
    }

}
