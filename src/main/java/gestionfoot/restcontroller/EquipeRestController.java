package gestionfoot.restcontroller;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.annotation.JsonView;

import gestionFoot.exception.EquipeException;
import gestionFoot.jsonviews.JsonViews;
import gestionFoot.model.Attaquant;
import gestionFoot.model.Entraineur;
import gestionFoot.model.Equipe;
import gestionFoot.model.Match;
import gestionFoot.model.Pays;
import gestionFoot.service.AttaquantService;
import gestionFoot.service.EntraineurService;
import gestionFoot.service.EquipeService;
import gestionFoot.service.GardienService;
import gestionFoot.service.MatchService;

@RestController
@RequestMapping("/api/equipe")
public class EquipeRestController {
	
	
	@Autowired
	private EquipeService equipeService;
	@Autowired
	private AttaquantService attaquantService;
	
	@Autowired
	private GardienService gardienService;
	
	@Autowired
	private EntraineurService entaineurService;
	
	@Autowired
	private MatchService matchService;
	
	
	@GetMapping("/{id}")
	@JsonView(JsonViews.Base.class)
	public Equipe getById(@PathVariable Integer id) {
		try {
			return equipeService.getById(id);
		} catch (EquipeException ex) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("")
	@JsonView(JsonViews.Base.class)
	public List<Equipe> getAll() {
		return equipeService.getAll();
	}

	
	/*@GetMapping("/create")
//	/api/equipe?idGardien=1&idAttaquant=4&idEntraineur=3
	public Equipe creatEquipe(	@RequestParam(name = "idGardien") Integer idGardien,
								@RequestParam(name = "idAttaquant") Integer idAttaquant, 
								@RequestParam(name = "idEntraineur") Integer idEntraineur ) {

		List<Joueur> listeJoueur = new ArrayList();
		
		Gardien j1 = gardienService.getById(idGardien);
		Attaquant j2 = attaquantService.getById(idAttaquant);
		Entraineur j3 = entaineurService.getById(idEntraineur);
		
		Collections.addAll(listeJoueur,j1,j2);
		
		Equipe equipe1 = new Equipe(Pays.France,"Bleu",3,listeJoueur,j3);
		
		
		return equipeService.create(equipe1) ;
	}*/
	
	@PostMapping("")
	@JsonView(JsonViews.Base.class)
	public Equipe create(@RequestBody Equipe equipe) {
		return equipeService.create(equipe);
	}
	
	@PostMapping("/{id}/addattaquant")
	@JsonView(JsonViews.EquipeWithJoueurs.class)
	public Equipe create(@PathVariable Integer id, @RequestBody Attaquant attaquant) {
		
		Equipe equipe = equipeService.getById(id);
		attaquant.setEquipe(equipe);
		
		attaquantService.update(attaquant) ;
		
		return equipeService.getByIdWithJoueurs(id);
	}
	
	@PostMapping("/{id}/matchdom")
	@JsonView(JsonViews.EquipeWithMatchDom.class)
	public Equipe createWithMatchDom(@PathVariable Integer id, @RequestBody Match match) {
		
		Equipe equipe = equipeService.getById(id);
		match.setEquipeDom(equipe);
		
		matchService.update(match) ;
		
		return equipeService.getByIdWithMatchDom(id);
	}
	
	@PostMapping("/{id}/matchext")
	@JsonView(JsonViews.EquipeWithMatchExt.class)
	public Equipe createWithMatchExt(@PathVariable Integer id, @RequestBody Match match) {
		
		Equipe equipe = equipeService.getById(id);
		match.setEquipeExt(equipe);
		
		matchService.update(match) ;
		
		return equipeService.getByIdWithMatchExt(id);
	}
	
	@PutMapping("/{id}")
	@JsonView(JsonViews.Base.class)
	public Equipe update(@PathVariable Integer id, @RequestBody Equipe equipe) {
		
		try {
			Equipe equipeEnBase = equipeService.getById(id);
			if(equipeEnBase != null) {
				equipe.setId(id);
			}
		} catch (EquipeException ex) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
		
		return equipeService.update(equipe);
	}
	
	@PatchMapping("/{id}")
	@JsonView(JsonViews.Base.class)
	public Equipe partialUpdate(@RequestBody Map<String, Object> fields, @PathVariable Integer id) {
		Equipe equipe = null;
		try {
			equipe = equipeService.getById(id);
		} catch (EquipeException ex) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
		
		final Equipe e = equipe;
		Pays pays = e.getPays();
		Entraineur entraineur = e.getEntraineur();
		System.out.println("pays de e : " + pays);
		
		fields.forEach((k,v) -> {
			if (k.equals("pays")) {
				Field fieldPays = ReflectionUtils.findField(Pays.class,k);
				ReflectionUtils.makeAccessible(fieldPays);
				ReflectionUtils.setField(fieldPays,pays,(String)v);
				e.setPays(Pays.valueOf((String)v));
			/*}else if(k.equals("entraineur")) {
				((Map<String, Object>) v).forEach((key, value) -> {
					Field fieldEntraineur = ReflectionUtils.findField(Entraineur.class,key);
					ReflectionUtils.makeAccessible(fieldEntraineur);
					ReflectionUtils.setField(fieldEntraineur,entraineur,value);
				});
				e.setEntraineur(entraineur);*/
			}else {
				Field field = ReflectionUtils.findField(Equipe.class,k);
				ReflectionUtils.makeAccessible(field);
				ReflectionUtils.setField(field, e, v);
			}
		});
		
		return equipeService.update(equipe);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Integer id) {
		equipeService.deleteById(id);
	}
	

}
