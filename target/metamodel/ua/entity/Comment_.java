package ua.entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Comment.class)
public abstract class Comment_ extends ua.entity.AbstractEntity_ {

	public static volatile SingularAttribute<Comment, Date> date;
	public static volatile SingularAttribute<Comment, Boolean> isPositive;
	public static volatile SingularAttribute<Comment, String> text;
	public static volatile SingularAttribute<Comment, User> user;

}

