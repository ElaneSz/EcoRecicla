package ecorecicla.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class UsuarioTest {

    private Cidadao usuario;

    @Before
    public void prepararTeste() {
    	
        usuario = new Cidadao
        		("Gabriela de Oliveira",
        		"52998224725", 
        		"gabi@email.com", 
        		"47999999999", 
        		"gabi", 
        		"123456",
        		"Rua A",
        		"03/11/2005");
    }

    @Test
    public void deveRealizarLoginComCredenciaisCorretas() {
        assertTrue(usuario.login("gabi", "123456"));
    }

    @Test
    public void naoDeveRealizarLoginComUsuarioIncorreto() {
        assertFalse(usuario.login("elane", "123456"));
    }

    @Test
    public void naoDeveRealizarLoginComSenhaIncorreta() {
        assertFalse(usuario.login("gabi", "789012"));
    }

    @Test
    public void deveAtualizarEmailETelefone() {
        usuario.atualizarDados("novo@email.com", "47888888888");

        assertEquals("novo@email.com", usuario.getEmail());
        assertEquals("47888888888", usuario.getTelefone());
    }

    @Test
    public void deveAlterarSenhaQuandoSenhaAtualForCorreta() {
        boolean resultado = usuario.alterarSenha("123456", "novaSenha");

        assertTrue(resultado);
        assertTrue(usuario.login("gabi", "novaSenha"));
        assertFalse(usuario.login("gabi", "123456"));
    }

    @Test
    public void naoDeveAlterarSenhaQuandoSenhaAtualForIncorreta() {
        boolean resultado = usuario.alterarSenha("incorreta", "novaSenha");

        assertFalse(resultado);
        assertTrue(usuario.login("gabi", "123456"));
        assertSame(usuario, usuario.getDados());
    }

    @Test
    public void deveExecutarLogoutSemExcecao() {
        usuario.logout();
    }

    @Test
    public void getDadosDeveRetornarAPropriaInstancia() {
        assertSame(usuario, usuario.getDados());
    }

    @Test
    public void deveAcessarEAlterarNomeCompleto() {
        usuario.setNomeCompleto("Gabriela Oliveira Silva");
        assertEquals("Gabriela Oliveira Silva", usuario.getNomeCompleto());
    }

    @Test
    public void deveAcessarEAlterarCpf() {
        usuario.setCpf("11144477735");
        assertEquals("11144477735", usuario.getCpf());
    }

    @Test
    public void deveAcessarEmail() {
        assertEquals("gabi@email.com", usuario.getEmail());
    }

    @Test
    public void deveAcessarTelefone() {
        assertEquals("47999999999", usuario.getTelefone());
    }

    @Test
    public void deveAcessarEAlterarUsuario() {
        assertEquals("gabi", usuario.getUsuario());
        usuario.setUsuario("gabi.oliveira");
        assertEquals("gabi.oliveira", usuario.getUsuario());
    }

    @Test
    public void deveAcessarSenha() {
        assertEquals("123456", usuario.getSenha());
    }

    @Test
    public void deveAlterarEmail() {
        usuario.setEmail("gabi.nova@email.com");
        assertEquals("gabi.nova@email.com", usuario.getEmail());
    }

    @Test
    public void deveAlterarTelefone() {
        usuario.setTelefone("47977776666");
        assertEquals("47977776666", usuario.getTelefone());
    }

    @Test
    public void deveAlterarSenhaDiretamentePeloSetter() {
        usuario.setSenha("outraSenha");
        assertEquals("outraSenha", usuario.getSenha());
    }
}

