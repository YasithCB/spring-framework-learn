package lk.ijse.spring.controller;

import lk.ijse.spring.dto.ItemDTO;
import lk.ijse.spring.service.ItemService;
import lk.ijse.spring.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/item")
@CrossOrigin
public class ItemController {

    @Autowired
    private ItemService itemService;


    @PostMapping
    public ResponseUtil saveItem(@ModelAttribute ItemDTO dto) {
        itemService.addItem(dto);
        return new ResponseUtil("200", dto.getCode()+" : Successfully Added.!", null);
    }

    @GetMapping
    public ResponseUtil getAllItems() {
        ArrayList<ItemDTO> allItems = itemService.getAllItems();
        return new ResponseUtil("200", "Successfully Loaded.!", allItems);
    }


    @PutMapping
    public ResponseUtil updateItem(@RequestBody ItemDTO dto) {
        itemService.updateItem(dto);
        return new ResponseUtil("200", dto.getCode()+ " : Successfully Updated", null);
    }


    @DeleteMapping(params = "code")
    public ResponseUtil deleteItem(String code) {
        itemService.deleteItem(code);
        return new ResponseUtil("200", code+ " : Successfully Deleted", null);
    }



}
