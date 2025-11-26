package Arthur.Unitario.Unitario;

import static org.junit.jupiter.api.Assertions.*;
import entity.Cliente;
import exceptions.ClienteInvalidoException;
import mock.ClienteMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

//ARTHUR VELHO CRISTIANO - REF 01 - RT01 CASO 01

@Nested
class RF01_RT02_TestArthur {
    private Cliente clienteTeste;
    private ClienteMock mock;

    @BeforeEach

    public void iniciarCliente() {
        mock = new ClienteMock();

        clienteTeste = Cliente.createUser(
                "Andrei Effiting",
                "11122233344",
                "47999999999",
                0
        );

        mock.salvar(clienteTeste);
    }

    @Test
    // Teste RT02 - Caso de teste 01 - Alterar informações fornecidas de um usuário
    public void testCS01() {

        String CPF_ORIGINAL = "11122233344";
        String nomeTeste = "Andrei Vinicios Effting";
        String cpfTesteNovo = "22211133344";
        String telTeste = "47989116927";
        int pontos = this.clienteTeste.getPontos();

        Cliente clienteTesteNovo = null;

        try {
            clienteTesteNovo = Cliente.createUser(nomeTeste, cpfTesteNovo, telTeste, pontos);
        } catch (ClienteInvalidoException e) {
            fail("Não deveria lançar exceção ao criar cliente atualizado: " + e.getMessage());
        }

        Cliente clienteRetornado = this.mock.update(clienteTesteNovo, CPF_ORIGINAL);

        assertNotNull(clienteRetornado);
        assertEquals(nomeTeste.toUpperCase(), clienteRetornado.getNome(), "O nome não foi alterado.");
        assertEquals(telTeste, clienteRetornado.getTelefone(), "O telefone não foi alterado.");
        assertEquals(cpfTesteNovo, clienteRetornado.getCpf(), "O CPF não foi alterado.");

        Cliente clienteNoMock = mock.consultarPeloCpf(cpfTesteNovo);
        assertNotNull(clienteNoMock, "O cliente atualizado não foi encontrado.");
    }


    @Test

    //Teste RT02 - Caso de teste 02 - Deve alterar informação de nome de um usuário

    public void testCS02() {

        String cpf = this.clienteTeste.getCpf();
        String nomeNovo = "Andrei Vinicios Effting";
        String tel = this.clienteTeste.getTelefone();
        int pontos = this.clienteTeste.getPontos();

        Cliente clienteTesteNovo = null;

        try {
            clienteTesteNovo = Cliente.createUser(nomeNovo, cpf, tel, pontos);
        } catch (ClienteInvalidoException e) {
            fail("Não deveria lançar exceção ao criar cliente atualizado: " + e.getMessage());
        }

        Cliente clienteRetornado = this.mock.update(clienteTesteNovo, cpf);

        assertNotNull(clienteRetornado);
        assertEquals(nomeNovo.toUpperCase(), clienteRetornado.getNome(), "O nome não foi alterado.");

        Cliente clienteNoMock = mock.consultarPeloCpf(cpf);
        assertNotNull(clienteNoMock, "O cliente atualizado não foi encontrado.");
    }


    @Test

    // Teste RT02 - Caso de teste 03 - Deve alterar informação de telefone de um usuário

    public void testCS03() {
        String cpf = this.clienteTeste.getCpf();
        String nome = this.clienteTeste.getNome();
        String telNovo = "11111111111";
        int pontos = this.clienteTeste.getPontos();

        Cliente clienteTesteNovo = null;

        try {
            clienteTesteNovo = Cliente.createUser(nome, cpf, telNovo, pontos);
        } catch (ClienteInvalidoException e) {
            fail("Não deveria lançar exceção ao criar cliente atualizado: " + e.getMessage());
        }

        Cliente clienteRetornado = this.mock.update(clienteTesteNovo, cpf);

        assertNotNull(clienteRetornado);
        assertEquals(telNovo, clienteRetornado.getTelefone(), "O telefone não foi alterado.");

        Cliente clienteNoMock = mock.consultarPeloCpf(cpf);
        assertNotNull(clienteNoMock, "O cliente atualizado não foi encontrado.");
    }

