package ru.practicum.evm.users.model.dto;

public class PageRequest {

    public static org.springframework.data.domain.PageRequest createPageRequest(Integer from, Integer size) {
        org.springframework.data.domain.PageRequest pageRequest;
        if (from < 0 || size < 0) {
            throw new IllegalArgumentException("Индекс первого элемента и количество элементов не могут быть отрицательными");
        }
        if (from == 0 && size == 0) {
            throw new IllegalArgumentException("Нечего возвращать");
        }
        int pageNumber = from / size;
        pageRequest = org.springframework.data.domain.PageRequest.of(pageNumber, Math.toIntExact(size));
        return pageRequest;
    }
}
