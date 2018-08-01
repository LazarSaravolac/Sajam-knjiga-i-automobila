package jwd.wafepa.support;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import jwd.wafepa.model.Iznajmi;
import jwd.wafepa.web.dto.IznajmiDTO;

@Component
public class IznajmiToIznajmiDTO implements Converter<Iznajmi, IznajmiDTO>{

	@Override
	public IznajmiDTO convert(Iznajmi iznajmi) {
		// TODO Auto-generated method stub
		
		IznajmiDTO dto=new IznajmiDTO();
		dto.setId(iznajmi.getId());
		return dto;
	}

}
