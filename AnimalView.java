package uni.pu.fmi.st.views.animal;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.flow.spring.data.VaadinSpringDataHelpers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;

import uni.pu.fmi.st.data.entity.Animal;
import uni.pu.fmi.st.data.entity.Farm;
import uni.pu.fmi.st.data.service.AnimalService;
import uni.pu.fmi.st.data.service.FarmService;
import uni.pu.fmi.st.data.service.UserService;
import uni.pu.fmi.st.views.MainLayout;
import uni.pu.fmi.st.views.animal.components.AnimalForm;
import uni.pu.fmi.st.views.farm.components.FarmForm;
import uni.pu.fmi.st.views.farm.components.FarmGrid;

import java.util.ArrayList;


@PageTitle("Животни")
@AnonymousAllowed
@Route(value = "animals", layout = MainLayout.class)
public class AnimalView extends VerticalLayout
{

    private AnimalService animalService;
    private Button addButton;
    private Button editButton;
    private Button removeButton;
    private Grid<Animal> grid;
    private FarmGrid farmGrid;
    private Button addFarm;
	//private final FarmService farmService;
	//private final UserService userService;

    @Autowired
    public AnimalView(AnimalService animalService)
    {
        this.animalService = animalService;
        init();
    }


    private void init()
    {
        add(new Label("Животни"));
        addAnimalButtons();
        addAnimalGrid();
        setSizeFull();
    }


    private void addAnimalGrid()
    {
        grid = new Grid<>(Animal.class, false);
        grid.addColumn(Animal::getFarmName).setHeader("Ферма").setSortable(true).setFrozen(true).setResizable(true);
        grid.addColumn(Animal::getName).setHeader("Име").setSortable(true).setFrozen(true).setResizable(true);
        grid.addColumn(Animal::getAnimalId).setHeader("Сериен номер").setSortable(true);
        grid.addColumn(Animal::getBreed).setHeader("Порода").setSortable(true);
        grid.addColumn(Animal::getMother).setHeader("Майка").setSortable(true);
        grid.addColumn(Animal::getFather).setHeader("Баща").setSortable(true);
        grid.addColumn(Animal::getBirthDate).setHeader("Дата на раждане").setSortable(true);

        refreshGridData();
        grid.asSingleSelect().addValueChangeListener(l -> {
            final Animal value = l.getValue();
            editButton.setEnabled(value != null);
            removeButton.setEnabled(value != null);
        });
        add(grid);
    }


    private void refreshGridData()
    {
        grid.setItems(query -> animalService.list(
                        PageRequest.of(query.getPage(), query.getPageSize(), VaadinSpringDataHelpers.toSpringDataSort(query)))
                                           .stream());
    }


    private void addAnimalButtons()
    {
        addButton = new Button("Добави", l -> {
            final Animal animal = new Animal();
            openAnimalForm(animal);
        });
        addButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        editButton = new Button("Промени", l -> {
            final Animal animal = grid.asSingleSelect().getValue();
            openAnimalForm(animal);
        });
        editButton.setEnabled(false);
        editButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        removeButton = new Button("Изтрий", l -> {
            final Dialog dialog = new Dialog();
            final Button yes = new Button("Да", removeL -> {
                animalService.delete(grid.asSingleSelect().getValue().getId());
                dialog.close();
                refreshGridData();
            });
            final Button no = new Button("Не", removeL -> dialog.close());
            final HorizontalLayout removeDialogButtons = new HorizontalLayout(yes, no);
            dialog.setWidth("350px");
            dialog.setHeight("200px");
            dialog.add(new VerticalLayout(new H3("Ама наистина ли ще го триеш?"), removeDialogButtons));
            dialog.open();
        });
        removeButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        removeButton.setEnabled(false);
        final HorizontalLayout buttons = new HorizontalLayout(addButton, editButton, removeButton);
        add(buttons);
    }


    private void openAnimalForm(Animal animal)
    {
        final Dialog dialog = new Dialog();
        final AnimalForm animalForm = new  AnimalForm(animal,animalService);
        animalForm.addCancelClickListener(closeL -> dialog.close());
        animalForm.addSaveClickListener(saveL -> {
            if (animalForm.isSaved())
            {
                animalService.update(animal);
                dialog.close();
                refreshGridData();
            }
        });
        dialog.add(animalForm);
        dialog.setHeight("450px");
        dialog.setWidth("300px");
        dialog.setCloseOnEsc(true);
        dialog.setCloseOnOutsideClick(true);
        dialog.open();
    }
}
