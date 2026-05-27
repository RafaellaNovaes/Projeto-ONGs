package br.umc.ongcaridade.controller;

import br.umc.ongcaridade.entity.Campanha;
import br.umc.ongcaridade.service.CampanhaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/campanhas")
public class CampanhaController {

    @Autowired
    private CampanhaService campanhaService;

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody Campanha campanha) throws Exception {
        campanhaService.cadastrar(campanha);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable String id) throws Exception {
        return ResponseEntity.ok(campanhaService.buscarPorId(id));
    }

    @GetMapping
    public ResponseEntity<?> listarTodas() throws Exception {
        return ResponseEntity.ok(campanhaService.listarTodas());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable String id, @RequestBody Campanha campanha) throws Exception {
        campanhaService.atualizar(id, campanha);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable String id) throws Exception {
        campanhaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
