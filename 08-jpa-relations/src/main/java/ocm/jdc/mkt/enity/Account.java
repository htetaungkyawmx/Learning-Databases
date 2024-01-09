package ocm.jdc.mkt.enity;

import java.awt.Color;
import java.io.Serializable;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
//@MappedSuperclass
@NoArgsConstructor
@RequiredArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorValue("P")
@DiscriminatorColumn(name = "account_type",discriminatorType = DiscriminatorType.CHAR)
public abstract class Account implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int id;
	@NonNull
	private String name;
	@NonNull
	private String userName;
	@NonNull
	private String password;

	
}
