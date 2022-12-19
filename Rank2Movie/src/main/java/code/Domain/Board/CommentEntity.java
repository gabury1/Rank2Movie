package code.Domain.Board;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.json.JSONObject;

import code.Domain.User.UserEntity;
import lombok.Data;

@Entity 
@Table(name="comment") @Data
public class CommentEntity 
{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_no")
    Long commentNo;
    
    @Column(name = "content")
    String content;

    @Column(name = "date")
    String date;

    @ManyToOne
    @JoinColumn(name = "writer_no")
    UserEntity user;

    @ManyToOne
    @JoinColumn(name = "board_no")
    BoardEntity board;

    public void setDate()
    {
         date = LocalDateTime.now().toLocalDate().toString();
    }

    public JSONObject toJSON()  
    {
        JSONObject object = new JSONObject();

        object.put("writer", user.getUserName());
        object.put("content", content);
        object.put("date", date);

        return object;
    }


}
