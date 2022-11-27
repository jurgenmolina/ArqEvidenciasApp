package Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import com.app.proyect.Modelo.Rol;
import com.app.proyect.Repositorio.UsuarioRepositorio;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class Test {
	
	@Autowired
	private UsuarioRepositorio usuarioRepository;
	
    @Autowired
    private TestEntityManager entityManager;
    
    
    @org.junit.jupiter.api.Test
    public void testCrearRoles() {
		Rol rolAdmin = new Rol("Director del programa");
		Rol rolProfesor = new Rol("Profesor");
		Rol rolEstudiante = new Rol("Estudiante");
		
		entityManager.persist(rolAdmin);
		entityManager.persist(rolProfesor);
		entityManager.persist(rolEstudiante);
	}

}
