package com.dmurealfinal.vicmag.service;

import com.dmurealfinal.vicmag.domain.entity.utils.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UtilsService {

    @Autowired
    CategoryRepository categoryRepository;


    @Transactional
    public List<String> getCategoryList() {
        return categoryRepository.getCategoryList();
    }
}
