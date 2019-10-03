package my.app;


import modular.di.annotations.DiComponent;
import modular.di.annotations.DiInject;

@DiComponent
public class BusinessService {

    private final DataRepository dataRepository;

    @DiInject
    public BusinessService(DataRepository dataRepository) {
        this.dataRepository = dataRepository;
    }

    private void doBusiness() {
        dataRepository.doWork();
    }


}
