package com.kikia.itacon.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

/**
 * That class represent an institution offered service
 * 
 * @author diambakus
 *
 */
@Entity
@Table(name = "service")
public class OfferedService implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5248273079563923850L;
	private Long id;
	private String name;
	private String code;
	private BigDecimal price;
	private Institution institution;

	public void setId(Long id) {
		this.id = id;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	/*xxx,xxx,xxx.yy*/
	@Column(precision = 11, scale = 3)
	@NumberFormat(style = Style.CURRENCY)
	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "institution_id")
	public Institution getInstitution() {
		return institution;
	}

	public void setInstitution(Institution institution) {
		this.institution = institution;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		return prime * ((id == null) ? 0 : id.hashCode() % 3);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OfferedService other = (OfferedService) obj;

		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}