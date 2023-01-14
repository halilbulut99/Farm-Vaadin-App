package uni.pu.fmi.st.data.entity;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;


import java.time.LocalDate;


@Entity
@Data

public class Animal extends AbstractEntity
{
	
    
    private String farmName;
    private LocalDate birthDate;
    private String name;
    @NotBlank(message = "Въведете идентификатор на животното!")
    private String animalId;
    private String breed; 
    @ManyToOne
    private Animal father;
    @ManyToOne
    private Animal mother;
      
}
