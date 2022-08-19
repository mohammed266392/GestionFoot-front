package gestionfoot.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gestionFoot.model.Attaquant;
import gestionFoot.repository.AttaquantRepository;


@RestController
@RequestMapping("/api/attaquant")
public class AttaquantRestController {
	
	@Autowired
	AttaquantRepository attaquantRepo;
	
	@GetMapping("")
	public String demo() {
		return "hello toi ? ca va ";
	}
	
//	@GetMapping("")
//	public List<Attaquant> getAll() {
//		return attaquantRepo.findAll();
//	}
	
}
