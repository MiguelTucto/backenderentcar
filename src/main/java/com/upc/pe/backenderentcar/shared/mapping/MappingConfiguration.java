package com.upc.pe.backenderentcar.shared.mapping;

import com.upc.pe.backenderentcar.car.mapping.CarMapper;
import com.upc.pe.backenderentcar.user.mapping.UserMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("enhancedModelMapperConfiguration")
public class MappingConfiguration {
    @Bean
    public EnhancedModelMapper modelMapper() { return new EnhancedModelMapper(); }

    @Bean
    public UserMapper userMapper() {return new UserMapper(); }

    @Bean
    public CarMapper carMapper() { return new CarMapper(); }
}
