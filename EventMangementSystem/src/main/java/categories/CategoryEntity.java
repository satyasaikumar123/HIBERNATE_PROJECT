package categories;


import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.event.EventEntity;

@Entity
public class CategoryEntity {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private int id;
@Column(name="category_Name")
private String name;
@Column(name="category_Price")
private int price;






@Override
public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("CategoryEntity {");
    sb.append("id=").append(id);
    sb.append(", name='").append(name).append('\'');
    sb.append(", price=").append(price);
    
    sb.append('}');
    return sb.toString();
}

public CategoryEntity( String name, int price) {
	super();
	this.name = name;
	this.price = price;
	
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public int getPrice() {
	return price;
}
public void setPrice(int price) {
	this.price = price;
}


}
