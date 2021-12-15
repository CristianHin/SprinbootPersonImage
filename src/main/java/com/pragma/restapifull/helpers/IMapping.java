package com.pragma.restapifull.helpers;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public interface IMapping {
    ModelMapper modelMapper();
}
