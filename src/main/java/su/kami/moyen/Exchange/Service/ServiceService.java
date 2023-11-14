package su.kami.moyen.Exchange.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import su.kami.moyen.Exchange.Implement.IServiceService;
import su.kami.moyen.Exchange.Mapper.IServicesMapper;
import su.kami.moyen.Helper.PageHelper;
import su.kami.moyen.Model.Service;

import java.util.List;

@org.springframework.stereotype.Service //CNM
@Transactional
public class ServiceService implements IServiceService {

    @Autowired
    IServicesMapper _s;

    @Override
    public void NewService(Service service) {
        _s.NewService(service);
    }

    @Override
    public void UpdatePlus(String plus, int id) {
        _s.UpdatePlus(plus, id); // overwrite tag
    }

    @Override
    public List<Service> GetPagedServices(int page) {
        return _s.GetPagedServices(PageHelper.P(page));
    }
}
