package org.feedeo.model.user;

import static org.feedeo.hibernate.InitSessionFactory.getSession;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.hibernate.criterion.Restrictions;

import org.feedeo.model.feed.Article;

/**
 * This class models the application's users (people who have subscribed to this
 * service).
 * 
 * @author Feedeo Team
 * 
 */
public class User {

  private Long                            id;

  private String                          login;
  private String                          password;
  private String                          email;

  private String                          firstName;
  private String                          lastName;

  private Folder                          rootDirectory;

  private Map<String, Serializable>       preferences;
  private Map<Article, ArticleProperties> articleProperties;

  /**
   * Default constructor.
   */
  public User() {
    super();
    rootDirectory = new Folder();
    rootDirectory.setOwner(this);
    preferences = new HashMap<String, Serializable>();
    articleProperties = new HashMap<Article, ArticleProperties>();
  }

  /**
   * @param id
   *          the id to set
   */
  @SuppressWarnings("unused")
  private void setId(Long id) {
    this.id = id;
  }

  /**
   * @return the id
   */
  public Long getId() {
    return id;
  }

  /**
   * @return the login
   */
  public String getLogin() {
    return login;
  }

  /**
   * @param login
   *          the login to set
   */
  public void setLogin(String login) {
    this.login = login;
  }

  /**
   * @return the password
   */
  public String getPassword() {
    return password;
  }

  /**
   * @param password
   *          the password to set
   */
  public void setPassword(String password) {
    this.password = password;
  }

  /**
   * @return the email
   */
  public String getEmail() {
    return email;
  }

  /**
   * @param email
   *          the email to set
   */
  public void setEmail(String email) {
    this.email = email;
  }

  /**
   * @return the firstName
   */
  public String getFirstName() {
    return firstName;
  }

  /**
   * @param firstName
   *          the firstName to set
   */
  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  /**
   * @return the lastName
   */
  public String getLastName() {
    return lastName;
  }

  /**
   * @param rootDirectory
   *          the rootDirectory to set
   */
  public void setRootDirectory(Folder rootDirectory) {
    this.rootDirectory = rootDirectory;
  }

  /**
   * @return the lastName
   */
  public Folder getRootDirectory() {
    return rootDirectory;
  }

  /**
   * @param lastName
   *          the lastName to set
   */
  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  /**
   * @return the preferences
   */
  public Map<String, Serializable> getPreferences() {
    return preferences;
  }

  /**
   * @param preferences
   *          the preferences to set
   */
  public void setPreferences(Map<String, Serializable> preferences) {
    this.preferences = preferences;
  }

  /**
   * @return the articleProperties
   */
  public Map<Article, ArticleProperties> getArticleProperties() {
    return articleProperties;
  }

  /**
   * @param articleProperties
   *          the articleProperties to set
   */
  public void setArticleProperties(
      Map<Article, ArticleProperties> articleProperties)
  {
    this.articleProperties = articleProperties;
  }

  /**
   * Gets an article properties for this user.
   * 
   * @param article
   *          the article in question
   * @return the corresponding ArticleProperties object
   */
  public ArticleProperties getArticleProperties(Article article) {
    if (!articleProperties.containsKey(article)) {
      articleProperties.put(article, new ArticleProperties());
    }
    return articleProperties.get(article);
  }

  /**
   * @param article
   *          the article whose map is desired
   * @return an article's map with its parameters.
   */
  public Map<String, Object> articleMap(Article article) {
    Map<String, Object> result = article.toMap(true);
    ArticleProperties currProperties = getArticleProperties(article);
    result.put("read", Boolean.valueOf(currProperties.isAlreadyRead()));
    result.put("important", Boolean.valueOf(currProperties.isImportant()));
    return result;
  }

  /**
   * Gets a User by his login.
   * 
   * @param login
   *          the user's login.
   * @return a User with the corresponding login; if he did not exist in the
   *         base, all fields except login will be empty.
   */
  public static User getUserByLogin(String login) {
    getSession().beginTransaction();
    User potentialUser = (User) getSession().createCriteria(User.class).add(
        Restrictions.eq("login", login)).uniqueResult();
    getSession().getTransaction().commit();
    if (potentialUser == null) {
      return new User();
    } else {
      return potentialUser;
    }
  }
}