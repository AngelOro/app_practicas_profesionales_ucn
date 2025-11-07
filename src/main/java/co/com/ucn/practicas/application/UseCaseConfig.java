package co.com.ucn.practicas.application;

import co.com.ucn.practicas.domain.usecase.VacanteUseCase;
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

}
