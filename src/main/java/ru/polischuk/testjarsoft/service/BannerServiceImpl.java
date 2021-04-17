package ru.polischuk.testjarsoft.service;


import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.polischuk.testjarsoft.model.Banner;
import ru.polischuk.testjarsoft.repository.BannerRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BannerServiceImpl implements BannerService{

    BannerRepository bannerRepository;

    @Override
    public Optional<Banner> getById(Long id) {
        return bannerRepository.findByIdAndDeleted(id, false);
    }

    @Override
    public String save(Banner banner) {

        String checkResult = uniquenessCheck(banner);
        if(!checkResult.isEmpty()){
            return  checkResult;
        }

        bannerRepository.save(banner);
        return  checkResult;
    }

    @Override
    public void delete(Banner banner) {
        banner.setDeleted(true);
        bannerRepository.save(banner);
    }

    @Override
    public List<Banner> getAll() {
        log.info("In bannerServiseImpl getAll");
        return bannerRepository.findByDeleted(false);
    }

    public String uniquenessCheck(Banner bannerToSave){

        StringBuilder chekBannerName = new StringBuilder();

        List<Banner> bannerList = bannerRepository.findByDeleted(false).stream()
                .filter(banner -> !banner.getId().equals(bannerToSave.getId())).collect(Collectors.toList());

        for(Banner banner: bannerList){
            if(banner.getName().equalsIgnoreCase(bannerToSave.getName())){
                chekBannerName.append("Banner with name \"")
                        .append(banner.getName())
                        .append("\" already exist ");
                return chekBannerName.toString();
            }
        }

        return chekBannerName.toString();
    }
}
