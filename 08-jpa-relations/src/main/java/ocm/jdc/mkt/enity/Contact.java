package ocm.jdc.mkt.enity;

import java.io.Serializable;

import javax.persistence.Convert;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ocm.jdc.mkt.converter.PhoneConverter;

@Getter
@Setter
@EqualsAndHashCode
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class Contact implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String email;
	@Convert(converter  = PhoneConverter.class)
	private String phone;

}
