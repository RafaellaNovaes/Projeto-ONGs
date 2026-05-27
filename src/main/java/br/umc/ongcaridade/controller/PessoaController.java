package br.umc.ongcaridade.controller;

import br.umc.ongcaridade.entity.Organizador;
import br.umc.ongcaridade.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {

    @Autowired
    private PessoaService pessoaService;

    @PostMapping("/cadastro")
    public ResponseEntity<?> cadastrar(@RequestBody Organizador pessoa) throws Exception {
        pessoaService.cadastrar(pessoa);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> body) throws Exception {
        String token = pessoaService.login(body.get("email"), body.get("senha"));
        return ResponseEntity.ok(Map.of("token", token));
    }
}
