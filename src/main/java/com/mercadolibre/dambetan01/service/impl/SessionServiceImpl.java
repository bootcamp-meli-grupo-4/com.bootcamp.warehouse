package com.mercadolibre.dambetan01.service.impl;

import com.mercadolibre.dambetan01.dtos.response.AccountResponseDTO;
import com.mercadolibre.dambetan01.exceptions.ApiException;
import com.mercadolibre.dambetan01.model.user.Account;
import com.mercadolibre.dambetan01.repository.AccountRepository;
import com.mercadolibre.dambetan01.repository.RolePermissionRepository;
import com.mercadolibre.dambetan01.service.ISessionService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SessionServiceImpl implements ISessionService {
    private final AccountRepository accountRepository;

    private final RolePermissionRepository rolePermissionRepository;

    /**
     * Realiza la validación del usuario y contraseña ingresado.
     * En caso de ser correcto, devuelve la cuenta con el token necesario para realizar las demás consultas.
     *
     * @param username
     * @param password
     * @return
     * @throws NotFoundException
     */
    @Override
    public AccountResponseDTO login(String username, String password) throws ApiException {
        //Voy a la base de datos y reviso que el usuario y contraseña existan.
        Account account = accountRepository.findByUsernameAndPassword(username, password);

        if (account != null) {
            String token = getJWTToken(account);
            AccountResponseDTO user = new AccountResponseDTO();
            user.setUsername(username);
            user.setToken(token);
            return user;
        } else {
            throw new ApiException("404", "Usuario y/o contraseña incorrecto", 404);
        }

    }

    /**
     * Genera un token para un usuario específico, válido por 10'
     * @param account
     * @return
     */
    private String getJWTToken(Account account) {
        String secretKey = "mySecretKey";

        List<String> permissions =
                rolePermissionRepository.findPermissionByRoleId(account.getRole().getId())
                .stream()
                        .map(permission -> permission.getName().name())
                        .collect(Collectors.toList());

        String permissionsConcat = StringUtils.join(permissions, ",");

        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList("ROLE_USER," + permissionsConcat);

        String token = Jwts
                .builder()
                .setId("softtekJWT")
                .setSubject(account.getId().toString())
                .claim("authorities",
                        grantedAuthorities.stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 999999999))
                .signWith(SignatureAlgorithm.HS512,
                        secretKey.getBytes()).compact();

        return "Bearer " + token;
    }

    /**
     * Decodifica un token para poder obtener los componentes que contiene el mismo
     * @param token
     * @return
     */
    private static Claims decodeJWT(String token) {
        Claims claims = Jwts.parser().setSigningKey("mySecretKey".getBytes())
                .parseClaimsJws(token).getBody();
        return claims;
    }

    /**
     * Permite obtener el username según el token indicado
     * @param token
     * @return
     */
    public static String getUsername(String token) {
        Claims claims = decodeJWT(token);
        return claims.get("sub", String.class);
    }

}
