package ocm.jdc.mkt.enity;

import java.io.Serializable;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@DiscriminatorValue("C")
public class Customer extends Account implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@NonNull
	@AttributeOverride(name = "email" ,column = @Column(name = "p_email"))
	@AttributeOverride(name ="phone",column = @Column(name = "p_phone"))
	private Contact primaryContact;
//	@AttributeOverride(name = "email" ,column = @Column(name = "s_email"))
//	@AttributeOverride(name ="phone",column = @Column(name = "s_phone"))
//	private Contact secondaryContact;
	@OneToOne
//	@JoinTable(name ="cu_address_tbl", joinColumns = @JoinColumn(name = "cu_id",referencedColumnName = ""), 
//	inverseJoinColumns = @JoinColumn(name = "addres_id")
//	)
	private Address address;
	
	
	
	public Customer(String name,String userName,String pass,String email,String phone) {
		super(name,userName,pass);
		this.primaryContact = new Contact(email,phone);
	}
}











