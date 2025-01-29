package com.cadastro.cadastroServidor.service;

import com.cadastro.cadastroServidor.model.dto.LotacaoDto;
import com.cadastro.cadastroServidor.model.dto.ServidorDto;
import com.cadastro.cadastroServidor.model.entity.Lotacao;
import com.cadastro.cadastroServidor.model.entity.Servidor;
import com.cadastro.cadastroServidor.repository.ServidorLogRepository;
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
    private ServidorLogService servidorLogService;

    @Autowired
    public ServidorService(ServidorRepository servidorRepository, LotacaoService lotacaoService,ServidorLogService servidorLogService) {
        this.servidorRepository = servidorRepository;
        this.lotacaoService = lotacaoService;
        this.servidorLogService=servidorLogService;
    }
    @Autowired
    private ServidorLogRepository servidorLogRepository;

    public ServidorDto create(ServidorDto servidorDto) {

        LotacaoDto lotacaoServidor = lotacaoService.findById(servidorDto.getIdLotacao());

        Servidor servidor = new Servidor();
        servidor.setData(new Date());
        servidor.setNome(servidorDto.getNome());
        servidor.setLotacao(new Lotacao(servidorDto.getIdLotacao()));

        Servidor servidorSalvo = servidorRepository.save(servidor);

        return new ServidorDto(
                servidorSalvo.getMatricula(),
                servidorSalvo.getNome(),
                servidorSalvo.getLotacao().getId()
        );
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
        Servidor servidor=servidorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Servidor "+id+" não existe"));

        return new ServidorDto(servidor.getMatricula(),
                servidor.getNome(), servidor.getLotacao().getId());
    }
    public ServidorDto update(ServidorDto servidorDto) {
        Servidor servidor = servidorRepository.findById(servidorDto.getMatricula())
                .orElseThrow(() -> new RuntimeException("Servidor não encontrado"));

        if (!servidor.getNome().equals(servidorDto.getNome())) {
            servidorLogService.registrarAlteracao(
                    servidor.getMatricula(),
                    "nome",
                    servidor.getNome(),
                    servidorDto.getNome()
            );
            servidor.setNome(servidorDto.getNome());
        }

        if (!servidor.getLotacao().getId().equals(servidorDto.getIdLotacao())) {
            servidorLogService.registrarAlteracao(
                    servidor.getMatricula(),
                    "lotacao",
                    servidor.getLotacao().getId().toString(),
                    servidorDto.getIdLotacao().toString()
            );
            servidor.setLotacao(new Lotacao(servidorDto.getIdLotacao()));
        }

        Servidor servidorAtualizado = servidorRepository.save(servidor);
        return new ServidorDto(servidorAtualizado.getMatricula(), servidorAtualizado.getNome(), servidorAtualizado.getLotacao().getId());
    }
//    public ServidorDto update(ServidorDto servidorDto) {
//
//        Servidor servidorExistente = servidorRepository.findById(servidorDto.getMatricula())
//                .orElseThrow(() -> new RuntimeException("Servidor não encontrado."));
//
//
//        if (servidorDto.getNome() != null) {
//            servidorExistente.setNome(servidorDto.getNome());
//        }
//
//
//        if (servidorDto.getIdLotacao() != null &&
//                !servidorDto.getIdLotacao().equals(servidorExistente.getLotacao().getId())) {
//
//            LotacaoDto novaLotacao = lotacaoService.findById(servidorDto.getIdLotacao());
//            servidorExistente.setLotacao(new Lotacao(novaLotacao.getId()));
//        }
//
//
//        Servidor servidorAtualizado = servidorRepository.save(servidorExistente);
//
//
//        return new ServidorDto(
//                servidorAtualizado.getMatricula(),
//                servidorAtualizado.getNome(),
//                servidorAtualizado.getLotacao().getId()
//        );
//    }

//    public ServidorDto update(ServidorDto servidorDto){
//
//        ServidorDto servidorDto1 = findById(servidorDto.getMatricula())
//                .orElseThrow
//
//
//        Servidor servidor= new Servidor();
//        servidor.setMatricula(servidorDto.getMatricula());
//
//            if (servidorDto.getNome() != null) {
//                servidor.setNome(servidorDto.getNome());
//            }
//
//            if (servidorDto.getIdLotacao() != null ){
//            LotacaoDto servidorLotacao = lotacaoService.findById(servidorDto.getIdLotacao());
//                if (servidorLotacao != null && servidorLotacao.getId() != servidorDto1.getIdLotacao()){
//                servidor.setLotacao(new Lotacao(servidorDto1.getIdLotacao()));
//            } else {
//                    throw new RuntimeException("Não foi possivel atualizar servidor.");
//                }
//        }
//
//        Servidor servidorAtualizado = servidorRepository.save(servidor);
//
//        return new ServidorDto(
//                servidorAtualizado.getMatricula(),
//                servidorAtualizado.getNome(),
//                servidorAtualizado.getLotacao().getId());
//
//    }

    public void delete(Long id) {
        findById(id);
        servidorRepository.deleteById(id);
    }
}