    @Test

    // Teste RT02 - Caso de teste 04 - Deve alterar informação de pontos de um usuário

    public void testCS04() {
        String cpf = this.clienteTeste.getCpf();
        String nome = this.clienteTeste.getNome();
        String tel = this.clienteTeste.getTelefone();
        int pontosNovo = 500;

        Cliente clienteTesteNovo = null;

        try {
            clienteTesteNovo = Cliente.createUser(nome, cpf, tel, pontosNovo);
        } catch (ClienteInvalidoException e) {
            fail("Não deveria lançar exceção ao criar cliente atualizado: " + e.getMessage());
        }

        Cliente clienteRetornado = this.mock.update(clienteTesteNovo, cpf);

        assertNotNull(clienteRetornado);
        assertEquals(pontosNovo, clienteRetornado.getPontos(), "Os pontos não foi alterado.");

        Cliente clienteNoMock = mock.consultarPeloCpf(cpf);
        assertNotNull(clienteNoMock, "O cliente atualizado não foi encontrado.");
    }


    @Test

    // Teste RT02 - Caso de teste 05 - Validar informação de alteração do nome inválida

    public void testCS05() {
        String cpf = this.clienteTeste.getCpf();
        String nomeInvalido = "";
        String tel = this.clienteTeste.getTelefone();
        int pontos = this.clienteTeste.getPontos();

        Cliente clienteTesteNovo = null;

        try {
            clienteTesteNovo = Cliente.createUser(nomeInvalido, cpf, tel, pontos);
        } catch (ClienteInvalidoException e) {
            fail("Não deveria lançar exceção ao criar cliente atualizado: " + e.getMessage());
        }

        Cliente clienteRetornado = this.mock.update(clienteTesteNovo, cpf);

        assertNotNull(clienteRetornado);
        assertEquals(nomeInvalido, clienteRetornado.getNome(), "Nome é null");

        Cliente clienteNoMock = mock.consultarPeloCpf(cpf);
        assertNotNull(clienteNoMock, "O cliente atualizado não foi encontrado.");
    }


    @Test

    // Teste RT02 - Caso de teste 06 - Validar informação de alteração do telefone inválida

    public void testCS06() {
        String cpf = this.clienteTeste.getCpf();
        String nome = this.clienteTeste.getNome();
        String telInvalido = "4799544";
        int pontos = this.clienteTeste.getPontos();

        Cliente clienteTesteNovo = null;

        try {
            clienteTesteNovo = Cliente.createUser(nome, cpf, telInvalido, pontos);
        } catch (ClienteInvalidoException e) {
            fail("Não deveria lançar exceção ao criar cliente atualizado: " + e.getMessage());
        }

        Cliente clienteRetornado = this.mock.update(clienteTesteNovo, cpf);

        assertNotNull(clienteRetornado);
        assertEquals(telInvalido, clienteRetornado.getTelefone(), "Telefone é null");

        Cliente clienteNoMock = mock.consultarPeloCpf(cpf);
        assertNotNull(clienteNoMock, "O cliente atualizado não foi encontrado.");
    }

    @Test

    // Teste RT02 - Caso de teste 06 - Validar informação de alteração do ponto acumulado inválida

    public void testCS07() {
        String cpf = this.clienteTeste.getCpf();
        String nome = this.clienteTeste.getNome();
        String tel = this.clienteTeste.getTelefone();
        int pontosInvalido = -5;

        Cliente clienteTesteNovo = null;

        try {
            clienteTesteNovo = Cliente.createUser(nome, cpf, tel, pontosInvalido);
        } catch (ClienteInvalidoException e) {
            fail("Não deveria lançar exceção ao criar cliente atualizado: " + e.getMessage());
        }

        Cliente clienteRetornado = this.mock.update(clienteTesteNovo, cpf);

        assertNotNull(clienteRetornado);
        assertEquals(pontosInvalido, clienteRetornado.getPontos(), "Telefone é null");

        Cliente clienteNoMock = mock.consultarPeloCpf(cpf);
        assertNotNull(clienteNoMock, "O cliente atualizado não foi encontrado.");
    }
}

