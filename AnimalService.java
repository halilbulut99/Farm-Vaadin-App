package uni.pu.fmi.st.data.service;


import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vaadin.flow.component.textfield.TextField;

import uni.pu.fmi.st.data.entity.Animal;


@Service
public class AnimalService extends BaseService<Animal>
{

    @Autowired
    public AnimalService(AnimalRepo repository)
    {
        super(repository);
    }




	
}
