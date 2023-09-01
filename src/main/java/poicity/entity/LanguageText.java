package poicity.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "lang_text")
public class LanguageText {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private long id_element;
	private long id_lang;
	@Column(columnDefinition = "TEXT")
	private String text;
	
}