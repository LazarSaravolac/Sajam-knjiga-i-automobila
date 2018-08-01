package jwd.wafepa.support;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import jwd.wafepa.model.Kompanija;
import jwd.wafepa.web.dto.KompanijaDTO;

@Component
public class KompanijaDTOtoKompanija implements Converter<KompanijaDTO, Kompanija>{

	@Override
	public Kompanija convert(KompanijaDTO source) {
		// TODO Auto-generated method stub
		return null;
	}

}
