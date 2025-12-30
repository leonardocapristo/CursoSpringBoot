package cursospringboot.exbootcamp.services;

import cursospringboot.exbootcamp.dto.ClientDTO;
import cursospringboot.exbootcamp.entities.Client;
import cursospringboot.exbootcamp.repositories.ClientRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class ClientServices {

    ClientRepository clientRepository;

    public ClientServices(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Page<ClientDTO> findAll(PageRequest pageRequest){
        Page<Client> result = clientRepository.findAll(pageRequest);
        return result.map(x -> new ClientDTO(x));

    }


}
