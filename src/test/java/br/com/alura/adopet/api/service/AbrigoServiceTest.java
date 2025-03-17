package br.com.alura.adopet.api.service;


import br.com.alura.adopet.api.model.Abrigo;
import br.com.alura.adopet.api.repository.AbrigoRepository;
import br.com.alura.adopet.api.repository.PetRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
public class AbrigoServiceTest {

    @InjectMocks
    private AbrigoService abrigoService;

    @Mock
    private AbrigoRepository abrigoRepository;

    @Mock
    private PetRepository petRepository;

    @Mock
    private Abrigo abrigo;

    @Test
    void listarTodos(){
        abrigoService.listar();

        then(abrigoRepository).should().findAll();
    }

    @Test
    void deveriaChamarListaDePetsDoAbrigoAtravesDoNome() {
        //Arrange
        String nome = "Miau";
        given(abrigoRepository.findByNome(nome)).willReturn(Optional.of(abrigo));

        //Act
        abrigoService.listarPetsDoAbrigo(nome);

        //Assert
        then(petRepository).should().findByAbrigo(abrigo);
    }

}
