package org.lss.eye.model.mapper;

import java.util.List;

import org.lss.eye.model.domains.HelloDto;
import org.lss.eye.model.models.Hello;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface HelloMapper {
  HelloMapper INSTANCE = Mappers.getMapper(HelloMapper.class);

  HelloDto toDomain(Hello hello);

  List<HelloDto> toDomain(List<Hello> hello);

  Hello domainToModel(HelloDto hello);

  List<Hello> domainToModel(List<HelloDto> hello);
}
