package com.kaiopaes.usuario.business.dto;

import com.kaiopaes.usuario.infrastructure.entity.Endereco;
import com.kaiopaes.usuario.infrastructure.entity.Telefone;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsuarioDTO {
    private String name;
    private String email;
    private String password;
    private List<EnderecoDTO> enderecos;
    private List<TelefoneDTO> telefones;
}
