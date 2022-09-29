package com.upc.pe.backenderentcar.favourite.mapping;

import com.upc.pe.backenderentcar.favourite.domain.model.entity.Favourite;
import com.upc.pe.backenderentcar.favourite.resource.CreateFavouriteResource;
import com.upc.pe.backenderentcar.favourite.resource.FavouriteResource;
import com.upc.pe.backenderentcar.favourite.resource.UpdateFavouriteResource;
import com.upc.pe.backenderentcar.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public class FavouriteMapper implements Serializable {
    @Autowired
    private EnhancedModelMapper mapper;

    public FavouriteResource toResource(Favourite model) { return mapper.map(model, FavouriteResource.class); }

    public Page<FavouriteResource> modelListPage(List<Favourite> modelList, Pageable pageable) {
        return new PageImpl<>(mapper.mapList(modelList, FavouriteResource.class), pageable, modelList.size());
    }

    public Favourite toModel(CreateFavouriteResource resource) { return mapper.map(resource, Favourite.class); }
    public Favourite toModel(UpdateFavouriteResource resource) { return mapper.map(resource, Favourite.class); }
}
