package lk.ijse.spring.service.impl;

import lk.ijse.spring.dto.ItemDTO;
import lk.ijse.spring.entity.Item;
import lk.ijse.spring.repo.ItemRepo;
import lk.ijse.spring.service.ItemService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.ArrayList;

@Service
@Transactional
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemRepo repo;

    @Autowired
    private ModelMapper mapper;

    @Override
    public void addItem(ItemDTO dto){
        if (repo.existsById(dto.getCode())) {
            throw new RuntimeException("Item "+dto.getCode()+" Already Exist..!");
        }
        repo.save(mapper.map(dto, Item.class));
    }

    @Override
    public void updateItem(ItemDTO dto){
        if (!repo.existsById(dto.getCode())){
            throw new RuntimeException("Item "+dto.getCode()+" Not Available to Update..!");
        }
        repo.save(mapper.map(dto, Item.class));
    }

    @Override
    public void deleteItem(String code){
        if (!repo.existsById(code)){
            throw new RuntimeException("Item "+code+" Not Available to Delete..!");
        }
        repo.deleteById(code);
    }

    @Override
    public ArrayList<ItemDTO> getAllItems(){
        return mapper.map(repo.findAll(),new TypeToken<ArrayList<ItemDTO>>(){}.getType());
    }
}
