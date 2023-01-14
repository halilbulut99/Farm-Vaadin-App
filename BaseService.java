package uni.pu.fmi.st.data.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.vaadin.flow.component.combobox.ComboBox;

import uni.pu.fmi.st.data.entity.Animal;
import uni.pu.fmi.st.data.entity.SamplePerson;
import uni.pu.fmi.st.views.animal.components.AnimalForm;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


public abstract class BaseService<T>
{
    private final JpaRepository<T, UUID> repository;


    public BaseService(JpaRepository<T, UUID> repository)
    {
        this.repository = repository;
    }


    public Optional<T> get(UUID id)
    {
        return repository.findById(id);
    }


    public T update(T entity)
    {
        return repository.save(entity);
    }


    public void delete(UUID id)
    {
        repository.deleteById(id);
    }


    public Page<T> list(Pageable pageable)
    {
        return repository.findAll(pageable);
    }
    
    public List<T> getAll(){
    	return repository.findAll();
    }
    
    
  //  public List<T> getAll1(ComboBox<Animal> father){
    //	return repository.findAll();
    //}
    public int count()
    {
        return (int)repository.count();
    }

    JpaRepository<T, UUID> getRepo(){
        return repository;
    }
}
