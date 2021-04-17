package ru.polischuk.testjarsoft.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.polischuk.testjarsoft.model.Banner;
import ru.polischuk.testjarsoft.model.Request;
import ru.polischuk.testjarsoft.repository.BannerRepository;
import ru.polischuk.testjarsoft.repository.RequestRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RequestServiceImpl implements RequestService{

    RequestRepository requestRepository;
    BannerRepository bannerRepository;

    @Override
    public String getBannerContent(String remoteAddress, String userAgent, String reqName) {

        LocalDateTime date = LocalDateTime.now().minusDays(1);

        List<Banner> featuredBanners = requestRepository
                .findAllByDateAfterAndRemoteAddressAndUserAgent(date,remoteAddress,userAgent)
                .stream()
                .map(Request::getBanner)
                .filter(banner -> banner.getDeleted().equals(false))
                .filter(banner -> banner.getCategory().getReqName().equalsIgnoreCase(reqName))
                .collect(Collectors.toList());

        List<Banner> bannerList =bannerRepository.findAllByCategory_ReqNameAndDeletedFalse(reqName).stream()
                .filter(banner -> !featuredBanners.contains(banner)).collect(Collectors.toList());

        if(bannerList.isEmpty()) {
            return "";
        }

        Banner bannerToSave = randomBannerWithMaxPrice(bannerList);

        saveRequest(remoteAddress, userAgent,bannerToSave);

        return bannerToSave.getContent();

    }

    private Banner randomBannerWithMaxPrice(List<Banner> bannerList) {

        if(bannerList.size()==1){
            return bannerList.get(0);
        }
        BigDecimal maxPrice = bannerList.stream()
                                .max(Comparator.comparing(Banner::getPriceEntity)).get().getPriceEntity();

        List<Banner> bannersWithMaxPrice = bannerList.stream()
                                            .filter(banner -> banner.getPriceEntity().equals(maxPrice))
                                            .collect(Collectors.toList());


        return bannersWithMaxPrice.stream()
                .skip(new Random().nextInt(bannersWithMaxPrice.size()-1))
                .findAny().get();


    }

    private void saveRequest(String remoteAddress, String userAgent, Banner banner){

        requestRepository.save(new Request(null,banner,userAgent,remoteAddress,LocalDateTime.now()));
    }
}
