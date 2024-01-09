package ocm.jdc.mkt.enity;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class VoucherDetails implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private VoucherDetailsPk id;
	@MapsId("voucherId")
	@ManyToOne
	private Voucher voucher;
	@MapsId("serviceId")
	@ManyToOne
	private Service service;
}







