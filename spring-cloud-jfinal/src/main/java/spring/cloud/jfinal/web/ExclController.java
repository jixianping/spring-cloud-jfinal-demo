package spring.cloud.jfinal.web;


import com.jfinal.core.Controller;
import com.jfinal.upload.UploadFile;
import spring.cloud.client.model.Goods;
import spring.cloud.client.uitils.ImportExcelModelKit;
import spring.cloud.jfinal.service.GoodService;

import java.util.List;

public class ExclController extends Controller{


    public void  index(){
        render("/index.jsp");
    }

    public void exclimport(){
        UploadFile up = this.getFile("filePath");
        List<Goods> list = ImportExcelModelKit.getImportData(up.getFile(), Goods.class);
        GoodService.batchSave(list);
    }

}
