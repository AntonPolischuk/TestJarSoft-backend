package ru.polischuk.testjarsoft.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.polischuk.testjarsoft.model.Banner;
import ru.polischuk.testjarsoft.model.Category;
import ru.polischuk.testjarsoft.repository.BannerRepository;
import ru.polischuk.testjarsoft.repository.CategoryRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CategoryServiceImpl implements CategoryService{

    CategoryRepository categoryRepository;
    BannerRepository bannerRepository;

    @Override
    public Optional<Category> getById(Long id) {
        log.info("In CategoryServiceImpl getById{} ", id);
        return categoryRepository.findByIdAndDeletedFalse(id);
    }

    @Override
    public String save(Category category) {
        log.info("In CategoryServiceImpl save{} ", category);
        String checkResult= uniquenessCheck(category);
        if(!checkResult.isEmpty()){
            return  checkResult;
        }
        categoryRepository.save(category);
        return checkResult;
    }

    @Override
    public String delete(Category category) {

        log.info("In CategoryServiceImpl delete{} ", category.getCategoryName());
        String result = checkingIfThereAreBanners(category);
        if(!result.isEmpty()){
           return result;
        }
        category.setDeleted(true);
        categoryRepository.save(category);
        return  result;
    }

    @Override
    public List<Category> getAll() {
        log.info("In CategoryServiceImpl getAll");
        return categoryRepository.findByDeletedFalse();
    }

    public String checkingIfThereAreBanners(Category category){
        StringBuilder chek = new StringBuilder();
        List<Banner> bannerList = bannerRepository.findByDeleted(false).stream()
                .filter(banner -> banner.getCategory().equals(category)).collect(Collectors.toList());
        if(bannerList.isEmpty()){
            return chek.toString();
        }
        for(Banner banner:bannerList){
            chek.append(banner.getId()).append(" ").append(banner.getName()).append(" ");
        }
            return chek.toString();
    }

    public String uniquenessCheck(Category categoryToSave){

        StringBuilder chek = new StringBuilder();

        List<Category> categoryList = categoryRepository.findByDeletedFalse().stream()
           .filter(category -> !category.getId().equals(categoryToSave.getId())).collect(Collectors.toList());

        for(Category category: categoryList){
              if(category.getCategoryName().equalsIgnoreCase(categoryToSave.getCategoryName())){
                chek.append("Category with name \"")
                        .append(category.getCategoryName())
                        .append("\" already exist ");
                return chek.toString();
            }
            if(category.getReqName().equalsIgnoreCase(categoryToSave.getReqName())){
                chek.append("Category with Request ID \"")
                        .append(category.getCategoryName())
                        .append("\" already exist ");
                return chek.toString();
            }
        }
        return chek.toString();
    }
}
