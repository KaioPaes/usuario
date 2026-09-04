package com.kaiopaes.usuario.business;

import com.kaiopaes.usuario.business.converter.UsuarioConverter;
import com.kaiopaes.usuario.business.dto.UsuarioDTO;
import com.kaiopaes.usuario.infrastructure.entity.Usuario;
import com.kaiopaes.usuario.infrastructure.exceptions.ConflictException;
import com.kaiopaes.usuario.infrastructure.exceptions.ResourceNotFoundException;
import com.kaiopaes.usuario.infrastructure.repository.UsuarioRepository;
import com.kaiopaes.usuario.infrastructure.security.JwtUtil;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password4j.BcryptPassword4jPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final UsuarioConverter usuarioConverter;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public UsuarioDTO salvaUsuario(UsuarioDTO usuarioDTO){
        emailExiste(usuarioDTO.getEmail());
        usuarioDTO.setPassword(passwordEncoder.encode(usuarioDTO.getPassword()));
        Usuario usuario = usuarioConverter.paraUsuario(usuarioDTO);
        return usuarioConverter.paraUsuarioDTO(usuarioRepository.save(usuario));
    }

    public void emailExiste(String email){
        try {
            boolean existe = verificaEmailExistente(email);
            if(existe){
                throw new ConflictException("Email já cadastrado" + email);
            }
        }catch (ConflictException e){
            throw new ConflictException("Email já cadastrado", e.getCause());
        }
    }

    public boolean verificaEmailExistente(String email){
        return usuarioRepository.existsByEmail(email);
    }

    public Usuario buscaUsuarioPorEmail(String email){
        return usuarioRepository.findByEmail(email).orElseThrow(
                () -> new ResourceNotFoundException("Email não encontrado" + email));
    }

    public void deletaUsuarioPorEmail(String email){
        usuarioRepository.deleteByEmail(email);
    }

    public UsuarioDTO atualizaDadosUsuario(String token, UsuarioDTO dto){
        String email = jwtUtil.extractUsername(token.substring(7));
        dto.setPassword(dto.getPassword() != null ? passwordEncoder.encode(dto.getPassword()) : null);

        Usuario usuarioEntity = usuarioRepository.findByEmail(email).orElseThrow(() ->
                new ResourceNotFoundException("Email não encontrado"));

        Usuario usuario = usuarioConverter.updateUsuario(dto, usuarioEntity);

        return usuarioConverter.paraUsuarioDTO(usuarioRepository.save(usuario));
    }
}
