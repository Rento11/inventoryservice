package com.example.inventoryservice.web;

import com.example.inventoryservice.dao.entities.Creator;
import com.example.inventoryservice.service.CreatorManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class CreatorController {

    @Autowired
    CreatorManager creatorManager;

    @GetMapping("/creatorsList")
    public String getCreators(Model model) {
        List<Creator> creators = creatorManager.getAllCreator();
        model.addAttribute("creators", creators);
        return "creatorsList";
    }

    @GetMapping("/addCreator")
    public String addCreatorGet(Model model) {
        return "addCreator";
    }

    @PostMapping("/addCreator")
    public String addCreatorPost(Model model, @RequestParam(name = "name") String name, @RequestParam(name = "email") String email) {
        Creator creator = new Creator();
        creator.setName(name);
        creator.setEmail(email);
        creatorManager.addCreator(creator);
        return getCreators(model);
    }

    @GetMapping("/detailsCreator")
    public String getDetailsCreator(Model model, @RequestParam(name = "id") Integer id) {
        model.addAttribute("creator", creatorManager.findCreatorById(id));
        return "detailsCreator";
    }

    @GetMapping("/updateCreator")
    public String modifyCreator(Model model, @RequestParam(name = "id") Integer id) {
        model.addAttribute("creator", creatorManager.findCreatorById(id));
        return "updateCreator";
    }

    @PostMapping("/updateCreator")
    public String modifyCreatorPost(Model model, @RequestParam(name = "id") Integer id, @RequestParam(name = "name") String name, @RequestParam(name = "email") String email) {
        Creator creator = creatorManager.findCreatorById(id);
        creator.setName(name);
        creator.setEmail(email);
        creatorManager.updateCreator(creator);
        return getCreators(model);
    }

    @GetMapping("/deleteCreator")
    public String deleteCreator(Model model, @RequestParam(name="id") Integer id){
        Creator creator = creatorManager.findCreatorById(id);
        creatorManager.deleteCreator(creator);
        return getCreators(model);
    }
}