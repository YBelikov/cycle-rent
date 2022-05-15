package com.belikov.valteris.cycle.admin;

import com.belikov.valteris.cycle.FileUtils;
import com.belikov.valteris.cycle.bicycle.BicycleService;
import com.belikov.valteris.cycle.bicycle.model.Bicycle;
import com.belikov.valteris.cycle.bicycle.model.BicycleDTO;
import com.belikov.valteris.cycle.bicycle.model.BicycleType;
import com.belikov.valteris.cycle.bicycleDetail.BicycleDetailService;
import com.belikov.valteris.cycle.bicycleDetail.impl.BicycleDetailDTO;
import com.belikov.valteris.cycle.bicycleDetail.model.BicycleDetail;
import com.belikov.valteris.cycle.detail.DetailService;
import com.belikov.valteris.cycle.detail.model.DetailDTO;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@Controller
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class AdminController {
    private final String PATH_TO_BICYCLES_IMAGE_DIR = "images/bicycles/";
    private final String PATH_TO_DETAILS_IMAGE_DIR = "images/details/";

    private final BicycleService bicycleService;
    private final DetailService detailService;
    private final BicycleDetailService bicycleDetailService;

    @GetMapping("/bicycles-admin")
    public String adminPage() {
        return "bicycles-admin-page";
    }

    @GetMapping("/bicycle-editor/{id}")
    public String itemEditor(@PathVariable Long id, Model model) {
        BicycleDTO bicycle = bicycleService.getById(id).orElse(new BicycleDTO());
        model.addAttribute("bicycle", bicycle);
        return "bicycle-editor";
    }

    @GetMapping("/details-admin")
    public String detailsAdmin() {
        return "details-admin-page";
    }

    @GetMapping("/detail-editor/{detailId}")
    public String detailEditor(@PathVariable Long detailId, Model model) {
        model.addAttribute("detail", detailService.getById(detailId).orElse(new DetailDTO()));
        return "detail-editor";
    }

    @PostMapping("/bicycles-admin/bicycle/add")
    public String addBicycle(@RequestParam("image") MultipartFile photoFile,
                             @Valid @ModelAttribute("bicycle") BicycleDTO newBicycle,
                             BindingResult bindingResult,
                             Model model,
                             RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            return "bicycle-editor";
        }
        if (newBicycle.getPhoto() == null || newBicycle.getPhoto().isEmpty()) {
            String photo = StringUtils.cleanPath(photoFile.getOriginalFilename());
            newBicycle.setPhoto(photo);
            try {
                FileUtils.saveFile(PATH_TO_BICYCLES_IMAGE_DIR, newBicycle.getPhoto(), photoFile);
            } catch (IOException exception) {
                System.out.println(exception.getMessage());
                return "redirect:/bicycles-admin";
            }
        }
        String typeAsString = bicycleTypeAsRegularString(BicycleType.valueOf(newBicycle.getType()));
        newBicycle.setType(typeAsString);
        bicycleService.save(newBicycle);
        return "redirect:/bicycles-admin";
    }

    @GetMapping("/bicycle-admin/bicycle/remove/{id}")
    public String removeBicycle(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        BicycleDTO bicycleDTO = bicycleService.getById(id).orElse(new BicycleDTO());
        try {
            FileUtils.removeFile(PATH_TO_BICYCLES_IMAGE_DIR, bicycleDTO.getPhoto());
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
            return "redirect:/bicycles-admin";
        }
        bicycleService.delete(id);
        return "redirect:/bicycles-admin";
    }

    @PostMapping("/details-admin/detail/add")
    public String addDetail(@RequestParam("image") MultipartFile photoFile,
                            @Valid @ModelAttribute("detail") DetailDTO newDetail,
                            BindingResult bindingResult,
                            Model model,
                            RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "detail-editor";
        }
        if (newDetail.getPhoto() == null || newDetail.getPhoto().isEmpty()) {
            String photo = StringUtils.cleanPath(photoFile.getOriginalFilename());
            newDetail.photo = photo;
            try {
                FileUtils.saveFile(PATH_TO_DETAILS_IMAGE_DIR, photo, photoFile);
            } catch (IOException ioException) {
                return "redirect:/details-admin";
            }
        }
        detailService.save(newDetail);
        return "redirect:/details-admin";
    }

    @GetMapping("/details-admin/details/remove/{id}")
    public String removeDetail(@PathVariable Long id,
                               RedirectAttributes redirectAttributes) {
        try {
            DetailDTO detailToDelete = detailService.getById(id).orElse(new DetailDTO());
            List<BicycleDetailDTO> result = bicycleDetailService.getByDetailId(id);

            for (BicycleDetailDTO bicycleDetailPair : result) {
                BicycleDTO bicycle = bicycleDetailPair.getBicycle();
                bicycle.getDetailDTOS().removeIf(detailDTO -> detailDTO.getId() == id);
                bicycleService.save(bicycle);
            }

            if (detailToDelete.getPhoto() != null && !detailToDelete.getPhoto().isEmpty()) {
                FileUtils.removeFile(PATH_TO_DETAILS_IMAGE_DIR, detailToDelete.getPhoto());
            }
            detailService.delete(id);
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
            return "redirect:/details-admin";
        }
        return "redirect:/details-admin";
    }

    private String bicycleTypeAsRegularString(BicycleType bicycleType) {
        switch (bicycleType) {
            case MOUNTAIN:
                return "Mountain";
            case CITY:
                return "City";
            case FOR_KIDS:
                return "For kids";
            default:
                return "All";
        }
    }
}
