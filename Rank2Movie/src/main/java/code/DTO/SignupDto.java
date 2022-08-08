package code.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Size;

@Data @Builder @AllArgsConstructor
public class UserDto
{
    Long userNo;

    @Size(min=2, max=12)
    String userName;

    @Size(min=8, max=28)
    String userId;
    @Size(min=8, max=28)
    String userPassword;
    String rePw;

    String userDetail;

}
