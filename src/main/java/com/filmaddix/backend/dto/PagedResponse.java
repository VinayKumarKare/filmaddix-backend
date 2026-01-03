package com.filmaddix.backend.dto;

import java.util.List;

public class PagedResponse<T> {

    private List<T> content;

    private int pageNumber;
    private int pageSize;

    private long totalElements;
    private int totalPages;

    private boolean first;
    private boolean last;
    private boolean hasNext;

    protected PagedResponse() {
    }

    public PagedResponse(
            List<T> content,
            int pageNumber,
            int pageSize,
            long totalElements,
            int totalPages,
            boolean first,
            boolean last,
            boolean hasNext
    ) {
        this.content = content;
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        this.totalElements = totalElements;
        this.totalPages = totalPages;
        this.first = first;
        this.last = last;
        this.hasNext = hasNext;
    }

    public List<T> getContent() { return content; }
    public int getPageNumber() { return pageNumber; }
    public int getPageSize() { return pageSize; }
    public long getTotalElements() { return totalElements; }
    public int getTotalPages() { return totalPages; }
    public boolean isFirst() { return first; }
    public boolean isLast() { return last; }
    public boolean isHasNext() { return hasNext; }
}
