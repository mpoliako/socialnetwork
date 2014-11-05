package socialnetwork;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import socialnetwork.model.dao.bean.User;

public class JpaTest {
	private static EntityManagerFactory emf = Persistence
			.createEntityManagerFactory("socialNetwork");
	private EntityManager em;

	@Before
	public void initEntityManager() throws Exception {
		em = emf.createEntityManager();
	}

	@After
	public void closeEntityManager() throws Exception {
		if (em != null)
			em.close();
	}

	@Test
	public void shouldFindUser() throws Exception {
		User user = em.find(User.class, 1L);
		System.out.println(user);
	}
	
	@Test
	public void shouldFindUsers() throws Exception {
		List<User> users = em.createNamedQuery("findMaleUsers", User.class).getResultList();
		System.out.println(users);
	}

}
