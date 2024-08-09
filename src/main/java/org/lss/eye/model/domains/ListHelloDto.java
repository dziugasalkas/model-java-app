package org.lss.eye.model.domains;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ListHelloDto {
  List<HelloDto> items;
}
