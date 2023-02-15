package com.test.salon.service.master;

import com.test.salon.repository.MasterRepo;
import com.test.salon.dto.MasterCreateRequestDto;
import com.test.salon.model.Master;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MasterServiceImpl implements MasterService {

    @Autowired
    private MasterRepo masterRepo;

    @Override
    public List<Master> findMasters(Integer rang){

        List<Master> masters;

        if(rang != null) {
            masters = masterRepo.findByRang(rang);
        } else masters = masterRepo.findAll();

        return masters;
    }

    @Override
    public void saveMaster(Master master, String name, String surname, Integer rang){

        master.setName(name);
        master.setSurname(surname);
        master.setRang(rang);
        masterRepo.save(master);

    }

    @Override
    public void deleteMaster(Master master){

        masterRepo.deleteById(master.getId());

    }

    @Override
    public void addMaster(MasterCreateRequestDto masterCreateRequestDto){

        Master master = new Master(masterCreateRequestDto.getName(), masterCreateRequestDto.getSurname(),
                masterCreateRequestDto.getRang());
        masterRepo.save(master);

    }

}
