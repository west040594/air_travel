package com.tstu.airflight.handlers;

import com.tstu.airflight.components.Cart;
import com.tstu.airflight.dto.ClientDto;
import com.tstu.airflight.model.Client;
import com.tstu.airflight.service.ClientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Slf4j
@RequiredArgsConstructor
public class AuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler {

    private final Cart cart;
    private final ClientService clientService;
    private final ModelMapper modelMapper;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        ClientDto client = modelMapper.map(clientService.findByUsername(authentication.getName()), ClientDto.class);
        request.getSession().setAttribute("cart", cart);
        request.getSession().setAttribute("client", client);
        log.info("cart put in session - {}", cart);
        response.sendRedirect(request.getContextPath() + "/flight");
    }
}
