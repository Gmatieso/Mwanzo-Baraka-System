package com.gmatieso.mwanzo.common.response;

import java.util.List;

public record PaginatedResponse<T>(List<T> content,
                                   int pageNumber,
                                   int size,
                                   long totalElements,
                                   int totalPages,
                                   boolean isLastPage
                                   )
{
}
