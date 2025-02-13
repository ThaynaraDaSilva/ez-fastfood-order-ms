package br.com.fiap.ez.fastfood.application.dto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UserResponseDTO_OLDTest {

    private UserResponseDTO_OLD userResponseDTO;

    @BeforeEach
    void setUp() {
        userResponseDTO = new UserResponseDTO_OLD();
    }

    @Test
    void testDefaultConstructor() {
        assertNull(userResponseDTO.getId());
        assertNull(userResponseDTO.getCpf());
        assertNull(userResponseDTO.getName());
        assertNull(userResponseDTO.getEmail());
    }

    @Test
    void testParameterizedConstructor() {
        Long id = 1L;
        String cpf = "123.456.789-00";
        String name = "John Doe";
        String email = "john.doe@example.com";

        userResponseDTO = new UserResponseDTO_OLD(id, cpf, name, email);

        assertEquals(id, userResponseDTO.getId());
        assertEquals(cpf, userResponseDTO.getCpf());
        assertEquals(name, userResponseDTO.getName());
        assertEquals(email, userResponseDTO.getEmail());
    }

    @Test
    void testSetAndGetId() {
        Long id = 1L;
        userResponseDTO.setId(id);
        assertEquals(id, userResponseDTO.getId());
    }

    @Test
    void testSetAndGetCpf() {
        String cpf = "123.456.789-00";
        userResponseDTO.setCpf(cpf);
        assertEquals(cpf, userResponseDTO.getCpf());
    }

    @Test
    void testSetAndGetName() {
        String name = "John Doe";
        userResponseDTO.setName(name);
        assertEquals(name, userResponseDTO.getName());
    }

    @Test
    void testSetAndGetEmail() {
        String email = "john.doe@example.com";
        userResponseDTO.setEmail(email);
        assertEquals(email, userResponseDTO.getEmail());
    }
}