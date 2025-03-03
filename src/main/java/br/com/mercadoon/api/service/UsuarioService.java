package br.com.mercadoon.api.service;

import br.com.mercadoon.api.dto.UsuarioDto;
import br.com.mercadoon.api.entity.Usuario;
import br.com.mercadoon.api.exception.UsuarioNotFoundException;
import br.com.mercadoon.api.repository.UsuarioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService implements UserDetailsService {

    private UsuarioRepository usuarioRepository;
    private ModelMapper modelMapper;
    private BCryptPasswordEncoder encoder;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository, ModelMapper modelMapper) {
        this.usuarioRepository = usuarioRepository;
        this.modelMapper = modelMapper;
        this.encoder = new BCryptPasswordEncoder(12);
    }

    public List<UsuarioDto> listar() {
        return usuarioRepository.findAll()
                                .stream()
                                .map(u -> modelMapper.map(u, UsuarioDto.class))
                                .collect(Collectors.toList());
    }

    public UsuarioDto buscar(Long id) {
        return modelMapper.map(
                    usuarioRepository.findById(id)
                    .orElseThrow(() -> new UsuarioNotFoundException("Usuário não encontrado para id = " + id)),
                UsuarioDto.class);
    }

    public UsuarioDto add(Usuario usuario) {
        usuario.setId(null);
        usuario.setSenha(encoder.encode(usuario.getSenha()));
        return modelMapper.map(usuarioRepository.save(usuario), UsuarioDto.class);
    }

    public UsuarioDto atualizar(Long id, Usuario novoUsuario) {
        Usuario bdUsuario = usuarioRepository.findById(id)
                                            .orElseThrow(() -> new UsuarioNotFoundException("Usuário não encontrado para id = " + id));
        bdUsuario.setEmail(novoUsuario.getEmail());
        return modelMapper.map(usuarioRepository.save(bdUsuario), UsuarioDto.class);
    }

    public void deletar(Long id) {
        Usuario bdUsuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new UsuarioNotFoundException("Usuário não encontrado para id = " + id));

        usuarioRepository.deleteById(id);
    }

    public void deletarTodos() {
        usuarioRepository.deleteAll();
    }

    // User Details
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByEmail(email);

        if(usuario == null) {
            throw new UsernameNotFoundException("E-mail \"" + email + "\" não econtrado.");
        }

        return new CustomUserDetails(usuario);
    }

    public class CustomUserDetails implements UserDetails {

        private Usuario usuario;

        public CustomUserDetails(Usuario usuario) {
            this.usuario = usuario;
        }

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            return List.of(new SimpleGrantedAuthority("USER"));
        }

        @Override
        public String getPassword() {
            return usuario.getSenha();
        }

        @Override
        public String getUsername() {
            return usuario.getEmail();
        }

        @Override
        public boolean isAccountNonExpired() {
            return true;
        }

        @Override
        public boolean isAccountNonLocked() {
            return true;
        }

        @Override
        public boolean isCredentialsNonExpired() {
            return true;
        }

        @Override
        public boolean isEnabled() {
            return true;
        }
    }
}
