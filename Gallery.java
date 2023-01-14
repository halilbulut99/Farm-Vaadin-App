package uni.pu.fmi.st.views.images;

import com.vaadin.flow.component.HasComponents;
import com.vaadin.flow.component.HasStyle;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Main;
import com.vaadin.flow.component.html.OrderedList;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.FlexComponent.JustifyContentMode;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.flow.theme.lumo.LumoUtility.AlignItems;
import com.vaadin.flow.theme.lumo.LumoUtility.Display;
import com.vaadin.flow.theme.lumo.LumoUtility.FontSize;
import com.vaadin.flow.theme.lumo.LumoUtility.Gap;
import com.vaadin.flow.theme.lumo.LumoUtility.JustifyContent;
import com.vaadin.flow.theme.lumo.LumoUtility.ListStyleType;
import com.vaadin.flow.theme.lumo.LumoUtility.Margin;
import com.vaadin.flow.theme.lumo.LumoUtility.MaxWidth;
import com.vaadin.flow.theme.lumo.LumoUtility.Padding;
import com.vaadin.flow.theme.lumo.LumoUtility.TextColor;

import uni.pu.fmi.st.views.MainLayout;
import uni.pu.fmi.st.views.imagelist.ImageListViewCard;

@PageTitle("Галерия")
@Route(value = "images", layout = MainLayout.class)
@AnonymousAllowed
public class Gallery  extends  Main implements HasComponents, HasStyle {

    private OrderedList imageContainer;

    public Gallery() {
        constructUI();

        imageContainer.add(new ImageListViewCard("Snow mountains under stars",
                "images/768x432.png"));
        imageContainer.add(new ImageListViewCard("Snow covered mountain",
                "images/chiken.png"));
        imageContainer.add(new ImageListViewCard("River between mountains",
                "images/chiken2.png"));
        imageContainer.add(new ImageListViewCard("Milky way on mountains",
                "images/dog1.png"));
        imageContainer.add(new ImageListViewCard("Mountain with fog",
                "images/farm1.png"));
        imageContainer.add(new ImageListViewCard("River between mountains",
                "images/farm2.png"));
        imageContainer.add(new ImageListViewCard("Milky way on mountains",
                "images/farm3.png"));
        imageContainer.add(new ImageListViewCard("Mountain with fog",
                "images/goat.png"));
        imageContainer.add(new ImageListViewCard("River between mountains",
                "images/goat1.png"));
        imageContainer.add(new ImageListViewCard("Milky way on mountains",
                "images/horse.png"));
        imageContainer.add(new ImageListViewCard("Mountain with fog",
                "images/horse1.png"));
        imageContainer.add(new ImageListViewCard("Mountain with fog",
                "images/rabit.png"));
            }

    private void constructUI() {
        addClassNames("image-list-view", MaxWidth.SCREEN_LARGE, Margin.Horizontal.AUTO, Padding.Bottom.LARGE,
                Padding.Horizontal.LARGE);

        HorizontalLayout container = new HorizontalLayout();
        container.addClassNames(AlignItems.CENTER, JustifyContent.BETWEEN);

        VerticalLayout headerContainer = new VerticalLayout();
        Paragraph description = new Paragraph("Royalty free photos and pictures, courtesy of Unsplash");
        description.addClassNames(Margin.Bottom.XLARGE, Margin.Top.NONE, TextColor.SECONDARY);
        headerContainer.add( description);

      

        imageContainer = new OrderedList();
        imageContainer.addClassNames(Gap.MEDIUM, Display.GRID, ListStyleType.NONE, Margin.NONE, Padding.NONE);

        add(container, imageContainer);

    }
}