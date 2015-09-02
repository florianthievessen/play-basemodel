package models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

import play.db.ebean.Model;

/**
 * BaseModel for Play Models. Adds attributes for id, as well as createdAt and
 * updatedAt timestamps that will be set automatically on save and update
 * actions.
 */
@MappedSuperclass
public class BaseModel extends Model {

	private static final long serialVersionUID = -9214804074533865792L;

	/**
	 * Numeric field that represents the databases primary key.
	 */
	@Id
	public Long id;

	/**
	 * Date field that tracks when a model got created.
	 */
	@Column(name = "created_at")
	public Date createdAt;

	/**
	 * Date field that tracks when a model got updated.
	 */
	@Column(name = "updated_at")
	public Date updatedAt;

	/**
	 * See JPA documentation.
	 */
	@Version
	public int version;

	/*
	 * (non-Javadoc)
	 * 
	 * @see play.db.ebean.Model#save()
	 */
	@Override
	public void save() {
		createdAt();
		super.save();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see play.db.ebean.Model#update()
	 */
	@Override
	public void update() {
		updatedAt();
		super.update();
	}

	/**
	 * Sets the createdAt and updatedAt values
	 */
	private void createdAt() {
		this.createdAt = this.updatedAt = new Date();
	}

	/**
	 * Sets the updatedAt value
	 */
	private void updatedAt() {
		this.updatedAt = new Date();
	}

	/**
	 * Set the version property. We declare version protected here, because no
	 * one is supposed to change the version value.
	 * 
	 * @param version
	 */
	protected void setVersion(int version) {
		this.version = version;
	}

}
