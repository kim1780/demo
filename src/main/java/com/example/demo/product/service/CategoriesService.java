package com.example.demo.product.service;

import com.example.demo.common.dto.ProductRes;
import com.example.demo.common.entity.ProductEntity;
import com.example.demo.product.dto.CategoryRequest;
import com.example.demo.product.entity.CategoriesEntity;
import com.example.demo.product.repository.CategoriesRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoriesService {

    private final CategoriesRepository categoriesRepository;
    public CategoriesService(CategoriesRepository categoriesRepository) {
        this.categoriesRepository = categoriesRepository;
    }

    public void saveCategories(CategoryRequest categoryRequest){
        CategoriesEntity categoriesEntity = new CategoriesEntity();
        categoriesEntity.setName(categoryRequest.getName());
        categoriesEntity.setDescription(categoryRequest.getDescription());
        categoriesRepository.save(categoriesEntity);
    }

    public List<CategoryRequest> findall() {
        List<CategoryRequest> categoryResList = new ArrayList<> ();
        List<CategoriesEntity> categoriesEntities = (List<CategoriesEntity>) categoriesRepository.findAll();


        for (int i = 0; i < categoriesEntities.size(); i++){
            CategoryRequest categoryRes = new CategoryRequest();
            categoryRes.setId(categoriesEntities.get(i).getId());
            categoryRes.setName(categoriesEntities.get(i).getName());
            categoryRes.setDescription(categoriesEntities.get(i).getDescription());
            categoryResList.add(categoryRes);

        }
        return categoryResList;
    }
}
