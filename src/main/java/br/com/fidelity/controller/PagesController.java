package br.com.fidelity.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.fidelity.dto.AssociadosDTO;
import br.com.fidelity.dto.CadastroDTO;
import br.com.fidelity.entity.Associacao;
import br.com.fidelity.entity.Cliente;
import br.com.fidelity.entity.Estabelecimento;
import br.com.fidelity.entity.Membro;
import br.com.fidelity.repository.AssociacaoRepository;
import br.com.fidelity.repository.ClienteRepository;
import br.com.fidelity.repository.EstabelecimentoRepository;
import br.com.fidelity.repository.MembroRepository;
import br.com.fidelity.repository.PontoRepository;
import br.com.fidelity.utils.TimeUtils;

@Controller
public class PagesController {

    @Autowired
    MembroRepository membroRepository;

    @Autowired
    EstabelecimentoRepository estabelecimentoRepository;

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    AssociacaoRepository associacaoRepository;

    @Autowired
    PontoRepository pontoRepository;

    TimeUtils timeUtils = new TimeUtils();

    Map<Integer, String> tipoEstab = new HashMap<Integer, String>();
    Map<Integer, String> categorias = new HashMap<Integer, String>();

    public PagesController() {
        categorias.put(1, "Bar");
        categorias.put(2, "Barbearia");
        categorias.put(3, "Cabeleireiro");
        categorias.put(4, "Farmácia");
        categorias.put(5, "Lanchonete");
        categorias.put(6, "Lava-Rápido");
        categorias.put(7, "Livraria");
        categorias.put(8, "Manicure");
        categorias.put(9, "Marcenaria");
        categorias.put(10, "Pizzaria");
        categorias.put(11, "Restaurante");

        tipoEstab.put(1, "10 marcações");
        tipoEstab.put(2, "15 marcações");
        tipoEstab.put(3, "20 marcações");
        tipoEstab.put(4, "150 reais");
        tipoEstab.put(5, "200 reais");
        tipoEstab.put(6, "300 reais");
    }

    @Autowired
    private PasswordEncoder passwordEncoder;

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping(value = "/cadastro")
    public String cadastro(Model model) {
        model.addAttribute("cadastro", new CadastroDTO());
        return "cadastro";
    }

    @GetMapping(value = "/cadastroAdm")
    public String cadastroAdm(Model model) {
        model.addAttribute("cadastro", new CadastroDTO());
        model.addAttribute("categorias", categorias);
        model.addAttribute("tipoEstab", tipoEstab);
        return "cadastroAdm";
    }

    @PostMapping(value = "/cadastro")
    public String cadastroPost(@Valid CadastroDTO cadastroDTO, BindingResult result) {
        if (result.hasErrors()) {
            return "cadastro";
        } else {
            Membro membro = new Membro();
            membro.setUsuario(cadastroDTO.getUsuario());
            membro.setSenha(passwordEncoder.encode(cadastroDTO.getSenha()));
            membro.setHabilitado(true);
            membro.setTipo(cadastroDTO.getTipo());

            if (membro.getTipo().equals("ROLE_ADMIN")) {
                Estabelecimento estab = new Estabelecimento();
                estab.setUsuario(membro);
                estab.setValidade(20);
                estab.setNome(cadastroDTO.getNome());
                estab.setDiasFuncionamento(cadastroDTO.getDiasFuncionamento());
                estab.setHorarioFuncionamento(cadastroDTO.getHorarioFuncionamento());
                estab.setTelefone(cadastroDTO.getTelefone());
                estab.setEndereco(cadastroDTO.getEndereco());
                estab.setBairro(cadastroDTO.getBairro());
                estab.setCidade(cadastroDTO.getCidade());
                estab.setUf(cadastroDTO.getUf());
                estab.setCategoria(cadastroDTO.getCategoria());
                estab.setTipoEstab(cadastroDTO.getTipoEstab());
                estab.setValidadeCartao(cadastroDTO.getValidadeCartao());
                membroRepository.save(membro);
                estabelecimentoRepository.save(estab);

            } else {
                Cliente cli = new Cliente();
                cli.setUsuario(membro);
                cli.setTelefone(cadastroDTO.getTelefone());
                membroRepository.save(membro);
                clienteRepository.save(cli);
            }

            cadastroDTO = null;

            return "index";
        }
    }

    @RequestMapping(value = "/home")
    public String homePage(Model model, Authentication authentication) {
        model.addAttribute("user", authentication.getName());
        return "admin/homePage";
    }

