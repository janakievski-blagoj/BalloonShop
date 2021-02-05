package mk.finki.ukim.wp.balloonShop.web.controller;

import mk.finki.ukim.wp.balloonShop.model.Balloon;
import mk.finki.ukim.wp.balloonShop.model.Manufacturer;
import mk.finki.ukim.wp.balloonShop.model.enums.TYPE;
import mk.finki.ukim.wp.balloonShop.model.exceptions.BalloonAlreadyExistsException;
import mk.finki.ukim.wp.balloonShop.model.exceptions.ManufacturerNotFoundException;
import mk.finki.ukim.wp.balloonShop.service.BalloonService;
import mk.finki.ukim.wp.balloonShop.service.ManufacturerService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/balloons")
public class BalloonController {

    private final BalloonService balloonService;
    private final ManufacturerService manufacturerService;

    public BalloonController(BalloonService balloonService, ManufacturerService manufacturerService) {
        this.balloonService = balloonService;
        this.manufacturerService = manufacturerService;
    }

    @GetMapping
    public String getBalloonsPage(@RequestParam(required = false) String error, Model model){

        if (error != null && !error.isEmpty()){
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }

        model.addAttribute("balloons", this.balloonService.listAll());
        model.addAttribute("bodyContent", "listBalloons");
        return "master-template";

    }

    @GetMapping("/add-form")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String getAddBalloonPage(Model model){
        List<Manufacturer> manufacturers = manufacturerService.findAll();
        model.addAttribute("manufacturers", manufacturers);
        model.addAttribute("typeOval", TYPE.OVAL);
        model.addAttribute("typeHeart", TYPE.HEART);
        model.addAttribute("typeMoon", TYPE.MOON);
        model.addAttribute("typeDog", TYPE.DOG);
        model.addAttribute("update", false);
        model.addAttribute("bodyContent", "add-balloon");
        return "master-template";
    }


    @GetMapping("/edit-form/{id}")
    public String getEditBalloonPage(@PathVariable Long id, Model model){

        try {
            if (balloonService.findBalloonById(id).isPresent()){
                Balloon b = balloonService.findBalloonById(id).get();
                model.addAttribute("balloon", b);
                model.addAttribute("manufacturers", manufacturerService.findAll());
                model.addAttribute("typeOval", TYPE.OVAL);
                model.addAttribute("typeHeart", TYPE.HEART);
                model.addAttribute("typeMoon", TYPE.MOON);
                model.addAttribute("typeDog", TYPE.DOG);
                model.addAttribute("update", true);
                model.addAttribute("bodyContent", "add-balloon");
            }
        }catch (RuntimeException exception){
            return "redirect:/balloons?error="+exception.getMessage();
        }
        return "master-template";
    }

    @PostMapping("/add")
    public String saveBalloon(@RequestParam String name,
                              @RequestParam String description,
                              @RequestParam Long manufacturer,
                              @RequestParam TYPE balloonType){

        try{
            balloonService.SaveOrUpdate(name, description, manufacturer, balloonType);
            return "redirect:/balloons";
        }
        catch (ManufacturerNotFoundException | BalloonAlreadyExistsException exception){
            return "redirect:/balloons?error="+exception.getMessage();
        }
    }


    @DeleteMapping("/delete/{id}")
    public String deleteBalloon(@PathVariable Long id){
        balloonService.deleteBalloon(id);
        return "redirect:/balloons";
    }

}
