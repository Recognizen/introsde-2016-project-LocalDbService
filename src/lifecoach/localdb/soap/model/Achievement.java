package lifecoach.localdb.soap.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import lifcoach.localdb.soap.dao.LifeCoachDao;

import javax.persistence.OneToOne;

/**
 * The persistent class for the "AchievedGoals" database table.
 * 
 */
@Entity
@Table(name = "AchievedGoals")
@NamedQuery(name = "AchievedGoals.findAll", query = "SELECT l FROM Achievement l")
@XmlType(propOrder = { "achievementId", "goal", "completed", "person" })
@XmlRootElement(name="achievements")
public class Achievement implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator="sqlite_achievedgoal")
	@TableGenerator(name="sqlite_achievedgoal", table="sqlite_sequence",
	    pkColumnName="name", valueColumnName="seq",
	    pkColumnValue="AchievedGoal")
	@Column(name = "id")
	private long achievementId;

	@Temporal(TemporalType.DATE)
	@Column(name = "completed")
	private Date completed;
	
	@OneToOne
	@JoinColumn(name = "idGoal", referencedColumnName = "idGoal")
	private Goal goal;
	
	@OneToOne
	@JoinColumn(name="idPerson",referencedColumnName="idPerson")
	private Person person;

	public Achievement() {
	}

	public long getAchievementId() {
		return this.achievementId;
	}

	public void setAchievementId(long idAchievement) {
		this.achievementId = idAchievement;
	}

	public Date getCompleted() {
		return this.completed;
	}

	public void setCompleted(Date completed) {
		this.completed = completed;
	}
	

	public Goal getGoal() {
		return goal;
	}

	public void setGoal(Goal param) {
		this.goal = param;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}
	
	// Database operations
	// Notice that, for this example, we create and destroy and entityManager on each operation. 
	// How would you change the DAO to not having to create the entity manager every time? 
	public static Achievement getAchievementById(long gid) {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		Achievement g = em.find(Achievement.class, gid);
		LifeCoachDao.instance.closeConnections(em);
		return g;
	}
	
	public static List<Achievement> getAll() {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
	    List<Achievement> list = em.createNamedQuery("Goal.findAll", Achievement.class).getResultList();
	    LifeCoachDao.instance.closeConnections(em);
	    return list;
	}
	
	public static Achievement saveAchievement(Achievement g) {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.persist(g);
		tx.commit();
	    LifeCoachDao.instance.closeConnections(em);
	    return g;
	}
	
	public static Achievement updateAchievement(Achievement g) {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		g=em.merge(g);
		tx.commit();
	    LifeCoachDao.instance.closeConnections(em);
	    return g;
	}
	
	public static void removeAchievement(Achievement g) {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
	    g=em.merge(g);
	    em.remove(g);
	    tx.commit();
	    LifeCoachDao.instance.closeConnections(em);
	}
}