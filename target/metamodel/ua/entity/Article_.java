package ua.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Article.class)
public abstract class Article_ extends ua.entity.AbstractEntity_ {

	public static volatile SingularAttribute<Article, String> date;
	public static volatile SingularAttribute<Article, String> photoUrl;
	public static volatile ListAttribute<Article, Comment> comments;
	public static volatile SingularAttribute<Article, String> text;
	public static volatile SingularAttribute<Article, String> title;
	public static volatile SingularAttribute<Article, Integer> version;

}

