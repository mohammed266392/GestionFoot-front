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

import gestionFoot.exception.ArbitreException;
import gestionFoot.model.Arbitre;
import gestionFoot.service.ArbitreService;


@RestController
@RequestMapping("/api/arbitre")
public class ArbitreRestController {
	
	
	@Autowired
	private ArbitreService arbitreService;
	
	
	@GetMapping("/{id}")
	public Arbitre getById(@PathVariable Integer id) {
		try {
			return arbitreService.getById(id);
		} catch (ArbitreException ex) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
	}
	
	
	@GetMapping("")
	public List<Arbitre> getAll() {
		return arbitreService.getAll();
	}
	
	@PostMapping("")
	public Arbitre create(@RequestBody Arbitre arbitre) {
		return arbitreService.create(arbitre);
	}
	
	@PutMapping("/{id}")
	public Arbitre update(@RequestBody Arbitre arbitre, @PathVariable Integer id) {
		try {
			Arbitre arbitreEnBase = arbitreService.getById(id);
			if (arbitreEnBase != null) {
				arbitre.setId(id);
			}
		} catch (ArbitreException ex) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
		return arbitreService.update(arbitre);
	}
	
	@PatchMapping("/{id}")
    public Arbitre partialUpdateArbitre(@RequestBody Map<String, Object> fields,@PathVariable Integer id ) {
        try {
            Arbitre fourni = arbitreService.getById(id);
            fields.forEach((k, v) -> {

                Field field = ReflectionUtils.findField(Arbitre.class, k);
                ReflectionUtils.makeAccessible(field);
                ReflectionUtils.setField(field, fourni, v); // ne fonctionne que pour les types standards

            });
            return arbitreService.update(fourni);
        }catch(RuntimeException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

    }
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Integer id) {
		arbitreService.deleteById(id);
	}
	
}
