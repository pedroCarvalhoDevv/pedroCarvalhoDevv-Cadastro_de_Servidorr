package com.cadastro.cadastroServidor.service;

import com.cadastro.cadastroServidor.model.dto.LotacaoDto;
import com.cadastro.cadastroServidor.model.dto.ServidorDto;
import com.cadastro.cadastroServidor.model.entity.Lotacao;
import com.cadastro.cadastroServidor.model.entity.Servidor;
import com.cadastro.cadastroServidor.repository.ServidorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ServidorService {

    private ServidorRepository servidorRepository;

    private LotacaoService lotacaoService;

    @Autowired
    public ServidorService(ServidorRepository servidorRepository,LotacaoService lotacaoService) {
        this.servidorRepository = servidorRepository;
        this.lotacaoService = lotacaoService;
    }


    public ServidorDto create(ServidorDto servidorDto){
        // objeto lotação -> colocar em uma variavel
        LotacaoDto lotacaoServidor = lotacaoService.findById(servidorDto.getIdLotacao());
        Servidor servidor = new Servidor();
        servidor.setData(new Date());
        servidor.setNome(servidorDto.getNome());
        servidor.setMatricula(servidorDto.getMatricula());
        // lotacao do findById
        servidor.setLotacao(new Lotacao(servidorDto.getIdLotacao()));
        Servidor servidor1 = servidorRepository.save(servidor);
        return new ServidorDto(servidor1.getMatricula(), servidorDto.getNome(),servidorDto.getIdLotacao());

    }

    public List<ServidorDto> findAll(){
        List<Servidor> servidorList = servidorRepository.findAll();
        List<ServidorDto> servidorDto = new ArrayList<>();

        for ( Servidor servidor1 : servidorList){
            servidorDto.add(new ServidorDto(
                    servidor1.getMatricula(),
                    servidor1.getNome(),
                    servidor1.getLotacao().getId()));
        }

        return servidorDto;
    }

    public ServidorDto findById(Long id) {
        Optional<Servidor> servidor = servidorRepository.findById(id);

        if (servidor.isPresent()) {
            Servidor servidor1 = servidor.get();

            return new ServidorDto(
                    servidor1.getMatricula(),
                    servidor1.getNome(),
                    servidor1.getLotacao().getId()
            );
        }
        throw new RuntimeException("A Servidor não existe");
    }

    public ServidorDto update(ServidorDto servidorDto){

        ServidorDto servidorDto1 = findById(servidorDto.getMatricula());

        Servidor servidor= new Servidor();
        servidor.setMatricula(servidorDto.getMatricula());

            if (servidorDto.getNome() != null) {
                servidor.setNome(servidorDto.getNome());
            }

            if (servidorDto.getIdLotacao() != null ){
            LotacaoDto servidorLotacao = lotacaoService.findById(servidorDto.getIdLotacao());
                if (servidorLotacao != null && servidorLotacao.getId() != servidorDto1.getIdLotacao()){
                servidor.setLotacao(new Lotacao(servidorDto1.getIdLotacao()));
            } else {
                    throw new RuntimeException("Não foi possivel atualizar servidor.");
                }
        }

        Servidor servidorAtualizado = servidorRepository.save(servidor);

        return new ServidorDto(
                servidorAtualizado.getMatricula(),
                servidorAtualizado.getNome(),
                servidorAtualizado.getLotacao().getId());

    }

    public void delete(Long id) {
        findById(id);
        servidorRepository.deleteById(id);
    }
}
