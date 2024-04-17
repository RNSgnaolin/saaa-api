package world.gta.saaa.aircraft.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class PageListService {

    public <T> Page<T> listToPage(List<T> list, Pageable pageable) {

        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), list.size());
        List<T> pageContent = list.subList(start, end);

        return new PageImpl<>(pageContent, pageable, list.size());

    }

    public <T> List<T> pageToList(Page<T> page) {

        List<T> list = new ArrayList<>();
        
        page.forEach(list::add);
        return list;

    }
    
}
