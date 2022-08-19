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

import gestionFoot.exception.GardienException;
import gestionFoot.model.Gardien;

import gestionFoot.service.GardienService;

@RestController
@RequestMapping("/api/gardien")
public class GardienRestController {
	
	@Autowired
	GardienService GardienService ;
	
	
	@GetMapping("")
	public List<Gardien> getAll() {
		return GardienService.getAll();
	}
	@GetMapping("/{id}")
	public Gardien getById(@PathVariable Integer id) {
		
		try {
			return GardienService.getById(id);
		}catch (GardienException ex) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
	}
	
	
	@PostMapping("")
	public Gardien create(@RequestBody Gardien Gardien) {
		return GardienService.create(Gardien);
	}
	
	@PutMapping("/{id}")
	public Gardien update(@RequestBody Gardien Gardien, @PathVariable Integer id) {
		try {
			Gardien GardienEnBase = GardienService.getById(id);
			if (GardienEnBase != null) {
				Gardien.setId(id);
			}
		} catch (GardienException ex) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
		return GardienService.update(Gardien);
	}
	
	@PatchMapping("/{id}")
    public Gardien partalUpdateGardien(@RequestBody Map<String, Object> fields,@PathVariable Integer id ) {
        try {
            Gardien fourni = GardienService.getById(id);
            fields.forEach((k, v) -> {

                Field field = ReflectionUtils.findField(Gardien.class, k);
                ReflectionUtils.makeAccessible(field);
                ReflectionUtils.setField(field, fourni, v); // ne fonctionne que pour les types standards

            });
            return GardienService.update(fourni);
        }catch(RuntimeException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

    }
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Integer id) {
		GardienService.deleteById(id);
	}

}
