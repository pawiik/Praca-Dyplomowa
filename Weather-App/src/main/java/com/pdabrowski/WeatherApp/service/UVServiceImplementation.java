package com.pdabrowski.WeatherApp.service;

import com.pdabrowski.WeatherApp.dao.UVDAO;
import com.pdabrowski.WeatherApp.entity.UV;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
@Service
public class UVServiceImplementation implements UVService{

    UVDAO uvDao;

    @Autowired
    public UVServiceImplementation(UVDAO uvdao){
        this.uvDao = uvdao;
    }
    @Override
    @Transactional
    public void saveUV(UV uv) {
        uvDao.save(uv);
    }

    @Override
    @Transactional
    public Optional<UV> getUVById(int id) {
        return uvDao.findById(id);
    }

    @Override
    @Transactional
    public List<UV> getAllUVs() {
        return uvDao.findAll();
    }

    @Override
    @Transactional
    public void deleteUV(UV uv) {
        uvDao.delete(uv);
    }
}
