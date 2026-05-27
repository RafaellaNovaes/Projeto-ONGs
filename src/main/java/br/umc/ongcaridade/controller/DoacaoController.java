package br.umc.ongcaridade.controller;

import br.umc.ongcaridade.entity.doacao.DoacaoFinanceira;
import br.umc.ongcaridade.service.DoacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/doacoes")
public class DoacaoController {

    @Autowired
    private DoacaoService doacaoService;

    @PostMapping
    public ResponseEntity<?> realizar(@RequestBody DoacaoFinanceira doacao) throws Exception {
        doacaoService.realizar(doacao);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/doador/{doadorId}")
    public ResponseEntity<?> listarPorDoador(@PathVariable String doadorId) throws Exception {
        return ResponseEntity.ok(doacaoService.listarPorDoador(doadorId));
    }

    @GetMapping("/campanha/{campanhaId}")
    public ResponseEntity<?> listarPorCampanha(@PathVariable String campanhaId) throws Exception {
        return ResponseEntity.ok(doacaoService.listarPorCampanha(campanhaId));
    }
}
