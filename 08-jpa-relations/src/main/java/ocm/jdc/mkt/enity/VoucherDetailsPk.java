package ocm.jdc.mkt.enity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
@EqualsAndHashCode
public class VoucherDetailsPk implements Serializable{

	private static final long serialVersionUID = 1L;
	//@Column(name = "voucher_id")
	private int voucherId;
	//@Column(name = "service_id")
	private int serviceId;
}
