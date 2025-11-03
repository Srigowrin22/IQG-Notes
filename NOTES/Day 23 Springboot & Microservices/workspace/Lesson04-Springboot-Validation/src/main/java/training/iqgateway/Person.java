package training.iqgateway;

import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.mongodb.core.mapping.*;

@Document("Persons")
public class Person {
	
	@Id
	private String id;
	
	@NotEmpty
	@Size(min = 5, message = "{Size.Person.FullName}")
	private String fullName;
	
	@NotEmpty
	@Email
	private String email;
	
	@NotNull
	@Min(value = 18)
	private Integer age;
	
//	@NotEmpty(message ="Field's Can't Be Empty !")
//	private String field;
	
	public Person() {
		// TODO Auto-generated constructor stub
	}

	public Person(String id, String fullName, String email, Integer age) {
		super();
		this.id = id;
		this.fullName = fullName;
		this.email = email;
		this.age = age;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}
	
//	public String getField() {
//		return field;
//	}
//
//	public void setField(String field) {
//		this.field = field;
//	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", fullName=" + fullName + ", email=" + email + ", age=" + age + "]";
	}
	
}
