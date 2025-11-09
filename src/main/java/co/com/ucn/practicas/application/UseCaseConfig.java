package co.com.ucn.practicas.application;

import co.com.ucn.practicas.domain.usecase.EmpresaUseCase;
import co.com.ucn.practicas.domain.usecase.EstudianteUseCase;
import co.com.ucn.practicas.domain.usecase.VacanteUseCase;
import co.com.ucn.practicas.domain.usecase.ports.EmpresaRepositoryPort;
import co.com.ucn.practicas.domain.usecase.ports.EstudianteRepositoryPort;
import co.com.ucn.practicas.domain.usecase.ports.VacanteRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
public class UseCaseConfig {

    @Bean
    VacanteUseCase vacanteUseCase(VacanteRepositoryPort vacanteRepositoryPort){
        return new VacanteUseCase(vacanteRepositoryPort);
    }

    @Bean
    EstudianteUseCase estudianteUseCase(EstudianteRepositoryPort estudianteRepositoryPort){
        return new EstudianteUseCase(estudianteRepositoryPort);
    }

    @Bean
    EmpresaUseCase empresaUseCase(EmpresaRepositoryPort empresaRepositoryPort){
        return new EmpresaUseCase(empresaRepositoryPort);
    }

}
