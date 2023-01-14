package uni.pu.fmi.st.views.animal.components;

import java.util.ArrayList;
import java.util.List;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.ValidationException;

import uni.pu.fmi.st.data.entity.Animal;
import uni.pu.fmi.st.data.entity.Farm;
import uni.pu.fmi.st.data.entity.Owner;
import uni.pu.fmi.st.data.service.AnimalService;
import uni.pu.fmi.st.data.service.FarmService;

public class AnimalForm extends VerticalLayout
{
	private TextField farmName = new TextField("Ферма");
//	private ComboBox<Farm> farmName = new ComboBox<Farm>("Ferma");
    private TextField name = new TextField("Име");
    private TextField animalId = new TextField("Сериен номер");
    private TextField breed = new TextField("Порода");
    private ComboBox<Animal> mother = new ComboBox<Animal>("Майка");
    private ComboBox<Animal> father = new ComboBox<Animal>("Баща");
    private DatePicker birthDate = new DatePicker("Дата на раждане");
    private boolean saved;
    private final AnimalService animalService;


    private Animal animal;
    private BeanValidationBinder<Animal> binder = new BeanValidationBinder<>(Animal.class);
    private Button cancel;
    private Button save;


    public AnimalForm(Animal animal, AnimalService animalService)
    {

    	this.animalService = animalService;
        this.animal = animal;
        init();
        

    }


    private void init()
    {
        setSizeFull();
        final FormLayout form = new FormLayout();
        form.add(farmName);
        form.add(name);
        form.add(animalId);
        form.add(breed);
        form.add(mother);
        form.add(father);		
        form.add(birthDate);
        mother.setItems(animalService.getAll());
        father.setItems(animalService.getAll());
        father.setItemLabelGenerator(a->a.getName());
        mother.setItemLabelGenerator(Animal::getName);
        binder.bindInstanceFields(this);
        
     
        
        
        binder.readBean(this.animal);
        HorizontalLayout buttons = configureButtons();
        add(form, buttons);
    }


    private HorizontalLayout configureButtons()
    {
        save = new Button("Съхрани", l -> {
            if (binder.isValid())
            {
                try
                {
                    binder.writeBean(animal);
                    saved = true;
                }
                catch (ValidationException e)
                {
                    e.printStackTrace();
                }
            }
        });
        cancel = new Button("Откажи");
        final HorizontalLayout buttons = new HorizontalLayout(save, cancel);
        return buttons;
    }


    public void addCancelClickListener(ComponentEventListener<ClickEvent<Button>> clickListener)
    {
        cancel.addClickListener(clickListener);
    }


    public void addSaveClickListener(ComponentEventListener<ClickEvent<Button>> clickListener)
    {
        save.addClickListener(clickListener);
    }


    public boolean isSaved()
    {
        return saved;
    }
    
}
