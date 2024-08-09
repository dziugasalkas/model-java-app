package org.lss.eye.model.services;

import java.util.List;
import java.util.Optional;
import org.lss.eye.model.domains.HelloDto;
import org.lss.eye.model.mapper.HelloMapper;
import org.lss.eye.model.models.Hello;
import org.lss.eye.model.repository.HelloRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class HelloService {
  Logger logger = LoggerFactory.getLogger(HelloService.class);

  private final HelloMapper helloMapper;
  private final HelloRepository helloRepository;

  public HelloService(HelloMapper helloMapper, HelloRepository helloRepository) {
    this.helloMapper = helloMapper;
    this.helloRepository = helloRepository;
  }

  public List<HelloDto> getAll() {
    List<Hello> helloList = helloRepository.findAll();
    return helloMapper.toDomain(helloList);
  }

  public HelloDto createHello(HelloDto source) {
    logger.debug("Hello Source: " + source);

    Hello hello = helloMapper.domainToModel(source);
    logger.debug("Hello: " + hello);
    Hello savedHello = helloRepository.save(hello);
    return helloMapper.toDomain(savedHello);
  }

  public HelloDto partialUpateHelloDto(Long id, HelloDto source) {
    Optional<Hello> hello = helloRepository.findById(id);
    if (hello.isPresent()) {
      hello.get().setName(source.getName());
      Hello savedHello = helloRepository.save(hello.get());
      return helloMapper.toDomain(savedHello);
    }
    return null;
  }

  public void deleteHello(Long id) {
    helloRepository.deleteById(id);
  }
}
