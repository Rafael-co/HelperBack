package org.soulcodeacademy.helpr.services;

import org.soulcodeacademy.helpr.domain.*;
import org.soulcodeacademy.helpr.domain.enums.Perfil;
import org.soulcodeacademy.helpr.domain.enums.StatusChamado;
import org.soulcodeacademy.helpr.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

// Torna o objeto de PopulateService disponível para toda a aplicação (global)
@Service // indica para o Spring que esta classe será gerenciada por ele
public class PopulateService {
    @Autowired // injetar o objeto direto na classe
    private CargoRepository cargoRepository;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ChamadoRepository chamadoRepository;

    @Autowired
    private PasswordEncoder encoder;


    public void populate() {
        // Integer idCargo, String nome, String descricao, Double salario
        Cargo c1 = new Cargo(null, "Diretor Geral", "Gerencia a empresa", 30000.0);
        Cargo c2 = new Cargo(null, "Diretor de Setor", "Gerencia um setor da empresa", 18000.0);
        Cargo c3 = new Cargo(null, "Técnico geral", "Resolve os chamados urgentes", 12000.0);
        Cargo c4 = new Cargo(null, "Coordenador(a)", "Responsável pelas áreas operacionais", 22000.0);
        Cargo c5 = new Cargo(null, "Coordenador de segurança", "Responsável pela segurança interna da empresa", 10000.0);


        // Integer id, String nome, String email, String cpf, String senha, String foto, Cargo cargo
        Funcionario f1 = new Funcionario(null, "Renato Pereira", "renato.pereira@gmail.com", "68258098144", encoder.encode("12345"), null, c1);
        f1.setPerfil(Perfil.ADMIN);
        Funcionario f2 = new Funcionario(null, "Victor Icoma", "victor.icoma@gmail.com", "51127383671", encoder.encode("12345"), null, c2);
        f2.setPerfil(Perfil.ADMIN);
        Funcionario f3 = new Funcionario(null, "Bart Simpson", "bart.simpson@gmail.com", "90642726035", encoder.encode("4321"), null, c3);
        Funcionario f4 = new Funcionario(null, "Lisa Simpson", "lisa.simpson@gmail.com", "95069763091", encoder.encode("1357"), null, c4);
        f4.setPerfil(Perfil.ADMIN);
        Funcionario f5 = new Funcionario(null, "Homer Simpson", "homer.simpson@gmail.com", "22422861024", encoder.encode("2468"), null, c5);
        Funcionario f6 = new Funcionario(null, "Montgomery Burns", "burns@gmail.com", "41604797070", encoder.encode("223344"), null, c5);
        f6.setPerfil(Perfil.ADMIN);
        Funcionario f7 = new Funcionario(null, "Ned Flanders", "flanders@gmail.com", "49756869020", encoder.encode("112233"), null, c5);
        f7.setPerfil(Perfil.ADMIN);
        Funcionario f8 = new Funcionario(null, "Milhouse Van Houten", "milhouse@gmail.com", "13559757002", encoder.encode("334455"), null, c3);
        Funcionario f9 = new Funcionario(null, "Krusty Clown", "krusty@gmail.com", "21835378099", encoder.encode("445566"), null, c3);
        f9.setPerfil(Perfil.ADMIN);
        Funcionario f10 = new Funcionario(null, "Nelson Muntz", "nelson@gmail.com", "62768735000", encoder.encode("556677"), null, c3);


        // Integer id, String nome, String email, String cpf, String senha, String telefone
        Cliente cl1 = new Cliente(null, "José Almir", "jose.almir@gmail.com", "12659185115", encoder.encode("batata"), "9999999999");
        Cliente cl2 = new Cliente(null, "Pedro João", "pedro@gmail.com", "37734168302", encoder.encode("batata"), "9999999997");
        Cliente cl3 = new Cliente(null, "Maggie Simpson", "maggie@gmail.com", "89209685008", encoder.encode("chupeta"), "999999999100");
        Cliente cl4 = new Cliente(null, "Marge Simpson", "marge@gmail.com", "04011840050", encoder.encode("cabelo azul"), "9999999910");


        Chamado ch1 = new Chamado(null, "Primeiro chamado do sistema", "Revisar as entidades criadas");
        ch1.setCliente(cl1);



        Chamado ch2 = new Chamado(null, "Ativar VPN do sistema", "Conectar aos servidores remotos");
        ch2.setCliente(cl2);
        ch2.setFuncionario(f1);
        ch2.setStatus(StatusChamado.ATRIBUIDO);

        Chamado ch3 = new Chamado(null, "Permissão ao responsável de setor", "Responsáveis de setor não estão conseguindo acesso.");
        ch3.setCliente(cl3);


        Chamado ch4 = new Chamado(null, "Cadastro não finaliza", "Cadastro de Clientes não finaliza.");
        ch4.setCliente(cl3);
        ch4.setFuncionario(f3);
        ch4.setStatus(StatusChamado.CONCLUIDO);
        ch4.setDataFechamento(LocalDate.now());
        ch4.setStatus(StatusChamado.ARQUIVADO);


        Chamado ch5 = new Chamado(null, "Clientes sem nenhum acesso", "Os clientes estão sem acesso nenhum.");
        ch5.setCliente(cl4);


        Chamado ch6 = new Chamado(null, "Senha bloqueada", "O colaborador está com a senha bloqueada");
        ch6.setCliente(cl2);

        Chamado ch7 = new Chamado(null, "Acesso restrito", "Administrador com problemas de acesso.");
        ch7.setCliente(cl4);
        ch7.setFuncionario(f4);
        ch7.setStatus(StatusChamado.CONCLUIDO);
        ch7.setDataFechamento(LocalDate.now());

        // vamos persistir as entidades = salvar no banco
        this.cargoRepository.saveAll(List.of(c1, c2, c3, c4, c5));
        this.funcionarioRepository.saveAll(List.of(f1, f2, f3, f4, f5, f6, f7, f8, f9, f10));
        this.clienteRepository.saveAll(List.of(cl1, cl2, cl3, cl4));
        this.chamadoRepository.saveAll(List.of(ch1, ch2, ch3, ch4, ch5, ch6, ch7));

    }
}

// O objetivo desta classe é inserir no banco, dados fictícios (de teste)
// IOC = Inversion of Control = Inversão de Controle = É ele quem manda nas instâncias
// Container = é o local onde o Spring guarda os objetos anotados
// @Service = indica que a classe é um serviço
// Injeção de Dependências = quando o Spring injeta os objetos na classe