package com.example.CourseManager.services;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.CourseManager.models.Lesson;
import com.example.CourseManager.models.Widget;
import com.example.CourseManager.repositories.LessonRepository;
import com.example.CourseManager.repositories.WidgetRepository;

@RestController
@CrossOrigin(origins="*", maxAge=3600)
public class WidgetService {
	@Autowired
	WidgetRepository widgetRepository;
	@Autowired
	LessonRepository lessonRepository;
	
	// "/api/course/{courseId}/module/{moduleId}/lesson/{lessonId}/module"
	@GetMapping("/api/lesson/{lessonId}/widget")
	public List<Widget> findAllWidgetsForLesson(
			@PathVariable("lessonId") int lessonId,
			HttpServletResponse response) {
		Optional<Lesson> data = lessonRepository.findById(lessonId);
		if (data.isPresent()) {
			Lesson lesson = data.get();
			return lesson.getWidgets();
		}
		response.setStatus(HttpServletResponse.SC_CONFLICT);
		return null;
	}
	
	@PostMapping("/api/lesson/{lessonId}/widget")
	public Widget createWidget(@PathVariable("lessonId") int lessonId,
			@RequestBody Widget widget) {
		Optional<Lesson> data = lessonRepository.findById(lessonId);
		if (data.isPresent()) {
			Lesson lesson = data.get();
			widget.setLesson(lesson);
			return widgetRepository.save(widget);
		}
		return null;
	}
	
	@GetMapping("/api/widget")
	public List<Widget> findAllWidgets(HttpServletResponse response) {
		List<Widget> widgets = (List<Widget>) widgetRepository.findAll();
		if (widgets.isEmpty()) {
			response.setStatus(HttpServletResponse.SC_CONFLICT);
		}
		return widgets;
	}
	
	@GetMapping("/api/widget/{widgetId}")
	public Widget findWidgetById(@PathVariable("widgetId") int widgetId) {
		Optional<Widget> data = widgetRepository.findById(widgetId);
		if (data.isPresent()) {
			return data.get();
		}
		return null;
	}
	
	// "/api/course/{courseId}/module/{moduleId}/lesson/{lessonId}/module/save"
	@PostMapping("/api/lesson/{lessonId}/widget/save")
	public void saveWidgetsForLesson(
			@PathVariable("lessonId") int lessonId,
			@RequestBody List<Widget> widgets) {
		Optional<Lesson> data = lessonRepository.findById(lessonId);
		if (data.isPresent()) {
			Lesson lesson = data.get();
			
			for (Widget widget : widgetRepository.findAll()) {
				if (widget.getLesson() != null && 
						widget.getLesson().getId() == lessonId) {
					widgetRepository.delete(widget);
				}
			}
			
			for (Widget widget : widgets) {
				widget.setLesson(lesson);
				widgetRepository.save(widget);
			}
		}
	}
	
	@PostMapping("/api/widget/save")
	public void save(@RequestBody List<Widget> widgets) {
		widgetRepository.deleteAll();
		for (Widget widget : widgets) {
			widgetRepository.save(widget);
		}
	}
	
	@PutMapping("/api/widget/{widgetId}")
	public Widget updateWidget(@PathVariable("widgetId") int widgetId,
			@RequestBody Widget newWidget) {
		Optional<Widget> data = widgetRepository.findById(widgetId);
		if (data.isPresent()) {
				Widget widget = data.get();
				if (newWidget.getName() != null) {
					widget.setName(newWidget.getName());
				}
				if (newWidget.getDisplayOrder() != 0) {
					widget.setDisplayOrder(newWidget.getDisplayOrder());
				}
				if (newWidget.getText() != null) {
					widget.setText(newWidget.getText());
				}
				if (newWidget.getWidgetType() != null) {
					widget.setWidgetType(newWidget.getWidgetType());
				}
				if (newWidget.getStyle() != null) {
					widget.setStyle(newWidget.getStyle());
				}
				if (newWidget.getWidth() != null) {
					widget.setWidth(newWidget.getWidth());
				}
				if (newWidget.getHeight() != null) {
					widget.setHeight(newWidget.getHeight());
				}
				if (newWidget.getSize() != null) {
					widget.setSize(newWidget.getSize());
				}
				if (newWidget.getHref() != null) {
					widget.setHref(newWidget.getHref());
				}
				if (newWidget.getSrc() != null) {
					widget.setSrc(newWidget.getSrc());
				}
				if (newWidget.getListItems() != null) {
					widget.setListItems(newWidget.getListItems());
				}
				if (newWidget.getListType() != null) {
					widget.setListType(newWidget.getListType());
				}
				widgetRepository.save(widget);
		}
		return null;
	}
	
	@DeleteMapping("/api/widget/{widgetId}")
	public void deleteWidget(@PathVariable("widgetId") int widgetId) {
		widgetRepository.deleteById(widgetId);
	}
}
