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

import gestionFoot.exception.AttaquantException;
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
	public Attaquant create(@RequestBody Attaquant attaquant) {
		return attaquantService.create(attaquant);
	}
	
	@PutMapping("/{id}")
	public Attaquant update(@RequestBody Attaquant attaquant, @PathVariable Integer id) {
		try {
			Attaquant attaquantEnBase = attaquantService.getById(id);
			if (attaquantEnBase != null) {
				attaquant.setId(id);
			}
		} catch (AttaquantException ex) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
		return attaquantService.update(attaquant);
	}
	
	@PatchMapping("/{id}")
    public Attaquant partalUpdateAttaquant(@RequestBody Map<String, Object> fields,@PathVariable Integer id ) {
        try {
            Attaquant fourni = attaquantService.getById(id);
            fields.forEach((k, v) -> {

                Field field = ReflectionUtils.findField(Attaquant.class, k);
                ReflectionUtils.makeAccessible(field);
                ReflectionUtils.setField(field, fourni, v); // ne fonctionne que pour les types standards

            });
            return attaquantService.update(fourni);
        }catch(RuntimeException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

    }
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Integer id) {
		attaquantService.deleteById(id);
	}
	
}
