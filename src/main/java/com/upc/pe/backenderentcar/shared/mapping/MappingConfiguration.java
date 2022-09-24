package com.upc.pe.backenderentcar.shared.mapping;

import com.upc.pe.backenderentcar.car.mapping.CarMapper;
import com.upc.pe.backenderentcar.favourite.mapping.FavouriteMapper;
import com.upc.pe.backenderentcar.rent.mapping.RentMapper;
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

    @Bean
    public FavouriteMapper favouriteMapper() { return new FavouriteMapper(); }

    @Bean
    public RentMapper rentMapper() { return new RentMapper(); }
}
