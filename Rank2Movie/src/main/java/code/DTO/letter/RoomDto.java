package code.DTO.letter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
@AllArgsConstructor
@ToString @EqualsAndHashCode
@Builder

public class RoomDto 
{

    String roomId; // 방 번호(UUID로 만들거임.)
    String master; // 방을 만든 사람

    String movie; // 방의 주제가 되는 영화

    String title; // 방의 제목
    String content; // 방의 내용

    List<String> users = new ArrayList<>();

    public RoomDto()
    {
        // roomId
        roomId = UUID.randomUUID().toString();
    }

    public JSONObject toJSON()
    {
        JSONObject object = new JSONObject();

        object.put("roomId", roomId);
        object.put("master", getMaster());
        object.put("title", getTitle());
        object.put("content", getContent());
        object.put("movie", getMovie());

        return object;
    }

}
