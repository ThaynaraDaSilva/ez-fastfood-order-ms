package br.com.fiap.ez.fastfood.application.dto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UserDTOTest {

    private UserDTO userDTO;

    @BeforeEach
    void setUp() {
        userDTO = new UserDTO();
    }

    @Test
    void testSetAndGetId() {
        Long id = 1L;
        userDTO.setId(id);
        assertEquals(id, userDTO.getId());
    }

    @Test
    void testSetAndGetCpf() {
        String cpf = "123.456.789-00";
        userDTO.setCpf(cpf);
        assertEquals(cpf, userDTO.getCpf());
    }

    @Test
    void testSetAndGetName() {
        String name = "John Doe";
        userDTO.setName(name);
        assertEquals(name, userDTO.getName());
    }

    @Test
    void testSetAndGetEmail() {
        String email = "john.doe@example.com";
        userDTO.setEmail(email);
        assertEquals(email, userDTO.getEmail());
    }

    @Test
    void testDefaultValues() {
        assertNull(userDTO.getId());
        assertNull(userDTO.getCpf());
        assertNull(userDTO.getName());
        assertNull(userDTO.getEmail());
    }
}