package com.kaiopaes.usuario.business.dto;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EnderecoDTO {
    private String street;
    private Long number;
    private String complement;
    private String city;
    private String state;
    private String cep;

}
