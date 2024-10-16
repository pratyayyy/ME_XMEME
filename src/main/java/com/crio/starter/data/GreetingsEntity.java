package com.crio.starter.data;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotNull;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "greetings")
@NoArgsConstructor
public class GreetingsEntity {
  @NotNull
  private String extId;

  private String message;

}
