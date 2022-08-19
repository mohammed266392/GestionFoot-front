package gestionfoot.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gestionFoot.model.Attaquant;
import gestionFoot.service.AttaquantService;


@RestController
@RequestMapping("/api/attaquant")
public class AttaquantRestController {
	
	
	@Autowired
	private AttaquantService attaquantService;
	
	
	@GetMapping("")
	public List<Attaquant> getAll() {
		return attaquantService.getAll();
	}
	
	@PostMapping("")
	public Attaquant create(Attaquant attaquant) {
		return attaquantService.create(attaquant);
	}
	
}
