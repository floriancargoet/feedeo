package org.feedeo.model.feed;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Category class that traces the functionality of the ROME SyndFeed interface.
 * 
 * @author Feedeo Team
 * 
 */
@Entity
public class Category {
  private String name;
  private String taxonomyUri;
  private Long   id;

  /**
   * Sets the object's id in the corresponding table in the database. Its
   * visibility, private, means that only Hibernate will access it.
   * 
   * @param id
   */
  @SuppressWarnings("unused")
  private void setId(Long id) {
    this.id = id;
  }

  /**
   * @return the object's id in the corresponding table in the database.
   */
  @Id
  public Long getId() {
    return id;
  }

  /**
   * @param name
   *          the name to set
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * @return the name
   */
  public String getName() {
    return name;
  }

  /**
   * @param taxonomyUri
   *          the taxonomyUri to set
   */
  public void setTaxonomyUri(String taxonomyUri) {
    this.taxonomyUri = taxonomyUri;
  }

  /**
   * @return the taxonomyUri
   */
  public String getTaxonomyUri() {
    return taxonomyUri;
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.lang.Object#hashCode()
   */
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((name == null) ? 0 : name.hashCode());
    result = prime * result
        + ((taxonomyUri == null) ? 0 : taxonomyUri.hashCode());
    return result;
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.lang.Object#equals(java.lang.Object)
   */
  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    Category other = (Category) obj;
    if (name == null) {
      if (other.name != null) {
        return false;
      }
    } else if (!name.equals(other.name)) {
      return false;
    }
    if (taxonomyUri == null) {
      if (other.taxonomyUri != null) {
        return false;
      }
    } else if (!taxonomyUri.equals(other.taxonomyUri)) {
      return false;
    }
    return true;
  }
}
