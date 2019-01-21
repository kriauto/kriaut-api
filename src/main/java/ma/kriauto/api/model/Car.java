package ma.kriauto.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "api_car")
public class Car {
	
	  @Id
	  private Long id;
	  
	  @Column(name="mark")
	  private String mark;
	  
	  @Column(name="model")
	  private String model;
	  
	  @Column(name="immatriculation")
	  private String immatriculation;
	  
	  @Column(name="htmlcolor")
	  private String htmlColor;
	  
	  @Column(name="idagency")
	  private Long idAgency;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getImmatriculation() {
		return immatriculation;
	}

	public void setImmatriculation(String immatriculation) {
		this.immatriculation = immatriculation;
	}

	public String getHtmlColor() {
		return htmlColor;
	}

	public void setHtmlColor(String htmlColor) {
		this.htmlColor = htmlColor;
	}

	public Long getIdAgency() {
		return idAgency;
	}

	public void setIdAgency(Long idAgency) {
		this.idAgency = idAgency;
	}

	@Override
	public String toString() {
		return "Car [id=" + id + ", mark=" + mark + ", model=" + model
				+ ", immatriculation=" + immatriculation + ", htmlColor="
				+ htmlColor + ", idAgency=" + idAgency + "]";
	}
}
