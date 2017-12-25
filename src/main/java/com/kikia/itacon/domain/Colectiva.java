package com.kikia.itacon.domain;

import javax.persistence.Entity;

import org.apache.commons.lang3.builder.HashCodeBuilder;

@Entity
public class Colectiva extends Contribuinte {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4998319876621339085L;

	private String name;
	private Long NIPC;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getNIPC() {
		return NIPC;
	}

	public void setNIPC(Long NIPC) {
		this.NIPC = NIPC;
	}

	@Override
	public int hashCode() {
		final int prime = 51;
		return new HashCodeBuilder(prime, 17).append(NIPC).toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Individuo))
			return false;
		Colectiva other = (Colectiva) obj;
		if (NIPC == null) {
			if (other.NIPC != null)
				return false;
		} else if (!NIPC.equals(other.NIPC))
			return false;
		return true;
	}
}
