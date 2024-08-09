package org.lss.eye.model.domains;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class HelloDto {
  private Long id;
  private String name;
}
