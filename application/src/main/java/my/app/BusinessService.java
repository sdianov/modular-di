package my.app;


import modular.di.annotations.Component;
import modular.di.annotations.Inject;

@Component
public class BusinessService {

    private final DataRepository dataRepository;

    @Inject
    public BusinessService(DataRepository dataRepository) {
        this.dataRepository = dataRepository;
    }

    private void doBusiness() {
        dataRepository.doWork();
    }


}
