package gestionfoot.restcontroller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import gestionFoot.jsonviews.JsonViews;
import gestionFoot.model.Attaquant;
import gestionFoot.model.Entraineur;
import gestionFoot.model.Equipe;
import gestionFoot.model.Gardien;
import gestionFoot.model.Joueur;
import gestionFoot.model.Pays;
import gestionFoot.service.AttaquantService;
import gestionFoot.service.EntraineurService;
import gestionFoot.service.EquipeService;
import gestionFoot.service.GardienService;

@RestController
@RequestMapping("/api/equipe")
public class EquipeRestController {
	
	
	@Autowired
	private EquipeService EquipeService;
	@Autowired
	private AttaquantService attaquantService;
	
	@Autowired
	private GardienService GardienService;
	
	@Autowired
	private EntraineurService EntaineurService;
	
	
	@GetMapping("")
	public List<Equipe> getAll() {
		return EquipeService.getAll();
	}
//	@GetMapping("")
//	public List<Equipe> getAll() {
//		return EquipeService.getAll();
//	}
	
	@GetMapping("/create")
//	/api/equipe?idGardien=1&idAttaquant=4&idEntraineur=3
	public Equipe creatEquipe(	@RequestParam(name = "idGardien") Integer idGardien,
								@RequestParam(name = "idAttaquant") Integer idAttaquant, 
								@RequestParam(name = "idEntraineur") Integer idEntraineur ) {

		List<Joueur> listeJoueur = new ArrayList();
		
		Gardien j1 = GardienService.getById(idGardien);
		Attaquant j2 = attaquantService.getById(idAttaquant);
		Entraineur j3 = EntaineurService.getById(idEntraineur);
		
		Collections.addAll(listeJoueur,j1,j2);
		
		Equipe equipe1 = new Equipe(Pays.France,"Bleu",3,listeJoueur,j3);
		
		
		return EquipeService.create(equipe1) ;
	}
	
	@PostMapping("")
//	/api/equipe?idGardien=1&idAttaquant=4&idEntraineur=3
	public Equipe creatEquipe(@RequestBody Equipe equipe) {
		return EquipeService.create(equipe) ;
	}
	
	@PostMapping("/{id}/addattaquant")
	@JsonView(JsonViews.EquipeWithJoueur.class)
//	/api/equipe?idGardien=1&idAttaquant=4&idEntraineur=3
	public Equipe creatEquipe(@PathVariable Integer id,@RequestBody Attaquant attaquant) {
		
		Equipe equipe = EquipeService.getById(id);
		attaquant.setEquipe(equipe);
		
		attaquantService.update(attaquant) ;
		
		return EquipeService.getByIdWithJoueur(id);
	}
	
	
	
	

}
