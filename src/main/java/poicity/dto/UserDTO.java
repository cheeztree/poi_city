package poicity.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
	
}
