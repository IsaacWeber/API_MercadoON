package br.com.mercadoon.api.rest;

import br.com.mercadoon.api.dto.UsuarioDto;
import br.com.mercadoon.api.entity.Usuario;
import br.com.mercadoon.api.exception.UsuarioNotFoundException;
import br.com.mercadoon.api.repository.UsuarioRepository;
import br.com.mercadoon.api.service.UsuarioService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.LinkedHashMap;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc(addFilters = false)
@SpringBootTest
public class UsuarioRestControllerTest {

    private UsuarioRepository usuarioRepository;
    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @Autowired
    public UsuarioRestControllerTest(MockMvc mockMvc, ObjectMapper objectMapper, UsuarioRepository usuarioRepository) {
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
        this.usuarioRepository = usuarioRepository;
    }

    @DisplayName("Listar")
    @Test
    public void listar() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/usuario"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", isA(List.class)));
    }

    @DisplayName("Buscar por Id")
    @Test
    public void buscar() throws Exception {

        // Adiciona
        Usuario usuario = new Usuario();
        usuario.setEmail("teste@email.com");
        usuario.setSenha("t@2025");

        mockMvc.perform(MockMvcRequestBuilders.post("/api/usuario")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(usuario)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$", isA(LinkedHashMap.class)));


        // Busca Usuário Existente
        usuario = usuarioRepository.findByEmail("teste@email.com");
        assertNotNull(usuario);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/usuario/" + usuario.getId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", isA(LinkedHashMap.class)));

        // Busca Usuário Não Existente
        Long idUsuarioNaoExistente = (long) usuarioRepository.findAll().size();

        mockMvc.perform(MockMvcRequestBuilders.get("/api/usuario/" + idUsuarioNaoExistente))
            .andExpect(status().isNotFound())
            .andExpect(result -> assertTrue(result.getResolvedException() instanceof UsuarioNotFoundException));

        // Remove do BD
        usuarioRepository.deleteById(usuario.getId());
        assertNull(usuarioRepository.findById(usuario.getId()).orElse(null));
    }


    @DisplayName("Adicionar")
    @Test
    public void add() throws Exception {
        Usuario usuario = new Usuario();
        usuario.setEmail("teste@email.com");
        usuario.setSenha("t@2025");

        mockMvc.perform(MockMvcRequestBuilders.post("/api/usuario")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(usuario)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$", isA(LinkedHashMap.class)));

        usuario = usuarioRepository.findByEmail("teste@email.com");

        assertNotNull(usuario);

        usuarioRepository.deleteById(usuario.getId());

        assertNull(usuarioRepository.findById(usuario.getId()).orElse(null));
    }

    @DisplayName("Atualizar")
    @Test
    public void atualizar() throws Exception {
        Usuario usuario = new Usuario();
        usuario.setEmail("teste@email.com");
        usuario.setSenha("t@2025");

        // Adiciona
        mockMvc.perform(MockMvcRequestBuilders.post("/api/usuario")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(usuario)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$", isA(LinkedHashMap.class)));

        usuario = usuarioRepository.findByEmail("teste@email.com");
        assertNotNull(usuario);

        // Atualiza usuário existente
        usuario.setEmail("teste_atualiza@email.com");
        mockMvc.perform(MockMvcRequestBuilders.put("/api/usuario/" + usuario.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(usuario)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", isA(LinkedHashMap.class)));

        // Atualiza usuário não existente
        mockMvc.perform(MockMvcRequestBuilders.put("/api/usuario/" + usuarioRepository.findAll().size())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(usuario)))
                .andExpect(status().isNotFound())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof UsuarioNotFoundException));

        // Remove do BD
        usuarioRepository.deleteById(usuario.getId());
        assertNull(usuarioRepository.findById(usuario.getId()).orElse(null));

    }

    @DisplayName("Deletar por Id")
    @Test
    public void deletarPorId() throws Exception {

        // Adiciona
        Usuario usuario = new Usuario();
        usuario.setEmail("teste@email.com");
        usuario.setSenha("t@2025");

        mockMvc.perform(MockMvcRequestBuilders.post("/api/usuario")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(usuario)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$", isA(LinkedHashMap.class)));


        // Deleta Usuário Existente
        usuario = usuarioRepository.findByEmail("teste@email.com");
        assertNotNull(usuario);

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/usuario/" + usuario.getId()))
                .andExpect(status().isNoContent());

        // Delete usuário não existente
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/usuario/" + usuarioRepository.findAll().size()))
                .andExpect(status().isNotFound())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof UsuarioNotFoundException));

    }

    @DisplayName("Deletar Todos")
    @Test
    public void deletarTodos() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/usuario"))
                .andExpect(status().isNoContent());
    }

}
