package br.com.icardapio.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@Entity
public class Category implements Serializable {
  
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Long id;
  
  private String name;
  
  @OneToMany(cascade=CascadeType.ALL, orphanRemoval=true, mappedBy="category")
  private List<Product> products;

  public Category() {
    super();
  }
  
  public Category(String name) {
    setName(name);
  }
  
  public Long getId() {
    return this.id;
  }

  public void setId(Long id) {
    this.id = id;
  }   
  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<Product> getProducts() {
    return products;
  }

  public void setProducts(List<Product> products) {
    this.products = products;
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(1, 3)
      .append(name)
      .toHashCode();
  } 

  @Override
  public boolean equals(Object obj) {
    if (obj == null) { return false; }
    if (obj == this) { return true; }
    if (obj.getClass() != getClass()) {
      return false;
    }
     
    Category rhs = (Category) obj;
    return new EqualsBuilder()
      .appendSuper(super.equals(obj))
      .append(name, rhs.getName())
      .isEquals();
  }
  
}
