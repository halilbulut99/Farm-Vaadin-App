package uni.pu.fmi.st.views.about;

import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import uni.pu.fmi.st.views.MainLayout;

@PageTitle("За нас")
@Route(value = "about", layout = MainLayout.class)
@AnonymousAllowed
public class AboutView extends VerticalLayout {

    public AboutView() {
        setSpacing(false);

        Image img = new Image("images/fermata.png", "placeholder plant");
        img.setWidth("500px");
        add(img);

        add(new H2("Фермата е създадена през 2015 година в подножието на Стара планина, в село Мировяне."));
        add(new Paragraph("Освен кравите и телетата, отглеждаме и други видове животни като овце, кози, прасета, пуйки, гъски, патки, кокошки, пилета и зайци . 🤗"));

        setSizeFull();
        setJustifyContentMode(JustifyContentMode.CENTER);
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        getStyle().set("text-align", "center");
    }

}
