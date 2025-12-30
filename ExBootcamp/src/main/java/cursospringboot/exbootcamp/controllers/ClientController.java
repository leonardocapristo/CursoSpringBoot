package cursospringboot.exbootcamp.controllers;

import cursospringboot.exbootcamp.dto.ClientDTO;
import cursospringboot.exbootcamp.services.ClientServices;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clients")
public class ClientController {

    private ClientServices service;

    public ClientController(ClientServices service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<Page<ClientDTO>> findAll (
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "12") Integer linesPerPage,
            @RequestParam(value = "orderBy", defaultValue = "name") String orderBy,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction
    ){

        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        Page<ClientDTO> list = service.findAll(pageRequest);
        return ResponseEntity.ok().body(list);
    }
}
