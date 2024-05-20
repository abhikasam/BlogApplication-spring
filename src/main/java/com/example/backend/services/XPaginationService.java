package com.example.backend.services;

import com.example.backend.entities.XPagination;
import com.google.gson.Gson;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class XPaginationService {
    public XPagination getxPagination(String xpag){
        Gson gson=new Gson();
        XPagination xPagination=gson.fromJson(xpag,XPagination.class);
        if(xPagination!=null){
            return xPagination;
        }
        return new XPagination();
    }

    public <T> List<T> getPaginatedResult(List<T> list,XPagination xpagination){
        var totalRecords = list.size();

        var totalPages = (totalRecords + xpagination.getPageSize()) / xpagination.getPageSize();

        if (totalRecords % xpagination.getPageSize() == 0)
        {
            totalPages--;
        }

        if (totalPages < xpagination.getPageNumber())
        {
            xpagination.setPageNumber(1);
        }

        var skip = (xpagination.getPageNumber() - 1) * xpagination.getPageSize();
        var take = xpagination.getPageSize();

        xpagination.setTotalPages(totalPages);

        return list.stream().skip(skip).limit(take).collect(Collectors.toList());
    }

    public void setXPagination(XPagination xPagination, HttpServletResponse response){
        Gson gson=new Gson();
        String xpag=gson.toJson(xPagination);
        response.addHeader("x-pagination",xpag);
    }

}
