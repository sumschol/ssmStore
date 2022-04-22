package com.sumschol.utils;

import com.github.pagehelper.PageInfo;

import java.util.ArrayList;
import java.util.List;

public class PageInfoUtil {

    public final static List<Integer> getPagesList(PageInfo pageInfo){
        List<Integer> PagesList = new ArrayList<>();
        for (int i = 1; i <= pageInfo.getPages(); i++) {
            PagesList.add(i);
        }
        return PagesList;
    }
}
