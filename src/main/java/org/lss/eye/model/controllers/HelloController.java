package org.lss.eye.model.controllers;

import jakarta.servlet.http.HttpServletRequest;
import java.net.URI;
import java.util.List;
import org.lss.eye.model.domains.HelloDto;
import org.lss.eye.model.domains.ListHelloDto;
import org.lss.eye.model.services.HelloService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class HelloController {
  Logger logger = LoggerFactory.getLogger(HelloController.class);

  private final HelloService helloService;

  public HelloController(HelloService helloService) {
    this.helloService = helloService;
  }

  @GetMapping(value = "/mja/v1/hello", produces = MediaType.APPLICATION_JSON_VALUE)
  public ListHelloDto getHello() {
    logger.debug("Get All Hello Request");
    List<HelloDto> items = helloService.getAll();
    ListHelloDto listHello = ListHelloDto.builder().items(items).build();
    logger.debug("Get All Hello Response: " + listHello);
    return listHello;
  }

  @PostMapping(value = "/mja/v1/hello", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity createHello(@RequestBody HelloDto hello, HttpServletRequest request) {
    HelloDto dto = helloService.createHello(hello);
    if (dto != null) {
      URI location =
          ServletUriComponentsBuilder.fromRequestUri(request)
              .path("/{id}")
              .buildAndExpand(dto.getId())
              .toUri();
      return ResponseEntity.status(HttpStatus.NO_CONTENT).location(location).build();
    } else {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
  }

  @PatchMapping(value = "/mja/v1/hello/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity partialUpdateHello(
      @RequestBody HelloDto hello, @PathVariable Long id, HttpServletRequest request) {
    logger.info("Hello ID: " + id);
    HelloDto dto = helloService.partialUpateHelloDto(id, hello);
    if (dto != null) {
      URI location =
          ServletUriComponentsBuilder.fromRequestUri(request)
              .path("/{id}")
              .buildAndExpand(dto.getId())
              .toUri();
      return ResponseEntity.status(HttpStatus.NO_CONTENT).location(location).build();
    } else {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
  }

  @DeleteMapping(value = "/mja/v1/hello/{id}")
  public ResponseEntity deleteHello(
      @RequestBody HelloDto hello, @PathVariable Long id, HttpServletRequest request) {
    helloService.deleteHello(id);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }
}
