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

import gestionFoot.exception.DefenseurException;
import gestionFoot.model.Defenseur;
import gestionFoot.service.DefenseurService;

@RestController
@RequestMapping("/api/defenseur")
public class DefenseurRestController {
	
	@Autowired
	private DefenseurService DefenseurService;
	
	
	@GetMapping("")
	public List<Defenseur> getAll() {
		return DefenseurService.getAll();
	}
	@GetMapping("/{id}")
	public Defenseur getById(@PathVariable Integer id) {
		
		try {
			return DefenseurService.getById(id);
		}catch (DefenseurException ex) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
	}
	
	
	@PostMapping("")
	public Defenseur create(@RequestBody Defenseur Defenseur) {
		return DefenseurService.create(Defenseur);
	}
	
	@PutMapping("/{id}")
	public Defenseur update(@RequestBody Defenseur Defenseur, @PathVariable Integer id) {
		try {
			Defenseur DefenseurEnBase = DefenseurService.getById(id);
			if (DefenseurEnBase != null) {
				Defenseur.setId(id);
			}
		} catch (DefenseurException ex) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
		return DefenseurService.update(Defenseur);
	}
	
	@PatchMapping("/{id}")
    public Defenseur partalUpdateDefenseur(@RequestBody Map<String, Object> fields,@PathVariable Integer id ) {
        try {
            Defenseur fourni = DefenseurService.getById(id);
            fields.forEach((k, v) -> {

                Field field = ReflectionUtils.findField(Defenseur.class, k);
                ReflectionUtils.makeAccessible(field);
                ReflectionUtils.setField(field, fourni, v); // ne fonctionne que pour les types standards

            });
            return DefenseurService.update(fourni);
        }catch(RuntimeException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

    }
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Integer id) {
		DefenseurService.deleteById(id);
	}
}
