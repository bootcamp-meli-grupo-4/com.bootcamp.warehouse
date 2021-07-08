package com.mercadolibre.dambetan01.unit.service.impl;

import com.mercadolibre.dambetan01.dtos.response.AccountResponseDTO;
import com.mercadolibre.dambetan01.exceptions.ApiException;
import com.mercadolibre.dambetan01.model.user.Account;
import com.mercadolibre.dambetan01.model.user.Role;
import com.mercadolibre.dambetan01.repository.AccountRepository;
import com.mercadolibre.dambetan01.repository.RolePermissionRepository;
import com.mercadolibre.dambetan01.service.impl.SessionServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class SessionServiceImplTest {

    AccountRepository repository = Mockito.mock(AccountRepository.class);
    RolePermissionRepository rolePermissionRepository = Mockito.mock(RolePermissionRepository.class);
    SessionServiceImpl service;

    @BeforeEach
    void setUp(){
        this.service = new SessionServiceImpl(repository, rolePermissionRepository);
    }


    @Test
    void loginFail() {
        when(repository.findByUsernameAndPassword("user", "invalid")).thenReturn(null);
        assertThrows(ApiException.class, () -> service.login("user", "invalid"),
                "Usuario y/o contraseña incorrecto");
    }

    @Test
    void loginOk(){
        Role role = new Role();
        role.setId(1l);
        Account account = new Account(1l, "User", "Pass", null, role, null);
        when(repository.findByUsernameAndPassword("User", "Pass")).thenReturn(account);
        AccountResponseDTO accountDTO = service.login("User","Pass");
        assertEquals("User", accountDTO.getUsername());
        assertTrue(accountDTO.getToken().startsWith("Bearer"));
    }
}
