package code.DTO.letter;

import groovy.transform.EqualsAndHashCode;
import groovy.transform.ToString;
import groovy.transform.builder.Builder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter @Setter @ToString
@Builder @EqualsAndHashCode
public class MessageDto 
{
    String roomId;
    String content;
}
