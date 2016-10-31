package dds.softpoi;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class Persistible implements Serializable{

	private static final long serialVersionUID = -2309789506901394713L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	protected long id;
	
	protected Persistible(){}
	
	@Column(name = "id")
	public long getId(){
		return id;
	}
	
	public void setId(long id){
		this.id = id;
	}
}
