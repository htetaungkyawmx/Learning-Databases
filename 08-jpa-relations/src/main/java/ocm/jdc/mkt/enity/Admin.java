package ocm.jdc.mkt.enity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@DiscriminatorValue("A")
public class Admin extends Account{

	private static final long serialVersionUID = 1L;
	
	private String type;
	

	public Admin(String name,String userName,String pass,String type) {
		super(name,userName,pass);
		this.type = type;
	}
}
