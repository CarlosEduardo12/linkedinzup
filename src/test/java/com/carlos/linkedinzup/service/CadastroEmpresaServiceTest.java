package com.carlos.linkedinzup.service;

import com.carlos.linkedinzup.domain.exception.NegocioException;
import com.carlos.linkedinzup.domain.model.Empresa;
import com.carlos.linkedinzup.domain.repository.EmpresaRepository;
import com.carlos.linkedinzup.domain.service.CadastroEmpresaService;
import com.carlos.linkedinzup.domain.service.impl.CadastroEmpresaServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class CadastroEmpresaServiceTest {

    private static final String CNPJ = "01.451.373/0001-41" ;
    private static final String LOCALIDADE = "Belo Horizonte" ;
    private static final String NOME = "Zup I.T. Innovation";

    @MockBean
    protected EmpresaRepository empresaRepository;
    protected CadastroEmpresaService sut;
    protected Empresa empresa;
    protected Empresa empresaB;

    @Before
    public void setUp() {
        sut = new CadastroEmpresaServiceImpl(empresaRepository);

        empresa = new Empresa();
        empresa.setCnpj(CNPJ);
        empresa.setLocalidade(LOCALIDADE);
        empresa.setNome(NOME);

        empresaB = new Empresa();
        empresaB.setCnpj(CNPJ);
        empresaB.setLocalidade(LOCALIDADE);
        empresaB.setNome(NOME);

        when(empresaRepository.findByCnpj(CNPJ)).thenReturn(Optional.empty());

    }
    @Test
    public void deve_cadastrar_empresa_no_repository() {
        sut.salvar(empresa);
        verify(empresaRepository).save(empresa);
    }

    @Test(expected = NegocioException.class)
    public void nao_deve_cadastrar_duas_empresas_com_o_mesmo_cnpj() {
        when(empresaRepository.findByCnpj(CNPJ)).thenReturn(Optional.of(empresa));
        sut.salvar(empresa);
    }

    @Test(expected = NegocioException.class)
    public void nao_deve_editar_um_cnpj_se_existir_outra_empresa_com_mesmo_cnpj() {
        when(empresaRepository.findByCnpj(CNPJ)).thenReturn(Optional.of(empresa));
        empresa.setId((long) 1);
        empresaB.setId((long) 2);
        sut.editar(empresaB);
    }
}
