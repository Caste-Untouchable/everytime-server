package com.untouchable.everytime.Config;

import com.untouchable.everytime.DTO.BoardDTO;
import com.untouchable.everytime.Entity.BoardEntity;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MatchingStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;

@Configuration
public class ModelMapperConfig {


    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();



        return  modelMapper;
    }


}
