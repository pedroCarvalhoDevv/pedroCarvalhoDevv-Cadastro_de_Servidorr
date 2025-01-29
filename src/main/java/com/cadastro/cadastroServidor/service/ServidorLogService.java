package com.cadastro.cadastroServidor.service;

import com.cadastro.cadastroServidor.model.entity.ServidorLog;
import com.cadastro.cadastroServidor.repository.ServidorLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServidorLogService {
    @Autowired
    private ServidorLogRepository servidorLogRepository;

    public void registrarAlteracao(Long matricula, String campo, String valorAnterior, String novoValor) {
        ServidorLog log = new ServidorLog(matricula, campo, valorAnterior, novoValor);
        servidorLogRepository.save(log);
    }

}

