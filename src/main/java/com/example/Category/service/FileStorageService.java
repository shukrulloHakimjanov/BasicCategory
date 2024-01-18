package com.example.Category.service;

import com.example.Category.mapper.FileStoreageMapper;
import com.example.Category.model.basic.Category;
import com.example.Category.model.basic.CategoryGetDto;
import com.example.Category.model.file.FileStorAge;
import com.example.Category.model.file.FileStorAgeGetDto;
import com.example.Category.model.file.FileStorageStatus;
import com.example.Category.repository.CategoryRepository;
import com.example.Category.repository.FileStorageRepository;
import org.hashids.Hashids;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.List;


@Service
public class FileStorageService {
    private final FileStorageRepository fileStorageRepository;
    private final CategoryRepository categoryRepository;
    @Value("${upload.folder}")
    private String uploadFolder;
    private final Hashids hashids;
    private final FileStoreageMapper fileStoreageMapper;

    public FileStorageService(FileStorageRepository fileStorageRepository, FileStoreageMapper fileStoreageMapper, CategoryRepository categoryRepository) {
        this.fileStorageRepository = fileStorageRepository;
        this.fileStoreageMapper = fileStoreageMapper;
        this.hashids=new Hashids(getClass().getName(),6);
        this.categoryRepository = categoryRepository;
    }


    public List<FileStorAge> findAll() {
        return fileStorageRepository.findAll();
    }


    public void save(MultipartFile multipartFile){
        FileStorAge fileStorAge=new FileStorAge();
        fileStorAge.setName(multipartFile.getOriginalFilename());
        fileStorAge.setExtansion(getExtansion(multipartFile.getOriginalFilename()));
        fileStorAge.setFileSize(multipartFile.getSize());
        fileStorAge.setContentType(multipartFile.getContentType());
        fileStorAge.setFileStorageStatus(FileStorageStatus.DRAFT);
        fileStorageRepository.save(fileStorAge);

        Date now=new Date();
        File uploadFolder=new File(String.format("%s/upload_files/%d/%d/%d",this.uploadFolder,
                1900+now.getYear(),1+now.getMonth(),now.getDate()));
        if (!uploadFolder.exists()&&uploadFolder.mkdirs()){
            System.out.println("Files have   already created");
        }
        fileStorAge.setHashId(hashids.encode(fileStorAge.getId()));
        fileStorAge.setUploadPath(String.format("upload_files/%d/%d/%d/%s.%s",
                1900+now.getYear()
                ,1+now.getMonth()
                ,now.getDate()
                ,fileStorAge.getHashId(),fileStorAge.getExtansion()));
        fileStorageRepository.save(fileStorAge);
        uploadFolder=uploadFolder.getAbsoluteFile();
        File file=new File(uploadFolder,String.format("%s.%s",fileStorAge.getHashId(),fileStorAge.getExtansion()));

        try{
            multipartFile.transferTo(file);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void delete(String hashId){
        FileStorAge fileStorAge =findByHashId(hashId);
        File file=new File(String.format("%s/%s",this.uploadFolder,fileStorAge.getUploadPath()));
        if (file.delete()){
            fileStorageRepository.delete(fileStorAge);
        }
    }


    public FileStorAge findByHashId(String hashId){
        return fileStorageRepository.findByHashId(hashId);
    }



    private String getExtansion(String fileName) {
        String ext=null;
        if(fileName!=null&&!fileName.isEmpty()){
            int dot =fileName.lastIndexOf('.');
            if(dot>0&&dot<=fileName.length()-2){
                ext=fileName.substring(dot+1);
            }
        }
        return ext;

    }


}
