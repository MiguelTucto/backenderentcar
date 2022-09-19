package com.upc.pe.backenderentcar.car.mapping;

import com.upc.pe.backenderentcar.car.domain.model.entity.Car;
import com.upc.pe.backenderentcar.car.resource.CarResource;
import com.upc.pe.backenderentcar.car.resource.CreateCarResource;
import com.upc.pe.backenderentcar.car.resource.UpdateCarResource;
import com.upc.pe.backenderentcar.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public class CarMapper implements Serializable {
    @Autowired
    EnhancedModelMapper mapper;

    public CarResource toResource(Car model) { return mapper.map(model, CarResource.class); }

    public Page<CarResource> modelListPage(List<Car> modelList, Pageable pageable) {
        return new PageImpl<>(mapper.mapList(modelList, CarResource.class), pageable, modelList.size());
    }

    public Car toModel(CreateCarResource resource) { return mapper.map(resource, Car.class); }
    public Car toModel(UpdateCarResource resource) { return mapper.map(resource, Car.class); }
}
