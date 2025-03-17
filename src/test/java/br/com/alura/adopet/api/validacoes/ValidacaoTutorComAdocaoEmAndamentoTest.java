package br.com.alura.adopet.api.validacoes;

import br.com.alura.adopet.api.dto.CadastroAbrigoDto;
import br.com.alura.adopet.api.dto.CadastroPetDto;
import br.com.alura.adopet.api.dto.CadastroTutorDto;
import br.com.alura.adopet.api.dto.SolicitacaoAdocaoDto;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.model.*;
import br.com.alura.adopet.api.repository.AdocaoRepository;
import br.com.alura.adopet.api.repository.TutorRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class ValidacaoTutorComAdocaoEmAndamentoTest {

    @InjectMocks
    private ValidacaoTutorComAdocaoEmAndamento validacaoTutorComAdocaoEmAndamento;

    @Mock
    private AdocaoRepository adocaoRepository;

    @Mock
    private TutorRepository tutorRepository;

    @Mock
    private SolicitacaoAdocaoDto dto;

    @Mock
    private Tutor tutor;

    private Tutor tutor1 = new Tutor( new CadastroTutorDto( "Tutor 1", "123456789", ""));

    @Test
    void validarTutorComAdocaoEmAndamento() {

        // ARRANGE

        List<Adocao> adocoes = listarAdocoes();

        given(adocaoRepository.findAll()).willReturn(adocoes);
        given(tutorRepository.getReferenceById(dto.idTutor())).willReturn(tutor1);

        // ACT
        // ASSERT

        Assertions.assertThrows(ValidacaoException.class, () -> validacaoTutorComAdocaoEmAndamento.validar(dto));

    }

    @Test
    void validarTutorComAdocaoEmAndamentoNaoLancaExcecao() {

        // ARRANGE

        List<Adocao> adocoes = listarAdocoes();
        adocoes.forEach(elem -> elem.marcarComoAprovada());


        given(adocaoRepository.findAll()).willReturn(adocoes);
        given(tutorRepository.getReferenceById(dto.idTutor())).willReturn(tutor1);

        // ACT
        // ASSERT

        Assertions.assertDoesNotThrow(() -> validacaoTutorComAdocaoEmAndamento.validar(dto));

    }

    private List<Adocao> listarAdocoes(){
        Tutor tutor2 = new Tutor( new CadastroTutorDto( "Tutor 2", "123456789", ""));

        Abrigo abrigo = new Abrigo(new CadastroAbrigoDto(
                "Abrigo do Seu Zé",
                "Seu Zé",
                "11999999999"
        ));

        Pet pet1 = new Pet(new CadastroPetDto(
                TipoPet.CACHORRO,
                "Rex",
                "Pastor Alemão",
                4,
                "Preto",
                20.0f
        ), abrigo);

        Pet pet2 = new Pet(new CadastroPetDto(
                TipoPet.GATO,
                "Lila",
                "Vira lata",
                4,
                "Preto",
                20.0f
        ), abrigo);

        Pet pet3 = new Pet(new CadastroPetDto(
                TipoPet.CACHORRO,
                "Rex 2",
                "Pastor Alemão",
                4,
                "Preto",
                20.0f
        ), abrigo);

        Pet pet4 = new Pet(new CadastroPetDto(
                TipoPet.CACHORRO,
                "Rex 3",
                "Pastor Alemão",
                4,
                "Preto",
                20.0f
        ), abrigo);


        Adocao adocao1 = new Adocao(tutor1, pet1, "teste");
        Adocao adocao2 = new Adocao(tutor1, pet2, "teste");
        Adocao adocao3 = new Adocao(tutor1, pet3, "teste");
        Adocao adocao4 = new Adocao(tutor2, pet4, "teste");

        List<Adocao> adocoes = List.of(adocao1, adocao2, adocao3, adocao4);

        return adocoes;

    }
}
