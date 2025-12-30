package cursospringboot.exbootcamp.services;

import cursospringboot.exbootcamp.dto.ClientDTO;
import cursospringboot.exbootcamp.entities.Client;
import cursospringboot.exbootcamp.repositories.ClientRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientServices {

    ClientRepository repository;

    public ClientServices(ClientRepository repository) {
        this.repository = repository;
    }

    public Page<ClientDTO> findAll(PageRequest pageRequest){
        Page<Client> result = repository.findAll(pageRequest);
        return result.map(x -> new ClientDTO(x));
    }

    public ClientDTO findById(Long id) {
        Client entity = repository.findById(id).get();
        return new ClientDTO(entity);

    }


}
