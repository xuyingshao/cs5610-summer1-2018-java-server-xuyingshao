package com.example.CourseManager.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Widget {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
//	private String name;
//	private int displayOrder;  
	private String text;
	private String widgetType;
//	private String style;
//	private String width;
//	private String height;
//	private String size;          // Heading
//	private String href;          // Link
//	private String src;           // Image
//	private String listItems;     // List
////	private String listType;    // List
//	private ListType listType;    // List
	@ManyToOne
	@JsonIgnore
	private Lesson lesson;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
//	public String getName() {
//		return name;
//	}
//	public void setName(String name) {
//		this.name = name;
//	}
//	public int getDisplayOrder() {
//		return displayOrder;
//	}
//	public void setDisplayOrder(int displayOrder) {
//		this.displayOrder = displayOrder;
//	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getWidgetType() {
		return widgetType;
	}
	public void setWidgetType(String widgetType) {
		this.widgetType = widgetType;
	}
//	public String getStyle() {
//		return style;
//	}
//	public void setStyle(String style) {
//		this.style = style;
//	}
//	public String getWidth() {
//		return width;
//	}
//	public void setWidth(String width) {
//		this.width = width;
//	}
//	public String getHeight() {
//		return height;
//	}
//	public void setHeight(String height) {
//		this.height = height;
//	}
//	public String getSize() {
//		return size;
//	}
//	public void setSize(String size) {
//		this.size = size;
//	}
//	public String getHref() {
//		return href;
//	}
//	public void setHref(String href) {
//		this.href = href;
//	}
//	public String getSrc() {
//		return src;
//	}
//	public void setSrc(String src) {
//		this.src = src;
//	}
//	public String getListItems() {
//		return listItems;
//	}
//	public void setListItems(String listItems) {
//		this.listItems = listItems;
//	}
//	public ListType getListType() {
//		return listType;
//	}
//	public void setListType(ListType listType) {
//		this.listType = listType;
//	}
//	public Lesson getLesson() {
//		return lesson;
//	}
//	public void setLesson(Lesson lesson) {
//		this.lesson = lesson;
//	}
}
