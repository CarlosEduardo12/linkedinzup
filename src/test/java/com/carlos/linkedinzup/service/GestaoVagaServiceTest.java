package com.carlos.linkedinzup.service;

import com.carlos.linkedinzup.domain.exception.NegocioException;
import com.carlos.linkedinzup.domain.model.Empresa;
import com.carlos.linkedinzup.domain.model.Vaga;
import com.carlos.linkedinzup.domain.repository.EmpresaRepository;
import com.carlos.linkedinzup.domain.repository.VagaRepository;
import com.carlos.linkedinzup.domain.service.GestaoVagaService;
import com.carlos.linkedinzup.domain.service.impl.GestaoVagaServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.Optional;
import static org.mockito.Mockito.*;

    @RunWith(SpringRunner.class)
    public class GestaoVagaServiceTest {

        private static final String CARGO = "Programdor Java";
        private static final int VAGAS_DISPONIVEIS = 2;
        private static final int ID_EMPRESA = 1;
        private static final String CNPJ = "01.451.373/0001-41" ;
        private static final String LOCALIDADE = "Belo Horizonte" ;
        private static final String NOME = "Zup I.T. Innovation";
        @MockBean
        protected VagaRepository vagaRepository;
        @MockBean
        protected EmpresaRepository empresaRepository;
        protected GestaoVagaService sut;
        protected Vaga vaga;
        protected Empresa empresa;

        @Before
        public void setUp(){
            sut = new GestaoVagaServiceImpl(vagaRepository, empresaRepository);

            empresa = new Empresa();
            empresa.setId((long) ID_EMPRESA);
            empresa.setCnpj(CNPJ);
            empresa.setLocalidade(LOCALIDADE);
            empresa.setNome(NOME);

            vaga = new Vaga();
            vaga.setCargo(CARGO);
            vaga.setDisponivel(VAGAS_DISPONIVEIS);
            vaga.setEmpresa(empresa);

            when(vagaRepository.findByCargoAndEmpresa(CARGO, empresa.getId())).thenReturn(Optional.empty());
            when(empresaRepository.findById(empresa.getId())).thenReturn(Optional.empty());

        }

        @Test
        public void deve_cadastrar_uma_vaga(){
            when(empresaRepository.findById(empresa.getId())).thenReturn(Optional.of(empresa));
            sut.criar(vaga);
            verify(vagaRepository).save(vaga);
        }

        @Test(expected = NegocioException.class)
        public void nao_deve_cadastrar_vagas_iguais_para_mesma_empresa(){
            when(vagaRepository.findByCargoAndEmpresa(CARGO, empresa.getId())).thenReturn(Optional.of(vaga));
            sut.criar(vaga);
        }

        @Test(expected = NegocioException.class)
        public void deve_verificar_se_empresa_existe(){
            sut.criar(vaga);
        }

        @Test()
        public void deve_excluir_vaga(){
            when(empresaRepository.findById(empresa.getId())).thenReturn(Optional.of(empresa));
            vaga.setId((long) 1);
            sut.excluir(vaga.getId());
            verify(vagaRepository).deleteById(vaga.getId());

        }
    }

