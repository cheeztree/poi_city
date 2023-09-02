package poicity.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import poicity.entity.Language;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

//    private Long id;
	private String username;
    private String name;
    private String lastname;
    private String email;
    private String password; 
//    private Language lang;
    private long lang_id;
	
}
