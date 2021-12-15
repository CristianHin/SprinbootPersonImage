package com.pragma.restapifull.helpers;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class Mapping implements IMapping {

	@Override
	public  ModelMapper modelMapper() {
		return new ModelMapper();
	}



}
