package com.kaiopaes.usuario.business;

import com.kaiopaes.usuario.business.converter.UsuarioConverter;
import com.kaiopaes.usuario.business.dto.UsuarioDTO;
import com.kaiopaes.usuario.infrastructure.entity.Usuario;
import com.kaiopaes.usuario.infrastructure.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final UsuarioConverter usuarioConverter;

    public UsuarioDTO salvaUsuario(UsuarioDTO usuarioDTO){
        Usuario usuario = usuarioConverter.paraUsuario(usuarioDTO);
        return usuarioConverter.paraUsuarioDTO(usuarioRepository.save(usuario));
    }
}
