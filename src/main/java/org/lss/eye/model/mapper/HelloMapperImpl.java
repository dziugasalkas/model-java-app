package org.lss.eye.model.mapper;

import java.util.ArrayList;
import java.util.List;

import org.lss.eye.model.domains.HelloDto;
import org.lss.eye.model.models.Hello;
import org.springframework.stereotype.Component;

@Component
public class HelloMapperImpl implements HelloMapper {

  @Override
  public HelloDto toDomain(org.lss.eye.model.models.Hello source) {
    if (source == null) {
      return null;
    }
    return HelloDto.builder().id(source.getId()).name(source.getName()).build();
  }

  @Override
  public List<HelloDto> toDomain(List<org.lss.eye.model.models.Hello> source) {
    if (source == null) {
      return null;
    }
    List<HelloDto> list = new ArrayList<HelloDto>();
    for (Hello dto : source) {
      list.add(HelloDto.builder().id(dto.getId()).name(dto.getName()).build());
    }
    return list;
  }

  @Override
  public Hello domainToModel(HelloDto source) {
    if (source == null) {
      return null;
    }
    return Hello.builder().id(source.getId()).name(source.getName()).build();
  }

  @Override
  public List<Hello> domainToModel(List<HelloDto> source) {
    if (source == null) {
      return null;
    }
    List<Hello> list = new ArrayList<Hello>();
    for (HelloDto dto : source) {
      list.add(Hello.builder().id(dto.getId()).name(dto.getName()).build());
    }
    return list;
  }
}
