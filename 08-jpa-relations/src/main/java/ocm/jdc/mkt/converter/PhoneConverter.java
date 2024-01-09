package ocm.jdc.mkt.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class PhoneConverter implements AttributeConverter<String, Integer>{

	@Override
	public Integer convertToDatabaseColumn(String phone) {
		var arr = phone.split("-");
		return Integer.parseInt(arr[1]);
	}

	@Override
	public String convertToEntityAttribute(Integer phone) {
		return "09-"+String.valueOf(phone);
	}

}
