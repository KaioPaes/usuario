package com.kaiopaes.usuario.business.converter;

import com.kaiopaes.usuario.business.dto.EnderecoDTO;
import com.kaiopaes.usuario.business.dto.TelefoneDTO;
import com.kaiopaes.usuario.business.dto.UsuarioDTO;
import com.kaiopaes.usuario.infrastructure.entity.Endereco;
import com.kaiopaes.usuario.infrastructure.entity.Telefone;
import com.kaiopaes.usuario.infrastructure.entity.Usuario;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UsuarioConverter {
    public Usuario paraUsuario(UsuarioDTO usuarioDTO){
        return Usuario.builder()
                .name(usuarioDTO.getName())
                .email(usuarioDTO.getEmail())
                .password(usuarioDTO.getPassword())
                .enderecos(paraListaEndereco(usuarioDTO.getEnderecos()))
                .telefones(paraListaTelefone(usuarioDTO.getTelefones()))
                .build();
    }

    public List<Endereco> paraListaEndereco(List<EnderecoDTO> enderecoDTOS){
        return enderecoDTOS.stream().map(this::paraEndereco).toList();
    }

    public Endereco paraEndereco(EnderecoDTO enderecoDTO){
        return Endereco.builder()
                .street(enderecoDTO.getStreet())
                .number(enderecoDTO.getNumber())
                .state(enderecoDTO.getState())
                .cep(enderecoDTO.getCep())
                .city(enderecoDTO.getCity())
                .complement(enderecoDTO.getComplement())
                .build();
    }

    public List<Telefone> paraListaTelefone(List<TelefoneDTO> telefoneDTOS){
        return telefoneDTOS.stream().map(this::parTelefone).toList();
    }

    public Telefone parTelefone(TelefoneDTO telefoneDTO){
        return Telefone.builder()
                .number(telefoneDTO.getNumber())
                .ddd(telefoneDTO.getDdd())
                .build();
    }

    //

    public UsuarioDTO paraUsuarioDTO(Usuario usuarioDTO){
        return UsuarioDTO.builder()
                .name(usuarioDTO.getName())
                .email(usuarioDTO.getEmail())
                .password(usuarioDTO.getPassword())
                .enderecos(paraListaEnderecoDTO(usuarioDTO.getEnderecos()))
                .telefones(paraListaTelefoneDTO(usuarioDTO.getTelefones()))
                .build();
    }

    public List<EnderecoDTO> paraListaEnderecoDTO(List<Endereco> enderecoDTOS){
        return enderecoDTOS.stream().map(this::paraEnderecoDTO).toList();
    }

    public EnderecoDTO paraEnderecoDTO(Endereco enderecoDTO){
        return EnderecoDTO.builder()
                .street(enderecoDTO.getStreet())
                .number(enderecoDTO.getNumber())
                .state(enderecoDTO.getState())
                .cep(enderecoDTO.getCep())
                .city(enderecoDTO.getCity())
                .complement(enderecoDTO.getComplement())
                .build();
    }

    public List<TelefoneDTO> paraListaTelefoneDTO(List<Telefone> telefoneDTOS){
        return telefoneDTOS.stream().map(this::parTelefoneDTO).toList();
    }

    public TelefoneDTO parTelefoneDTO(Telefone telefoneDTO){
        return TelefoneDTO.builder()
                .number(telefoneDTO.getNumber())
                .ddd(telefoneDTO.getDdd())
                .build();
    }

    public Usuario updateUsuario(UsuarioDTO usuarioDTO, Usuario usuarioEntity){
        return Usuario.builder()
                .name(usuarioDTO.getName() != null ? usuarioDTO.getName() : usuarioEntity.getName())
                .id(usuarioEntity.getId())
                .password(usuarioDTO.getPassword() != null ? usuarioDTO.getPassword() : usuarioEntity.getPassword())
                .email(usuarioDTO.getEmail() != null ? usuarioDTO.getEmail() : usuarioEntity.getEmail())
                .enderecos(usuarioEntity.getEnderecos())
                .telefones(usuarioEntity.getTelefones())
                .build();
    }
}
