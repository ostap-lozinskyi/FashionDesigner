package ua.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import ua.entity.ClothingModel;
import ua.model.filter.ClothingModelFilter;
import ua.model.request.ClothingModelRequest;
import ua.model.view.ClothingModelView;
import ua.repository.ClothingModelRepository;
import ua.repository.ClothingModelViewRepository;
import ua.repository.ColorRepository;
import ua.repository.SeasonRepository;
import ua.repository.SectionOfClothesRepository;
import ua.repository.TypeOfClothesRepository;
import ua.service.ClothingModelService;

@Service
public class ClothingModelServiceImpl implements ClothingModelService {

    private final ClothingModelRepository clothingModelRepository;

    private final ClothingModelViewRepository clothingModelViewRepository;

    private final SeasonRepository seasonRepository;

    private final TypeOfClothesRepository typeOfClothesRepository;

    private final SectionOfClothesRepository sectionOfClothesRepository;

    private final ColorRepository colorRepository;

    @Value("${cloudinary.url}")
    Cloudinary cloudinary = new Cloudinary();

    @Autowired
    public ClothingModelServiceImpl(ClothingModelRepository clothingModelRepository,
                                    ClothingModelViewRepository clothingModelViewRepository,
                                    SeasonRepository seasonRepository, TypeOfClothesRepository typeOfClothesRepository,
                                    SectionOfClothesRepository sectionOfClothesRepository,
                                    ColorRepository colorRepository) {
        this.clothingModelRepository = clothingModelRepository;
        this.clothingModelViewRepository = clothingModelViewRepository;
        this.seasonRepository = seasonRepository;
        this.typeOfClothesRepository = typeOfClothesRepository;
        this.sectionOfClothesRepository = sectionOfClothesRepository;
        this.colorRepository = colorRepository;
    }

    @Override
    public Page<ClothingModelView> findAllClothingModelViews(ClothingModelFilter filter, Pageable pageable) {
        return clothingModelViewRepository.findAllClothingModelView(filter, pageable);
    }

    @Override
    public void saveClothingModel(ClothingModelRequest clothingModelRequest) {
        ClothingModel clothingModel = new ClothingModel();
        clothingModel.setId(clothingModelRequest.getId());
        clothingModel.setName(clothingModelRequest.getName());
        clothingModel.setText(clothingModelRequest.getText());
        clothingModel.setSeason(clothingModelRequest.getSeason());
        clothingModel.setTypeOfClothes(clothingModelRequest.getTypeOfClothes());
        clothingModel.setSectionOfClothes(clothingModelRequest.getSectionOfClothes());
        clothingModel.setColors(clothingModelRequest.getColors());
        clothingModel.setPhotoUrls(clothingModelRequest.getPhotoUrls());
        clothingModel.setVersion(clothingModelRequest.getVersion());
        clothingModelRepository.save(clothingModel);
    }

    @Override
    public ClothingModelRequest findOneRequest(Integer id) {
        ClothingModel clothingModel = clothingModelRepository.findOneRequest(id);
        ClothingModelRequest clothingModelRequest = new ClothingModelRequest();
        clothingModelRequest.setId(clothingModel.getId());
        clothingModelRequest.setName(clothingModel.getName());
        clothingModelRequest.setText(clothingModel.getText());
        clothingModelRequest.setSeason(clothingModel.getSeason());
        clothingModelRequest.setTypeOfClothes(clothingModel.getTypeOfClothes());
        clothingModelRequest.setSectionOfClothes(clothingModel.getSectionOfClothes());
        clothingModelRequest.setColors(clothingModel.getColors());
        clothingModelRequest.setPhotoUrls(clothingModel.getPhotoUrls());
        clothingModelRequest.setVersion(clothingModel.getVersion());
        return clothingModelRequest;
    }

    @Override
    public void deleteClothingModel(Integer id) {
        clothingModelRepository.delete(id);
    }

    @Override
    public ClothingModelView findClothingModelViewById(Integer id) {
        return clothingModelRepository.findClothingModelViewById(id);
    }

    public ClothingModelRequest uploadPhotoToCloudinary(ClothingModelRequest clothingModelRequest, MultipartFile[] multipartFiles)
            throws IOException {
        List<String> photoUrls = new ArrayList<>();
        int i = 0;
        for (MultipartFile multipartFile : multipartFiles) {
            String photoName = clothingModelRequest.getName() + "_" + i;
            @SuppressWarnings("rawtypes")
            Map uploadResult = cloudinary.uploader().upload(multipartFile.getBytes(),
                    ObjectUtils.asMap("public_id", photoName));
            i++;
            String cloudinaryUrl = (String) uploadResult.get("url");
            photoUrls.add(cloudinaryUrl);
        }

        List<String> oldPhotoUrls = clothingModelRequest.getPhotoUrls();
        if (oldPhotoUrls.containsAll(photoUrls) && photoUrls.containsAll(oldPhotoUrls)) {
            clothingModelRequest.setVersion(clothingModelRequest.getVersion() + 1);
        } else {
            clothingModelRequest.setVersion(0);
        }
        clothingModelRequest.setPhotoUrls(photoUrls);
        return clothingModelRequest;
    }

    @Override
    public List<String> findAllSeasonNames() {
        return seasonRepository.findAllSeasonsNames();
    }

    @Override
    public List<String> findAllTypeOfClothesNames() {
        return typeOfClothesRepository.findAllTypeOfClothesNames();
    }

    @Override
    public List<String> findAllSectionOfClothesNames() {
        return sectionOfClothesRepository.findAllSectionOfClothesNames();
    }

    @Override
    public List<String> findAllColorsNames() {
        return colorRepository.findAllColorsNames();
    }

    @Override
    public List<String> findPhotoUrls(Integer id) {
        return clothingModelRepository.findPhotoUrls(id);
    }

}