package lifecoach.localdb.soap.model;

import java.io.Serializable;
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
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import lifcoach.localdb.soap.dao.LifeCoachDao;

import javax.persistence.OneToOne;

/**
 * The persistent class for the "Goal" database table.
 * 
 */
@Entity
@Table(name = "Goal")
@NamedQuery(name = "Goal.findAll", query = "SELECT l FROM Goal l")
@XmlType(propOrder = { "gid", "value", "measureDefinition" })
@XmlRootElement
public class Goal implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator="sqlite_goal")
	@TableGenerator(name="sqlite_goal", table="sqlite_sequence",
	    pkColumnName="name", valueColumnName="seq",
	    pkColumnValue="Goal")
	@Column(name = "idGoal")
	private long gid;

	@Column(name = "value")
	private String value;
	
	@OneToOne
	@JoinColumn(name = "idMeasureDef", referencedColumnName = "idMeasureDef")
	private MeasureDefinition measureDefinition;

	public Goal() {
	}

	public long getGid() {
		return this.gid;
	}

	public void setGid(long idGoal) {
		this.gid = idGoal;
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	public MeasureDefinition getMeasureDefinition() {
		return measureDefinition;
	}

	public void setMeasureDefinition(MeasureDefinition param) {
		this.measureDefinition = param;
	}
	
	// Database operations
	// Notice that, for this example, we create and destroy and entityManager on each operation. 
	// How would you change the DAO to not having to create the entity manager every time? 
	public static Goal getGoalById(long gid) {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		Goal g = em.find(Goal.class, gid);
		LifeCoachDao.instance.closeConnections(em);
		return g;
	}

	public static List<Goal> getAll() {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
	    List<Goal> list = em.createNamedQuery("Goal.findAll", Goal.class).getResultList();
	    LifeCoachDao.instance.closeConnections(em);
	    return list;
	}
	
	public static Goal saveGoal(Goal g) {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.persist(g);
		tx.commit();
	    LifeCoachDao.instance.closeConnections(em);
	    return g;
	}
	
	public static Goal updateGoal(Goal g) {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		g=em.merge(g);
		tx.commit();
	    LifeCoachDao.instance.closeConnections(em);
	    return g;
	}
	
	public static void removeGoal(Goal g) {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
	    g=em.merge(g);
	    em.remove(g);
	    tx.commit();
	    LifeCoachDao.instance.closeConnections(em);
	}
}