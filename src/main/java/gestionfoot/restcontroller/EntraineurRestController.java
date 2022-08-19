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

import gestionFoot.exception.EntraineurException;
import gestionFoot.model.Entraineur;
import gestionFoot.service.EntraineurService;


@RestController
@RequestMapping("/api/entraineur")
public class EntraineurRestController {
	
	
	@Autowired
	private EntraineurService entraineurService;
	
	
	@GetMapping("/{id}")
	public Entraineur getById(@PathVariable Integer id) {
		try {
			return entraineurService.getById(id);
		} catch (EntraineurException ex) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("")
	public List<Entraineur> getAll() {
		return entraineurService.getAll();
	}
	
	@PostMapping("")
	public Entraineur create(@RequestBody Entraineur entraineur) {
		return entraineurService.create(entraineur);
	}
	
	@PutMapping("/{id}")
	public Entraineur update(@RequestBody Entraineur entraineur, @PathVariable Integer id) {
		try {
			Entraineur entraineurEnBase = entraineurService.getById(id);
			if (entraineurEnBase != null) {
				entraineur.setId(id);
			}
		} catch (EntraineurException ex) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
		return entraineurService.update(entraineur);
	}
	
	@PatchMapping("/{id}")
    public Entraineur partialUpdateEntraineur(@RequestBody Map<String, Object> fields,@PathVariable Integer id ) {
        try {
            Entraineur fourni = entraineurService.getById(id);
            fields.forEach((k, v) -> {

                Field field = ReflectionUtils.findField(Entraineur.class, k);
                ReflectionUtils.makeAccessible(field);
                ReflectionUtils.setField(field, fourni, v); // ne fonctionne que pour les types standards

            });
            return entraineurService.update(fourni);
        }catch(RuntimeException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

    }
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Integer id) {
		entraineurService.deleteById(id);
	}
	
}
