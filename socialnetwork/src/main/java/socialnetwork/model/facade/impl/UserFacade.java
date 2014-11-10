package socialnetwork.model.facade.impl;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import socialnetwork.model.facade.UserFacadeLocal;
import socialnetwork.model.facade.UserFacadeRemote;
import socialnetwork.model.dao.bean.User;

@Stateless
public class UserFacade extends AbstractFacade<User> implements
		UserFacadeLocal, UserFacadeRemote {

	@PersistenceContext(unitName = "socialNetwork")
	private EntityManager em;

	public UserFacade() {
		super(User.class);
	}

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	@Override
	public User findUserByNameAndPassword(String login, String password) {
		return em
				.createNamedQuery(User.FIND_USER_BY_NAME_AND_PASSWORD,
						User.class).setParameter(User.USER_LOGIN, login)
				.setParameter(User.USER_PASSWORD, password).getSingleResult();
	}

	@Override
	public User findUserByEmail(String email) {
		return em.createNamedQuery(User.FIND_USER_BY_EMAIL, User.class)
				.setParameter(User.USER_EMAIL, email).getSingleResult();
	}

	@Override
	public void addUser(User user) {
		this.create(user);
	}

}
