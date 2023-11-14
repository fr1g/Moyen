package su.kami.moyen.Exchange.Implement;

import su.kami.moyen.Model.Service;

import java.util.List;

public interface IServiceService {
    void NewService(Service service);
    void UpdatePlus(String plus, int id);
    List<Service> GetPagedServices(int page);
}
