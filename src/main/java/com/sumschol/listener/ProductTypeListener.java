package com.sumschol.listener;

import com.sumschol.pojo.ProductType;
import com.sumschol.service.ProductTypeService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.List;

@WebListener
public class ProductTypeListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
//        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext_*.xml");
//        ProductTypeService productTypeService = (ProductTypeService) context.getBean("ProductTypeServiceImpl");
//        List<ProductType> productTypeList = productTypeService.getall();
//        servletContextEvent.getServletContext().setAttribute("typeList",productTypeList);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
