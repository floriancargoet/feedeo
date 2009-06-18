package org.feedeo.model.feed;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Type a description for Writer here.
 * This class is used to model both contributors and writers, as
 * specified by the Atom specification.
 * 
 * For RSS feeds, only the name field will usually be filled.
 * 
 * @author Feedeo Team
 *
 */
@Entity
public class Writer {

  private Long id;

 //A human readable name for the Writer
  private String name;
  
  //An email
  private String email;
  
  //A uri, usually links to a homepage for the person.
  private String uri;

  /**
   * @return the object's id in the corresponding table in the database.
   */
  @Id
  public Long getId() {
    return id;
  }

  /**
   * Sets the object's id in the corresponding table in the database. Its
   * visibility, protected, means that only Hibernate will access it.
   * 
   * @param id
   */
  @SuppressWarnings("unused")
  private void setId(Long id) {
    this.id = id;
  }

  /**
   * @return the name
   */
  public String getName() {
    return name;
  }

  /**
   * @param name the name to set
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * @return the email
   */
  public String getEmail() {
    return email;
  }

  /**
   * @param email the email to set
   */
  public void setEmail(String email) {
    this.email = email;
  }

  /**
   * @return the uri
   */
  public String getUri() {
    return uri;
  }

  /**
   * @param uri the uri to set
   */
  public void setUri(String uri) {
    this.uri = uri;
  }
  
  /** 
   * {@inheritDoc}
   * @see java.lang.Object#hashCode()
   */
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((email == null) ? 0 : email.hashCode());
    result = prime * result + ((name == null) ? 0 : name.hashCode());
    result = prime * result + ((uri == null) ? 0 : uri.hashCode());
    return result;
  }

  /**
   * {@inheritDoc}
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
    Writer other = (Writer) obj;
    if (uri == null) {
      if (other.uri != null) {
        return false;
      }
    } else if (!uri.equals(other.uri)) {
      return false;
    }
    if (email == null) {
      if (other.email != null) {
        return false;
      }
    } else if (!email.equals(other.email)) {
      return false;
    }
    if (name == null) {
      if (other.name != null) {
        return false;
      }
    } else if (!name.equals(other.name)) {
      return false;
    }
    return true;
  }

}
