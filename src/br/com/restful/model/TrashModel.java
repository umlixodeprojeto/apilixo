package br.com.restful.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class TrashModel {
	
	private long id;
	private String name;
	private boolean full;
	
	// ####################################################################
	
	public TrashModel(long id, String name, boolean full) {
		this.id = id;
		this.name = name;
		this.full = full;
	}
	
	public TrashModel(String name, boolean full) {
		this(0, name, full);
	}
	
	public TrashModel(boolean full) {
		this(null, full);
	}
	
	public TrashModel() {}
	
	//#####################################################################
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isFull() {
		return full;
	}
	public void setFull(boolean full) {
		this.full = full;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (full ? 1231 : 1237);
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TrashModel other = (TrashModel) obj;
		if (full != other.full)
			return false;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	
	@Override
	public String toString() {
		return "TrashModel [id=" + id + ", name=" + name + ", full=" + full + "]";
	}
	
}
