package jwd.wafepa.support;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import jwd.wafepa.model.Kupi;
import jwd.wafepa.web.dto.KupiDTO;

@Component
public class KupiToKupiDTO implements Converter<Kupi, KupiDTO>{

	@Override
	public KupiDTO convert(Kupi kupi) {
		// TODO Auto-generated method stub
		KupiDTO dto=new KupiDTO();
		dto.setId(kupi.getId());
		return dto;
	}

}
