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
import gestionFoot.model.Milieu;
import gestionFoot.service.MilieuService;


@RestController
@RequestMapping("/api/milieu")
public class MilieuRestController {
	
	
	@Autowired
	private MilieuService milieuService;
	
	
	@GetMapping("")
	public List<Milieu> getAll() {
		return milieuService.getAll();
	}
	
	@PostMapping("")
	public Milieu create(@RequestBody Milieu attaquant) {
		return milieuService.create(attaquant);
	}
	
	@PutMapping("/{id}")
	public Milieu update(@RequestBody Milieu attaquant, @PathVariable Integer id) {
		try {
			Milieu milieuEnBase = milieuService.getById(id);
			if (milieuEnBase != null) {
				attaquant.setId(id);
			}
		} catch (AttaquantException ex) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
		return milieuService.update(attaquant);
	}
	
	@PatchMapping("/{id}")
    public Milieu partalUpdateAttaquant(@RequestBody Map<String, Object> fields,@PathVariable Integer id ) {
        try {
            Milieu fourni = milieuService.getById(id);
            fields.forEach((k, v) -> {

                Field field = ReflectionUtils.findField(Milieu.class, k);
                ReflectionUtils.makeAccessible(field);
                ReflectionUtils.setField(field, fourni, v); // ne fonctionne que pour les types standards

            });
            return milieuService.update(fourni);
        }catch(RuntimeException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

    }
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Integer id) {
		milieuService.deleteById(id);
	}
	
}