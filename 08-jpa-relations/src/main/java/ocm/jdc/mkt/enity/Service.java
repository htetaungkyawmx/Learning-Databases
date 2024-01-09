package ocm.jdc.mkt.enity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.JoinColumn;

@Getter
@Setter
@Entity
public class Service implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private ServiceType serviceType;
	private int price;
	@ManyToOne
	@JoinTable(name = "service_vehi_tbl", 
	joinColumns = @JoinColumn(name = "service_id"), 
	inverseJoinColumns = @JoinColumn(name = "vehicle_id"))
	private Vehicle vehicles;
	
	public enum ServiceType{
		WASH,KIT,SOAP
	}
	
	
}
