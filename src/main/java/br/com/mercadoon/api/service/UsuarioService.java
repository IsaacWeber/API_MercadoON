package br.com.mercadoon.api.service;

import br.com.mercadoon.api.entity.Usuario;
import br.com.mercadoon.api.repository.UsuarioRepository;
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

@Service
public class UsuarioService implements UserDetailsService {

    private UsuarioRepository usuarioRepository;
    private BCryptPasswordEncoder encoder;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
        this.encoder = new BCryptPasswordEncoder(12);
    }

    public List<Usuario> listar() {
        return usuarioRepository.findAll();
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByEmail(email);

        if(usuario == null) {
            throw new UsernameNotFoundException("E-mail \"" + email + "\" não econtrado.");
        }

        return new CustomUserDetails(usuario);
    }

    public Usuario buscar(Long id) {
        return usuarioRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuário não encontrado para id = " + id));
    }

    public Usuario add(Usuario usuario) {
        usuario.setSenha(encoder.encode(usuario.getSenha()));
        return usuarioRepository.save(usuario);
    }

    class CustomUserDetails implements UserDetails {

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
