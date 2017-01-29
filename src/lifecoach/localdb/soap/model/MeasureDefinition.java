package lifecoach.localdb.soap.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import lifecoach.localdb.soap.dao.LifeCoachDao;


/**
 * The persistent class for the "MeasureDefinition" database table.
 * 
 */
@Entity
@Table(name="MeasureDefinition")
@NamedQuery(name="MeasureDefinition.findAll", query="SELECT m FROM MeasureDefinition m")
@XmlType(propOrder = { "measureType", "measureValueType" })
@XmlRootElement
public class MeasureDefinition implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator="sqlite_measuredef")
	@TableGenerator(name="sqlite_measuredef", table="sqlite_sequence",
	    pkColumnName="name", valueColumnName="seq",
	    pkColumnValue="MeasureDefinition")
	@Column(name="idMeasureDef")
	private long tid;
	

	@Column(name="measureName")
	private String measureType;

	@Column(name="measureType")
	private String measureValueType;

	public MeasureDefinition() {
	}

	@XmlTransient
	public long getTid() {
		return this.tid;
	}

	public void setTid(long idMeasureDef) {
		this.tid = idMeasureDef;
	}
	
	public String getMeasureType() {
		return this.measureType;
	}

	public void setMeasureType(String measureType) {
		this.measureType = measureType;
	}
	
	public String getMeasureValueType() {
		return this.measureValueType;
	}

	public void setMeasureValueType(String measureValueType) {
		this.measureValueType = measureValueType;
	}
	


	// database operations
	public static MeasureDefinition getMeasureDefinitionById(long tid) {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		MeasureDefinition p = em.find(MeasureDefinition.class, tid);
		LifeCoachDao.instance.closeConnections(em);
		return p;
	}
	
	public static List<MeasureDefinition> getAll() {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
	    List<MeasureDefinition> list = em.createNamedQuery("MeasureDefinition.findAll", MeasureDefinition.class).getResultList();
	    LifeCoachDao.instance.closeConnections(em);
	    return list;
	}
	
	public static MeasureDefinition saveMeasureDefinition(MeasureDefinition p) {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.persist(p);
		tx.commit();
	    LifeCoachDao.instance.closeConnections(em);
	    return p;
	}
	
	public static MeasureDefinition updateMeasureDefinition(MeasureDefinition p) {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		p=em.merge(p);
		tx.commit();
	    LifeCoachDao.instance.closeConnections(em);
	    return p;
	}
	
	public static void removeMeasureDefinition(MeasureDefinition p) {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
	    p=em.merge(p);
	    em.remove(p);
	    tx.commit();
	    LifeCoachDao.instance.closeConnections(em);
	}
	
	public static MeasureDefinition getByName(String name){
		List<MeasureDefinition> defs = getAll();
		MeasureDefinition d = null;
		for(int i = 0; i < defs.size(); i++){
			if(defs.get(i).getMeasureType().equals(name)){
				d = defs.get(i);
			}
		}
		
		return d;
	}
}
