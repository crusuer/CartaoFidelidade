package br.com.fidelity.controller;

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

import br.com.fidelity.dto.CadastroDTO;
import br.com.fidelity.entity.Estabelecimento;
import br.com.fidelity.entity.Membro;
import br.com.fidelity.repository.EstabelecimentoRepository;
import br.com.fidelity.repository.MembroRepository;
import br.com.fidelity.utils.TimeUtils;

@Controller
public class PagesController {

    @Autowired
    MembroRepository membroRepository;

    @Autowired
    EstabelecimentoRepository estabelecimentoRepository;
    
    TimeUtils timeUtils = new TimeUtils();

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
            
            if(membro.getTipo().equals("ROLE_ADMIN")) {
            	Estabelecimento estab = new Estabelecimento();
            	estab.setUsuario(membro);
            	estab.setTelefone(cadastroDTO.getTelefone());
            	estab.setEndereco(cadastroDTO.getEndereco());
            	estab.setBairro(cadastroDTO.getBairro());
            	estab.setCidade(cadastroDTO.getCidade());
            	estab.setUf(cadastroDTO.getUf());
            	estab.setCategoria(cadastroDTO.getCategoria());
            	estab.setTipoEstab(cadastroDTO.getTipoEstab());
            	estab.setValidade(cadastroDTO.getValidade());
            	membroRepository.save(membro);
            	estabelecimentoRepository.save(estab);
            	
            } else {
            	membroRepository.save(membro);
            }
            
            cadastroDTO = null;

            return "index";
        }
    }

    @RequestMapping(value = "/home")
    public String homePage(Model model, Authentication authentication) {
    	model.addAttribute("user",authentication.getName());
        return "admin/homePage";
    }
    
    @RequestMapping(value = "/assoc")
    public String assocPage(Model model, Authentication authentication) {
        return "admin/assocPage";
    }
    
    @RequestMapping(value = "/info")
    public String infoPage(Model model, Authentication authentication) {
        return "admin/infoPage";
    }

    @GetMapping("/user")
    public String user(Authentication authentication, Model model) {
        return "user";
    }
}