    @RequestMapping(value = "/assoc")
    public String assocPage(Model model, Authentication authentication) {
        Estabelecimento estab = estabelecimentoRepository.findByUsuario(membroRepository.findByUsuario(authentication.getName()));
        /*
         * Ponto ponto1 = new Ponto(); ponto1.setIdAssoc(associacao);
         * ponto1.setData(timeUtils.sdfDate.format(timeUtils.getTime()));
         * ponto1.setHora(timeUtils.sdfTime.format(timeUtils.getTime())); ponto1.setValor(0);
         * 
         * Ponto ponto2 = new Ponto(); ponto2.setIdAssoc(associacao);
         * ponto2.setData(timeUtils.sdfDate.format(timeUtils.getTime()));
         * ponto2.setHora(timeUtils.sdfTime.format(timeUtils.getTime())); ponto2.setValor(0);
         * 
         * pontoRepository.save(ponto1); pontoRepository.save(ponto2);
         */
        List<Associacao> associados = associacaoRepository.findAllByUsuarioEstab(estab);
        List<AssociadosDTO> dto = new ArrayList<>();
        for (Associacao associado : associados) {
            AssociadosDTO dt = new AssociadosDTO();
            dt.setCliente(associado.getUsuarioCli().getUsuario().getUsuario());
            dt.setPontos(pontoRepository.findAllByIdAssoc(associado).size());
            dt.setTotal(tipoEstab.get(associado.getTipoEstab()));
            dto.add(dt);
            dt = null;
        }

        model.addAttribute("associados", dto);

        return "admin/assocPage";
    }

    @RequestMapping(value = "/info")
    public String infoPage(Model model, Authentication authentication) {
        Estabelecimento estab = estabelecimentoRepository.findByUsuario(membroRepository.findByUsuario(authentication.getName()));

        model.addAttribute("validade", estab.getValidade());
        model.addAttribute("associados", associacaoRepository.findAllByUsuarioEstab(estab).size());
        model.addAttribute("categoria", categorias.get(estab.getCategoria()));
        model.addAttribute("marcacoes", tipoEstab.get(estab.getTipoEstab()));
        model.addAttribute("validadeCartao", estab.getValidadeCartao());
        return "admin/infoPage";
    }

    @GetMapping("/user")
    public String user(Model model, Authentication authentication) {
        model.addAttribute("user", authentication.getName());
        return "user/userPage";
    }

    @GetMapping("/connect")
    public String connectPage(Model model, Authentication authentication) {

        Iterable<Estabelecimento> iterable = estabelecimentoRepository.findAll();
        List<Estabelecimento> estabs = StreamSupport.stream(iterable.spliterator(), false).collect(Collectors.toList());
        Cliente cliente = clienteRepository.findByUsuario(membroRepository.findByUsuario(authentication.getName()));
        List<Estabelecimento> removeList = associacaoRepository.listEstabs(cliente);
        estabs.removeAll(removeList);

        model.addAttribute("estabs", estabs);

        return "user/connectPage";
    }

    @PostMapping("/connect")
    public String connectPagePost(Model model, Authentication authentication,
                    @RequestParam(value = "estabId", required = true) String estabId) {
        Associacao associacao = new Associacao();
        associacao.setUsuarioCli(clienteRepository.findByUsuario(membroRepository.findByUsuario(authentication.getName())));
        Estabelecimento estab = estabelecimentoRepository.findByUsuario(membroRepository.findByUsuario(estabId));
        associacao.setUsuarioEstab(estab);
        associacao.setTipoEstab(estab.getTipoEstab());
        associacao.setData(timeUtils.sdfDate.format(timeUtils.getTime()));

        associacaoRepository.save(associacao);
        return connectPage(model, authentication);
    }

    @GetMapping("/checkin")
    public String checkinPage(Model model) {
        return "user/checkinPage";
    }

    @GetMapping("/rule")
    public String rulePage(Model model, @RequestParam(value = "estabId", required = true) String estabId) {
        model.addAttribute("estabId", estabId);
        model.addAttribute("nome",estabelecimentoRepository.findByUsuario(membroRepository.findByUsuario(estabId)).getNome());
        return "user/rulePage";
    }

    @GetMapping("/score")
    public String scorePage(Model model, Authentication authentication) {
        List<Associacao> associados = associacaoRepository
                        .findAllByUsuarioCli(clienteRepository.findByUsuario(membroRepository.findByUsuario(authentication.getName())));
        List<AssociadosDTO> dto = new ArrayList<>();
        for (Associacao associado : associados) {
            AssociadosDTO dt = new AssociadosDTO();
            dt.setCliente(associado.getUsuarioEstab().getNome());
            dt.setPontos(pontoRepository.findAllByIdAssoc(associado).size());
            dt.setTotal(tipoEstab.get(associado.getTipoEstab()));
            dto.add(dt);
            dt = null;
        }
        
        model.addAttribute("associados", dto);
        return "user/scorePage";
    }

    @GetMapping("/checkout")
    public String checkoutPage(Model model) {
        return "user/checkoutPage";
    }
}
